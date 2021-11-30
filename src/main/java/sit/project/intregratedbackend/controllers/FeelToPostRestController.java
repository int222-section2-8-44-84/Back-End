package sit.project.intregratedbackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sit.project.intregratedbackend.models.FeelToPost;
//import sit.project.intregratedbackend.repositories.AccountsRepository;
import sit.project.intregratedbackend.repositories.FeelToPostRepository;

@CrossOrigin
@RestController
public class FeelToPostRestController {

//	@Autowired
//	private AccountsRepository accountRepo;

	@Autowired
	private FeelToPostRepository feelRepo;

	@GetMapping("/showAllFeelToPost")
	public List<FeelToPost> showAllFeelToPost() {
		return feelRepo.findAll();
	}

	@GetMapping("/showFeelToPost/{accountNumber}")
	public List<FeelToPost> showAllFeelToPost(@PathVariable("accountNumber") int accountNumber) {
		return feelRepo.findAllByaccountNumber(accountNumber);
	}

	@GetMapping("/findFeelOfPost/{postNumber}/{accountNumber}")
	public Optional<FeelToPost> findFeelByPostNumber(@PathVariable("postNumber") int postNumber,
			@PathVariable("accountNumber") int accountNumber) {
		List<FeelToPost> feelByAccount = feelRepo.findAllByaccountNumber(accountNumber);
		if (feelByAccount.size() != 0) {
			for (int i = 0; i < feelByAccount.size(); i++) {
				if (feelByAccount.get(i).getPostNumber() == postNumber) {
					return feelRepo.findById(feelByAccount.get(i).getLikePostNember());
				}
			}
		} else {
			return null;
		}
		return null;

	}

	@PutMapping("/feel")
	public String manageFeel(@RequestParam("accountNumber") int accountNumber,
			@RequestParam("postNumber") int postNumber, @RequestParam("feel") String feel) {

		int foundLikePostNember = checkFeelHistory(accountNumber, postNumber, feel);
		if (foundLikePostNember == -1) {
			return addFeel(accountNumber, postNumber, feel);
		} else {
			return changeFeel(foundLikePostNember, feel);
		}
	}

	public int checkFeelHistory(@RequestParam("accountNumber") int accountNumber,
			@RequestParam("postNumber") int postNumber, @RequestParam("feel") String feel) {

		List<FeelToPost> feltPost = feelRepo.findAllByaccountNumber(accountNumber);

		for (int i = 0; i < feltPost.size(); i++) {
			if (feltPost.get(i).getPostNumber() == postNumber) {
				System.out.println(feltPost.get(i).getLikePostNember());
				return feltPost.get(i).getLikePostNember();
			}
		}
		return -1;
	}

	public String addFeel(int accountNumber, int postNumber, String feel) {

		FeelToPost feelToPost = new FeelToPost(accountNumber, postNumber);
		feelToPost.setFeel(feel);
		feelRepo.save(feelToPost);
		return "Add: Account Number " + accountNumber + " " + feel + " Post Number " + postNumber;
	}

	public String changeFeel(int likePostNember, String feel) {

		FeelToPost feelToPost = feelRepo.findById(likePostNember).orElse(null);
		feelToPost.setFeel(feel);
		feelRepo.save(feelToPost);
		return "Update: Account Number " + feelToPost.getAccountNumber() + " " + feel + " Post Number "
				+ feelToPost.getPostNumber();
	}
}
