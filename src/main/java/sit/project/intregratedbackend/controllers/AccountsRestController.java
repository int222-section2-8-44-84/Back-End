package sit.project.intregratedbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sit.project.intregratedbackend.models.AuthenticationUser;
import sit.project.intregratedbackend.models.Posts;
import sit.project.intregratedbackend.models.Roles;
import sit.project.intregratedbackend.repositories.AccountsRepository;
import sit.project.intregratedbackend.repositories.RolesRepository;
import sit.project.intregratedbackend.services.ServiceUtil;

@CrossOrigin
@RestController
public class AccountsRestController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountsRepository accountRepo;
	
//	@Autowired
//	private RolesRepository roleRepo;
	
	@GetMapping("/showAllAccounts")
	public List<AuthenticationUser> showAllAccount(){
		return accountRepo.findAll();
	}
	
    @GetMapping("/accounts/{accountNumber}")
    public AuthenticationUser showAccount(@PathVariable String accountNumber) {
        return accountRepo.findById(Integer.parseInt(accountNumber)).orElse(null);
    }
	
	
	@PostMapping(value = "/register")
	public String register(@RequestParam("userID") String userID,@RequestParam("password") String password,@RequestParam("email") String email) {
		String encodedPassword = passwordEncoder.encode(password);
		if(!checkUserID(userID)) {
			System.out.println("Pass checkUserID");
			if(!checkEmail(email)) {
				System.out.println("Pass checkEmail");
				AuthenticationUser newAccount = new AuthenticationUser();
				//Roles userRole = roleRepo.getById(2);
				newAccount.setUserID(userID);
				newAccount.setPassword(encodedPassword);
				newAccount.setEmail(email);
				//newAccount.setRole(userRole);
				newAccount.setRoleID(2);
				accountRepo.save(newAccount);
				System.out.println("Register Complete.");
				return "Register Complete.";
			} else {
				System.out.println("This email has already exit.");
				return "This email has already exit.";
			}
			
		} else {
			System.out.println("This userID has already exit.");
			return "This userID has already exit.";
		}
	}
	
	@GetMapping("/me")
	public AuthenticationUser getMe() {
		String username = ServiceUtil.getUsername();
		return accountRepo.findByuserID(username).get();
	}
	
	@GetMapping(value = "/checkUserID/{userID}")
	public boolean checkUserID(@PathVariable("userID") String userID) {
		try {
			if(accountRepo.findByuserID(userID).get().getUsername().equals(userID)) {
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
			if(accountRepo.findByemail(email).get().getEmail().equals(email)) {
				return false;
			}else {
				return true;
			}
		} catch (Exception ex) {
			return false;
		}
	}
	
	
}
