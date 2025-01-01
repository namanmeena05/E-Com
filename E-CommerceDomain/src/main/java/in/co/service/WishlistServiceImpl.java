package in.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.common.BaseServiceImpl;
import in.co.dao.WishlistDAOInt;
import in.co.dto.WishlistDTO;

@Service
@Transactional
public class WishlistServiceImpl extends BaseServiceImpl<WishlistDTO, WishlistDAOInt> implements WishlistServiceInt {

	@Autowired
    private WishlistDAOInt wishlistDao;

    @Override
    @Transactional(readOnly = true)
    public List<WishlistDTO> findWishlistByUserId(Long userId) {
        return wishlistDao.findByUserId(userId);
    }

    @Override
    @Transactional
    public void removeWishlistItem(Long userId, Long productId) {
        wishlistDao.deleteByUserIdAndProductId(userId, productId);
    }

}
