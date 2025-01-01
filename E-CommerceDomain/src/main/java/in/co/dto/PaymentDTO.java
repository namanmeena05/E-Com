package in.co.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import in.co.common.BaseDTO;
@Entity
@Table(name = "PAYMENT_DTO")
public class PaymentDTO extends BaseDTO {
	
	private Long userId;
    private Double amount;
    private String paymentStatus;


	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
