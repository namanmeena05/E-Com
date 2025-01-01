package in.co.service;

import in.co.common.BaseServiceInt;
import in.co.dto.WishlistDTO;
import java.util.List;

public interface WishlistServiceInt extends BaseServiceInt<WishlistDTO> {
    List<WishlistDTO> findWishlistByUserId(Long userId);

    void removeWishlistItem(Long userId, Long productId);
}
