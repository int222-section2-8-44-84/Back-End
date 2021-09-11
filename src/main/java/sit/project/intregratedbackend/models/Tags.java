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

@Entity
@Table(name = "Tags")
public class Tags {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TagID")
	private int tagId;
	
	@Column(name = "TagName")
	private String tagName;
	
	@OneToMany(mappedBy = "Tags", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<Posts_has_Tags> postsTag;

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
