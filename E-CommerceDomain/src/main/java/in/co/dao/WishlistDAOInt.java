package in.co.dao;

import in.co.common.BaseDAOInt;
import in.co.dto.WishlistDTO;
import java.util.List;

public interface WishlistDAOInt extends BaseDAOInt<WishlistDTO> {
    List<WishlistDTO> findByUserId(Long userId);

    void deleteByUserIdAndProductId(Long userId, Long productId);
}
