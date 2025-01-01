package in.co.ctl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.common.BaseCtl;
import in.co.common.ORSResponse;
import in.co.dto.ProductDTO;
import in.co.dto.ShoppingCartDTO;
import in.co.form.ShoppingCartForm;
import in.co.service.ProductServiceInt;
import in.co.service.ShoppingCartServiceInt;

@RestController
@RequestMapping(value = "Cart")
public class ShoppingCartCtl extends BaseCtl<ShoppingCartForm, ShoppingCartDTO, ShoppingCartServiceInt> {

	@Autowired
	public ProductServiceInt productService;

	@PostMapping("/add")
	public ORSResponse addToCart(@RequestBody ShoppingCartForm form) {
		// Convert form to DTO
		ShoppingCartDTO dto = (ShoppingCartDTO) form.getDTO();
		Long userId = form.getUserId();
		Long productId = form.getProductId();
		ORSResponse res = new ORSResponse();

		// Fetch product details by productId
		ProductDTO product = productService.findByPk(dto.getProductId());
		if (product == null) {
			res.setSuccess(false);
			res.addMessage("Product not found");
			return res;
		}

		// Set product-related fields in ShoppingCartDTO
		dto.setPrice(product.getPrice());
		dto.setProductName(product.getName());
		dto.setUserId(userId);
		dto.setImageId(product.getImageId());

		// Calculate total price (price * quantity)
		dto.setTotalPrice(dto.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())));

		// Get existing cart items for the user
		ShoppingCartDTO existingCartItem =  baseService.getCartItemByProduct(productId);

		// If the product already exists in the cart, update quantity and total price
		if (existingCartItem != null && existingCartItem.getUserId().equals(dto.getUserId())) {
			// Update quantity and total price
			existingCartItem.setQuantity(existingCartItem.getQuantity() + dto.getQuantity());
			existingCartItem.setTotalPrice(
					existingCartItem.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
			baseService.update(existingCartItem); // Update existing cart item
			res.addMessage("Quantity updated");
			res.setSuccess(true);
			res.addData(existingCartItem.getId());
		} else {
			// Add new product to the cart
			baseService.add(dto); // Add new cart item
			res.addMessage("Product added to the cart");
			res.setSuccess(true);
			res.addData(dto);
		}

		return res;
	}

	@GetMapping("/getCartItems/{userId}")
	public ORSResponse getCartItemsByUser(@PathVariable Long userId) {
		System.out.println("userId ===" + userId);
		List<ShoppingCartDTO> cartItems = baseService.getCartItemsByUser(userId);
		ORSResponse res = new ORSResponse(true);
		if (cartItems != null ) {
			res.addData(cartItems);
		} else {
			res.setSuccess(false);
			res.addMessage("No items found for this user.");
		} 
		return res;
	}

	@GetMapping("/product/{productId}")
	public ORSResponse getCartItemByProduct(@PathVariable Long productId) {
		ShoppingCartDTO cartItem = baseService.getCartItemByProduct(productId);
		ORSResponse res = new ORSResponse(true);
		if (cartItem != null) {
			res.addData(cartItem);
		} else {
			res.setSuccess(false);
			res.addMessage("No item found for this product.");
		}
		return res;
	}

}
