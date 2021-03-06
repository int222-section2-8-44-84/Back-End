package sit.project.intregratedbackend.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Roles")
public class Roles {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
		@GenericGenerator(name = "native", strategy = "native")
		@Column(name = "RoleID")
		private int roleID;

		@Column(name = "Role")
		private String role;
		
		@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
		Set<AuthenticationUser> authenticationUsers;

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public int getRoleID() {
			return roleID;
		}
		
		
}