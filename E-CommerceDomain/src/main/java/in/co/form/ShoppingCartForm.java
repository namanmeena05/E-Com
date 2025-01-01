package in.co.form;

import javax.validation.constraints.NotNull;

import in.co.common.BaseDTO;
import in.co.common.BaseForm;
import in.co.dto.ShoppingCartDTO;

import java.math.BigDecimal;

public class ShoppingCartForm extends BaseForm {

	@NotNull
	private Long userId;
	@NotNull
	private Long productId;
	@NotNull
	private Integer quantity;
	@NotNull
	private BigDecimal price;

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

	@Override
	public BaseDTO getDTO() {
		ShoppingCartDTO dto = initDTO(new ShoppingCartDTO());
		dto.setUserId(userId);
		dto.setProductId(productId);
		dto.setQuantity(quantity);
		dto.setPrice(price);
		
		return dto;
	}
}
