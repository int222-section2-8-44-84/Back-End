package sit.project.intregratedbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FeelToPost") 
public class FeelToPost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "LikePostNumber")
	private int likePostNember;

	@Column(name = "Feel")
	private String feel;

	@Column(name = "AccountNumber")
	private int accountNumber;

	@Column(name = "PostNumber")
	private int postNumber;

	public String getFeel() {
		return feel;
	}

	public void setFeel(String feel) {
		this.feel = feel;
	}

	public FeelToPost() {
		
	}
	
	public FeelToPost(int accountNumber, int postNumber) {
//		super();
//		this.feel = feel;
		this.accountNumber = accountNumber;
		this.postNumber = postNumber;
	}

	public int getLikePostNember() {
		return likePostNember;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public int getPostNumber() {
		return postNumber;
	}

//	public AuthenticationUser getAccounts() {
//		return accounts;
//	}
//
//	public AuthenticationUser getPost() {
//		return post;
//	}

//	@ManyToOne
//	@JoinColumn(name = "AccountNumber", insertable = false, updatable = false)
//	AuthenticationUser accounts;
//	
//	@ManyToOne
//	@JoinColumn(name = "PostNumber", insertable = false, updatable = false)
//	AuthenticationUser post;
	
}
