package sit.project.intregratedbackend.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sit.project.intregratedbackend.models.AuthenticationUser;
import sit.project.intregratedbackend.models.FeelToPost;
import sit.project.intregratedbackend.models.Posts;
import sit.project.intregratedbackend.models.Roles;
import sit.project.intregratedbackend.repositories.AccountsRepository;
import sit.project.intregratedbackend.repositories.FeelToPostRepository;
import sit.project.intregratedbackend.repositories.PostsRepository;
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
	
	@Autowired
    FeelToPostRepository feelToPostJpaRepository;
	
	@Autowired
    PostsRepository postsJpaRepository;
	
	@GetMapping("/showAllAccounts")
	public List<AuthenticationUser> showAllAccount(){
		return accountRepo.findAll();
	}
	
    @GetMapping("/accounts/{accountNumber}")
    public AuthenticationUser showAccount(@PathVariable int accountNumber) {
        return accountRepo.findById(accountNumber).orElse(null);
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
	
	@PutMapping("/editAccount/{accountNumber}")
	public String editAccount(@PathVariable("accountNumber") int accountNumber,@RequestParam("userID") String userID,@RequestParam("email") String email) {
		try {
		AuthenticationUser editingAccount = accountRepo.findById(accountNumber).get();
		if(editingAccount!=null) {
		editingAccount.setUserID(userID);
		editingAccount.setEmail(email);
		return "Update account success.";
		} else {
			return "Invalid this account.";
		}
		} catch (Exception e) {
			return e.getMessage();
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
			if(accountRepo.findByuserID(userID).get().getUserID().equals(userID)) {
				//System.out.println(accountRepo.findByuserID(userID).get().getUserID());
				return true ;
			}else {
				//System.out.println(accountRepo.findByuserID(userID).get().getUserID());
				return false;
			}
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public boolean checkEmail(String email) {
		try {
			if(accountRepo.findByemail(email).get().getEmail().equals(email)) {
				return true ;
			}else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}
	
	@PutMapping(value = "/manageRole")
	public String manageRole(@RequestParam("accountNumber") int accountNumber,@RequestParam("roleID") int roleID) {
		AuthenticationUser account = accountRepo.findById(accountNumber).orElse(null);
		account.setRoleID(roleID);
		accountRepo.save(account);
		return "Change roleID "+ account.getUserID() + "to" + roleID;
	}
	
	@DeleteMapping("/deleteAccount/{accountNumber}")
	public String deleteAccount(@PathVariable("accountNumber") int accountNumber) throws Exception {
		try {
			deleteAccountInPost(accountNumber);
			accountRepo.deleteById(accountNumber);
			return "Delete Account Number "+ accountNumber+" Complete.";
		} catch (Exception e) {
			return "Can't delete Account Number "+accountNumber+"\n" + e.getMessage();
		}
	}
	
	public void deleteAccountInPost(int accountNumber) throws IOException {
    	List<Posts> allFeelOfThisPost = postsJpaRepository.findAllByaccountNumber(accountNumber);
    	for (int i = 0; i < allFeelOfThisPost.size(); i++) {
			allFeelOfThisPost.get(i).setAccountNumber(0);
		}
    }
	
	public void deleteFeels(int accountNumber) throws IOException {
    	List<FeelToPost> allFeelOfThisPost = feelToPostJpaRepository.findAllByaccountNumber(accountNumber);
    	feelToPostJpaRepository.deleteAll(allFeelOfThisPost);
    }
}
