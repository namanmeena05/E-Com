package in.co.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import in.co.common.BaseDTO;

@Entity
@Table(name = "PRODUCT")
public class ProductDTO extends BaseDTO {

	@Column(name = "PD_NAME")
	private String name;

	@Lob
	@Column(name = "PD_DESCRIPTION")
	private String description;

	@Column(name = "PD_PRICE")
	private BigDecimal price; // Product price

	@Column(name = "PRICE_RANGE")
	private String priceRange;

	@Column(name = "PD_DATEOFPURCHASE")
	private Date dateOfPurchase;

	@Column(name = "PD_CATEGORY")
	private String category;

	@Column(name = "IMAGE_ID")
	private Long imageId; // If you store images in another table

	
	
	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	@Override
	public String getKey() {
		return getId() + "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return category;
	}

//    @Override
//    public String getValue() {
//        StringBuilder value = new StringBuilder();
//        if (category != null && !category.isEmpty()) {
//            value.append("Category: ").append(category);
//        }
//        if (price != null) {
//            if (value.length() > 0) {
//                value.append(", ");
//            }
//            value.append("Price: ").append(price);
//        }
//        return value.toString();
//    }

}
