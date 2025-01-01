package in.co.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import in.co.common.BaseDTO;
import in.co.common.BaseForm;
import in.co.dto.ProductDTO;
import in.co.dto.WishlistDTO;

public class WishlistForm extends BaseForm {

	private Long userId; // The user who owns the wishlist
	private Long productId; // Product ID passed from the frontend
	private Date dateAdded; // Date when the product was added to the wishlist

	// Getters and Setters for form fields
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

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	// Convert Form to DTO
	@Override
	public BaseDTO getDTO() {
		WishlistDTO dto = initDTO(new WishlistDTO());
		dto.setUserId(userId); // Set userId
		dto.setDateAdded(dateAdded); // Set the date added
		return dto; // Product will be set later in the controller
	}
}
