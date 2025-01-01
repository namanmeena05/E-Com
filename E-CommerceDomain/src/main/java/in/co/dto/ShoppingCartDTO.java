package in.co.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import in.co.common.BaseDTO;

@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCartDTO extends BaseDTO {

	private Long cartId;
	private Long userId;
	private Long productId;
	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	private String productName;
	private Long imageId; // Changed to String, assuming image URL or ID
	private Integer quantity;
	private BigDecimal price;
	private BigDecimal totalPrice; // Computed field
	private Date addedDate;
	private boolean stockAvailability; // Optional field for stock checks
	private boolean checkoutStatus; // To track whether the item is checked out

	// Getters and Setters
	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public boolean isStockAvailability() {
		return stockAvailability;
	}

	public void setStockAvailability(boolean stockAvailability) {
		this.stockAvailability = stockAvailability;
	}

	public boolean isCheckoutStatus() {
		return checkoutStatus;
	}

	public void setCheckoutStatus(boolean checkoutStatus) {
		this.checkoutStatus = checkoutStatus;
	}

	@Override
	public String getValue() {
		return productName;
	}
}
