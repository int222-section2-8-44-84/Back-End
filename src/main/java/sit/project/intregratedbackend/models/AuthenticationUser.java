package sit.project.intregratedbackend.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Table(name = "Accounts") 
@Entity
public class AuthenticationUser extends User implements Serializable {

	private static final long serialVersionUID = -7924450568553326886L;

	public AuthenticationUser() {
		super("anonymous", "", new ArrayList<>());
	}

	public AuthenticationUser(String userID, String password, Collection<? extends GrantedAuthority> authorities) {
		super(userID, password, authorities);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AccountNumber")
	private int accountNumber;
	
	@Column(name = "UserID")
	private String userID;
	
	@Column(name = "Password")
	private String pass;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "RoleID")
	private int roleID;
	
	@OneToMany(mappedBy = "account")
	Set<Posts> Posts;
	
//	@OneToMany(mappedBy = "accounts")
//	Set<FeelToPost> FeelToPosts;
	
	@ManyToOne
	@JoinColumn(name = "RoleID", insertable = false, updatable = false)
	Roles role;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return pass;
	}

	public void setPassword(String password) {
		this.pass = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAccountNumber() {
		return accountNumber;
	}
	

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

}
