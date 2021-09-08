package sit.project.intregratedbackend.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Posts")
public class Posts {
	@Id
	@Column(name = "PostNumber")
	private int postNumber;
	
	@Column(name = "PostTiltle")
	private String postTitle;
	
	@Column(name = "FoodName")
	private String foodName;
	
	@Column(name = "RestaurantName")
	private String restaurantName;
	
	@Column(name = "FoodPrice")
	private double foodPrice;
	
	@Column(name = "PostDescription")
	private String postdescription;
	
	@Column(name = "ReviewRate")
	private int reviewRate;
	
	@Column(name = "PostTime")
	private java.sql.Date postTime;
	
	@Column(name = "CategoryID")
	private int categoryId;
	
	@Column(name = "UserNumber")
	private int userNumber;
	
	@OneToMany(mappedBy = "PostNumber", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<PostTag> postsNumber ;
	
	@ManyToOne
    @JoinColumn(name = "UserNumber", insertable = false, updatable = false)
    Accounts accountNumber;
	
	@ManyToOne
	@JoinColumn(name = "CategoryID", insertable = false, updatable = false)
	Categories categoriesId;

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

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public double getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}

	public String getPostdescription() {
		return postdescription;
	}

	public void setPostdescription(String postdescription) {
		this.postdescription = postdescription;
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
}
