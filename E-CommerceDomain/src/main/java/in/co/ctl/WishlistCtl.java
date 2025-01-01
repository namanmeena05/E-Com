package in.co.ctl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import in.co.common.BaseCtl;
import in.co.dto.ProductDTO;
import in.co.dto.WishlistDTO;
import in.co.form.WishlistForm;
import in.co.service.ProductServiceInt;
import in.co.service.WishlistServiceInt;
import in.co.common.ORSResponse;

@RestController
@RequestMapping(value = "Wish")
public class WishlistCtl extends BaseCtl<WishlistForm, WishlistDTO, WishlistServiceInt> {

    @Autowired
    private ProductServiceInt productService;  // Autowire ProductService to fetch product details

    @PostMapping("/addToWishlist")
    public ORSResponse addToWishlist(@RequestBody WishlistForm form) {
        ORSResponse res = new ORSResponse();

        // Fetch the product details using productId from the form
        ProductDTO product = productService.findByPk(form.getProductId());
        if (product == null) {
            res.setSuccess(false);
            res.addMessage("Product not found");
            return res;
        }

        // Check if the product is already in the user's wishlist
        List<WishlistDTO> existingWishlist = baseService.findWishlistByUserId(form.getUserId());
        boolean alreadyInWishlist = existingWishlist.stream()
                .anyMatch(w -> w.getProduct().getId().equals(product.getId()));

        if (alreadyInWishlist) {
            res.setSuccess(false);
            res.addMessage("Product is already in the wishlist");
        } else {
            // Convert form to WishlistDTO
            WishlistDTO wishlistDTO = (WishlistDTO) form.getDTO();
            
            // Set the product and related fields
            wishlistDTO.setProduct(product);  // Set the ProductDTO in WishlistDTO
            wishlistDTO.setDateAdded(new Date());  // Set the current date
            wishlistDTO.setImageId(product.getImageId());

            // Save the wishlist item
            baseService.add(wishlistDTO); 

            res.setSuccess(true);
            res.addMessage("Product added to the wishlist");
        }

        return res;
    }

    @GetMapping("/getWishlist/{userId}")
    public ORSResponse getWishlist(@PathVariable long userId) {
        ORSResponse res = new ORSResponse(true);
        try {
            List<WishlistDTO> wishlistItems = baseService.findWishlistByUserId(userId);
            res.addData(wishlistItems);
        } catch (Exception e) {
            res.setSuccess(false);
            res.addMessage("Unable to fetch wishlist for the user");
        }
        return res;
    }

    @PostMapping("/deleteWishlist/{productId}")
    public ORSResponse removeWishlistItem(@PathVariable long productId, @RequestParam long userId) {
        ORSResponse res = new ORSResponse(true);
        try {
            baseService.removeWishlistItem(userId, productId);
            res.addMessage("Product removed from wishlist successfully");
        } catch (Exception e) {
            res.setSuccess(false);
            res.addMessage("Error removing product from wishlist");
        }
        return res;
    }
}
