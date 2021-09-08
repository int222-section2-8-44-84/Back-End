package sit.project.intregratedbackend.models;

import java.sql.Date;
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
	
	@Column(name = "Food")
	private String food;
	
	@Column(name = "Restaurant")
	private String restaurant;
	
	@Column(name = "FoodPrice")
	private double foodPrice;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "ReviewRate")
	private int reviewRate;
	
	@Column(name = "PostTime")
	private Date postTime;
	
	@Column(name = "Accounts_UserNumber")
	private int accounts_UserNumber;
	
	@Column(name = "Categories_CategoryID")
	private int categories_CategoryId;
	
	@OneToMany(mappedBy = "Posts_PostNumber", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<Posts_has_Tags> posts_Number ;
	
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

	public void setFoodPrice(double foodPrice) {
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

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public int getAccounts_UserNumber() {
		return accounts_UserNumber;
	}

	public void setAccounts_UserNumber(int accounts_UserNumber) {
		this.accounts_UserNumber = accounts_UserNumber;
	}

	public int getCategories_CategoryId() {
		return categories_CategoryId;
	}

	public void setCategories_CategoryId(int categories_CategoryId) {
		this.categories_CategoryId = categories_CategoryId;
	}
	
	
}
