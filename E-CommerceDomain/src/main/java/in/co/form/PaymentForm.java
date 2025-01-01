package in.co.form;

import in.co.common.BaseDTO;
import in.co.common.BaseForm;
import in.co.dto.PaymentDTO;

public class PaymentForm extends BaseForm {

	private Long userId;
    private Double amount;
    private String paymentStatus;
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
	
	@Override
	public BaseDTO getDTO() {
	PaymentDTO dto =	initDTO(new PaymentDTO());
	dto.setAmount(amount);
	dto.setPaymentStatus(paymentStatus);
	dto.setUserId(userId);
		return dto;
	}
    
    
}
