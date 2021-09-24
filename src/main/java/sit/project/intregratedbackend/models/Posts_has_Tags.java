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
@Table(name = "Posts_has_Tags")
public class Posts_has_Tags {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	@Column(name = "PostTagNumber")
	private int postTagNumber;
	
	@Column(name = "PostNumber")
	private int postsNumber;
	
	@Column(name = "TagID")
	private int tagId;
	
	@ManyToOne
    @JoinColumn(name = "TagID", insertable = false, updatable = false)
    Tags tags;
	
	@ManyToOne
	@JoinColumn(name = "PostNumber", insertable = false, updatable = false)
	Posts posts;

	public int getPostTagNumber() {
		return postTagNumber;
	}

	public void setPostTagNumber(int postTagNumber) {
		this.postTagNumber = postTagNumber;
	}

	public int getPostsNumber() {
		return postsNumber;
	}

	public void setPostsNumber(int postsNumber) {
		this.postsNumber = postsNumber;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}


}
