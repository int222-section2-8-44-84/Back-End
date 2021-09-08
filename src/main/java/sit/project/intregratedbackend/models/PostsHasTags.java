package sit.project.intregratedbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Posts_has_Tags")
public class PostsHasTags {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PostTagNumber")
	private int postTagNumber;
	
	@Column(name = "Posts_PostNumber")
	private int posts_PostNumber;
	
	@Column(name = "Tags_TagID")
	private int tags_TagId;
	
	@ManyToOne
    @JoinColumn(name = "TagID", insertable = false, updatable = false)
    Tags tags;
	
	@ManyToOne
	@JoinColumn(name = "PostNumber", insertable = false, updatable = false)
	Posts postNumber;
	
	

}
