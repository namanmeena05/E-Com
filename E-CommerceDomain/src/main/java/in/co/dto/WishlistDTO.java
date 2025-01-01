package in.co.dto;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import in.co.common.BaseDTO;

@Entity
@Table(name = "WISHLIST")
public class WishlistDTO extends BaseDTO {
	
	    @Column(name = "USER_ID")
	    private Long userId; // The user who owns the wishlist

	    @ManyToOne(fetch = FetchType.EAGER) // Fetch product details when the wishlist is queried
	    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
	    private ProductDTO product; // The product added to the wishlist

	    @Column(name = "DATE_ADDED")
	    private Date dateAdded; // Date when the product was added to the wishlist

	    // Additional attributes for better details
	    private String productName; // The name of the product
	    public Long getImageId() {
			return imageId;
		}

		public void setImageId(Long imageId) {
			this.imageId = imageId;
		}

		private Long imageId; // URL of the product image
	    private BigDecimal productPrice; // Price of the product

	    public Long getUserId() {
	        return userId;
	    }

	    public void setUserId(Long userId) {
	        this.userId = userId;
	    }

	    public ProductDTO getProduct() {
	        return product;
	    }

	    public void setProduct(ProductDTO product) {
	        this.product = product;
	        // Populate additional fields based on product details
	        if (product != null) {
	            this.productName = product.getName();
	            this.productPrice = product.getPrice();
	            
	        }
	    }

	    public Date getDateAdded() {
	        return dateAdded;
	    }

	    public void setDateAdded(Date dateAdded) {
	        this.dateAdded = dateAdded;
	    }

	    public String getProductName() {
	        return productName;
	    }


	    public BigDecimal getProductPrice() {
	        return productPrice;
	    }

	    @Override
	    public String getKey() {
	        return getId() + "";
	    }

	    @Override
	    public String getValue() {
	        return productName; // Return the product name for display purposes
	    }
	
}
