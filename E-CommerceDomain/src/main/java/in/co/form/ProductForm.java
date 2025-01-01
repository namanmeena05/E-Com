package in.co.form;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.web.multipart.MultipartFile;

import in.co.common.BaseDTO;
import in.co.common.BaseForm;
import in.co.dto.ProductDTO;

import javax.validation.constraints.PastOrPresent;

public class ProductForm extends BaseForm {

    @NotEmpty(message = "Please enter the name")
    private String name;

    @NotEmpty(message = "Please enter the description")
    private String description;

    @NotNull(message = "Please enter the price")
    @Positive(message = "The price must be positive")
    private BigDecimal price;

    @NotNull(message = "Please enter the date of purchase")
    @PastOrPresent(message = "The date of purchase must be in the past or present")
    private Date dateOfPurchase;

    @NotEmpty(message = "Please enter the category")
    private String category;
    
	private Long imageId;

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
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

    @Override
    public BaseDTO getDTO() {
        ProductDTO dto = initDTO(new ProductDTO());
        dto.setName(name);
        dto.setDescription(description);
        dto.setDateOfPurchase(dateOfPurchase);
        dto.setCategory(category);
        dto.setPrice(price);
        dto.setImageId(imageId);
        return dto;
    }
}
