package in.co.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.common.BaseServiceImpl;
import in.co.dao.ShoppingCartDAOInt;
import in.co.dto.ShoppingCartDTO;

@Service
@Transactional
public class ShoppingCartServiceImpl extends BaseServiceImpl<ShoppingCartDTO, ShoppingCartDAOInt>
		implements ShoppingCartServiceInt {

	@Override
	public List<ShoppingCartDTO> getCartItemsByUser(Long userId) {

		return baseDao.findByUserId(userId);
	}

	@Override
	public ShoppingCartDTO getCartItemByProduct(Long productId) {
		return baseDao.findByProductId(productId);
	}

}
