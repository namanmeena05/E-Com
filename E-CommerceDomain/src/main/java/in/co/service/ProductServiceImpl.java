package in.co.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.common.BaseServiceImpl;
import in.co.dao.ProductDAOInt;
import in.co.dto.ProductDTO;

@Service
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<ProductDTO, ProductDAOInt> implements ProductServiceInt {

}
