package in.co.dao;

import java.util.List;

import in.co.common.BaseDAOInt;
import in.co.dto.ShoppingCartDTO;

public interface ShoppingCartDAOInt extends BaseDAOInt<ShoppingCartDTO> {

	public List<ShoppingCartDTO> findByUserId(Long userId);

	public ShoppingCartDTO findByProductId(Long productId);
	
	


}
