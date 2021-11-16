package sit.project.intregratedbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sit.project.intregratedbackend.models.AuthenticationUser;
import sit.project.intregratedbackend.models.Roles;
import sit.project.intregratedbackend.repositories.AccountsRepository;
import sit.project.intregratedbackend.repositories.RolesRepository;

public class AccountsRestController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountsRepository accountRepo;
	
	@Autowired
	private RolesRepository roleRepo;
	
	@PostMapping(value = "/register")
	public boolean register(@RequestParam("userID") String userID,@RequestParam("password") String password,@RequestParam("email") String email) {
		String encodedPassword = passwordEncoder.encode(password);
		if(checkUserID(userID)) {
			if(checkEmail(email)) {
				AuthenticationUser newAccount = new AuthenticationUser();
				Roles userRole = roleRepo.getById(2);
				newAccount.setUserID(userID);
				newAccount.setPassword(encodedPassword);
				newAccount.setEmail(email);
				newAccount.setRole(userRole);
				accountRepo.save(newAccount);
				System.out.println("Register Complete.");
				return true;
			} else {
				System.out.println("This email has already exit.");
				return false;
			}
			
		} else {
			System.out.println("This userID has already exit.");
			return false;
		}
	}
	
	public boolean checkUserID(String userID) {
		try {
			if(accountRepo.findByuserID(userID).get().getUsername().equalsIgnoreCase(userID)) {
				return false;
			}else {
				return true;
			}
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean checkEmail(String email) {
		try {
			if(accountRepo.findByemail(email).get().getEmail().equalsIgnoreCase(email)) {
				return false;
			}else {
				return true;
			}
		} catch (Exception ex) {
			return false;
		}
	}
}