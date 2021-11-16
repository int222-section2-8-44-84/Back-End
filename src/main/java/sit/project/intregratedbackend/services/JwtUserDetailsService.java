package sit.project.intregratedbackend.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sit.project.intregratedbackend.models.AuthenticationUser;
import sit.project.intregratedbackend.repositories.AccountsRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {    
	@Autowired    
	AccountsRepository userRepository ;    
	@Override 
	public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
		//        if ("khaitong".equals(username)) {
		//            return new User("khaitong",
		//                    "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
		//                    new ArrayList<>());
		//} else {
		//            throw new UsernameNotFoundException("User not found with username: " + username);
		//}  
		AuthenticationUser user = userRepository.findByuserID(userID).get();
		
		return new org.springframework.security.core.userdetails.User(user.getUserID(), user.getPassword(),
                new ArrayList<>());
	}
}