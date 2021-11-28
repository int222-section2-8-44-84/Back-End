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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Posts")
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
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
	private java.sql.Timestamp postTime;

	@Column(name = "ImageName")
	private String imageName;
	
	@Column(name = "PosterName")
	private String posterName;

	@Column(name = "AccountNumber")
	private int accountNumber;

	@Column(name = "CategoryID")
	private int categoryId;

	@OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<Posts_has_Tags> postTags;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<FeelToPost> feelToPost;

	@ManyToOne
	@JoinColumn(name = "AccountNumber", insertable = false, updatable = false)
	AuthenticationUser account;

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

	public java.sql.Timestamp getPostTime() {
		return postTime;
	}

	public void setPostTime(java.sql.Timestamp postTime) {
		this.postTime = postTime;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Set<Posts_has_Tags> getPostTags() {
		return postTags;
	}

	public void setPostTags(Set<Posts_has_Tags> postTags) {
		this.postTags = postTags;
	}

	public Categories getCategories() {
		return categories;
	}

	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public void setAll(Posts post) {
		this.setPostTitle(post.postTitle);
		this.setFood(post.food);
		this.setRestaurant(post.restaurant);
		this.setFoodPrice(post.foodPrice);
		this.setDescription(post.description);
		this.setReviewRate(post.reviewRate);
		this.setPostTime(post.postTime);
		this.setImageName(post.imageName);
		this.setPosterName(post.posterName);
		this.setAccountNumber(post.accountNumber);
		this.setCategoryId(post.categoryId);
	}

}
