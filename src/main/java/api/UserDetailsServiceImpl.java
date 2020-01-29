package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import api.domain.UserRepository;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		api.domain.User user = repository.findByLogin(username);
		
		if( user == null )  throw new UsernameNotFoundException("Usuario não encotrado");
		
		return user;
		
		//return User.withUsername(username).password(user.getSenha()).roles("USER").build();
		
		
//		if (username.equals("user")) {
//			return User.withUsername(username).password(encoder.encode("123")).roles("USER").build();
//		} else if (username.equals("user")) {
//			return User.withUsername(username).password(encoder.encode("321")).roles("USER", "ADMIN").build();
//		}
		//throw new UsernameNotFoundException("Usuario não encotrado");
	}

}
