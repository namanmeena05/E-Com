package in.co.ctl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.common.BaseCtl;
import in.co.common.ORSResponse;
import in.co.dto.PaymentDTO;
import in.co.form.PaymentForm;
import in.co.service.PaymentServiceInt;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "Payment")
public class PaymentCtl extends BaseCtl<PaymentForm, PaymentDTO, PaymentServiceInt> {

	@PostMapping("create")
	public ORSResponse createPayment(@RequestBody PaymentForm form) {

	ORSResponse response = new ORSResponse(true);

		PaymentDTO dto = (PaymentDTO) form.getDTO();
		dto.setPaymentStatus("PENDING"); // Default status
		long id = baseService.save(dto);
		response.addData(id);
		return response;
	}


    public PaymentCtl() {
        Stripe.apiKey = "your-secret-key";  // Stripe Secret key
    }

    @PostMapping("/createCheckoutSession")
    public Map<String, Object> createCheckoutSession(@RequestBody Map<String, Object> paymentData) throws StripeException {
        SessionCreateParams params = SessionCreateParams.builder()
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl("http://localhost:4200/success?session_id={CHECKOUT_SESSION_ID}")
            .setCancelUrl("http://localhost:4200/cancel")
            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
            .addLineItem(SessionCreateParams.LineItem.builder()
                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                    .setCurrency("usd")
                    .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName("Your Order")
                        .build())
                    .setUnitAmount((long) ((double) paymentData.get("amount") * 100))  // Convert to cents
                    .build())
                .setQuantity(1L) 
                .build())
            .build();

        Session session = Session.create(params);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("success", true);
        responseData.put("sessionId", session.getId());
        return responseData;
    }
}
