package sit.project.intregratedbackend.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Posts")
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PostNumber")
	private int postNumber;
	
	@Column(name = "PostTitle")
	private String postTitle;
	
	@Column(name = "Food")
	private String food;
	
	@Column(name = "Restaurant")
	private String restaurant;
	
	@Column(name = "FoodPrice")
	private float foodPrice;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "ReviewRate")
	private int reviewRate;
	
	@Column(name = "PostTime")
	private java.sql.Date postTime;
	
	@Column(name = "ImageName")
	private String imageName;
	
	@Column(name = "UserNumber")
	private int userNumber;
	
	@Column(name = "CategoryID")
	private int categoryId;
	
	@OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<Posts_has_Tags> postTags;
	
	@ManyToOne
    @JoinColumn(name = "UserNumber", insertable = false, updatable = false)
    Accounts account;
	
	@ManyToOne
	@JoinColumn(name = "CategoryID", insertable = false, updatable = false)
	Categories categories;

	public int getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public double getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(float foodPrice) {
		this.foodPrice = foodPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReviewRate() {
		return reviewRate;
	}

	public void setReviewRate(int reviewRate) {
		this.reviewRate = reviewRate;
	}

	public java.sql.Date getPostTime() {
		return postTime;
	}

	public void setPostTime(java.sql.Date postTime) {
		this.postTime = postTime;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


}
