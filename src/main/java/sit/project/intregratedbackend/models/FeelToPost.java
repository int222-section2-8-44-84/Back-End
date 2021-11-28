package sit.project.intregratedbackend.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

public class FeelToPost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "LikePostNember")
	private int likePostNember;

	@Column(name = "PostTitle")
	private String postTitle;

	@Column(name = "Feel")
	private String feel;

	@Column(name = "AccountNumber")
	private int accountNumber;

	@Column(name = "PostNumber")
	private float postNumber;

//	@OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, orphanRemoval = true)
//	Set<Posts_has_Tags> postTags;
//
//	@ManyToOne
//	@JoinColumn(name = "AccountNumber", insertable = false, updatable = false)
//	AuthenticationUser account;
//
//	@ManyToOne
//	@JoinColumn(name = "CategoryID", insertable = false, updatable = false)
//	Categories categories;
}
