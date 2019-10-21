package tseo.tseo19.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tseo.tseo19.model.User;
import tseo.tseo19.model.UserAuthority;
import tseo.tseo19.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(String.format("User with username '%s' does not exist.", username));
		} else {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			for (UserAuthority ua: user.getAuthorities()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(ua.getAuthority().getName()));
			}
			
			//Java 1.8 way   	
	    	/*List<GrantedAuthority> grantedAuthorities = user.getUserAuthorities().stream()
	                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getName()))
	                .collect(Collectors.toList());*/
			
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(),
					user.getPassword(),
					grantedAuthorities);
			
		}
	}

}
