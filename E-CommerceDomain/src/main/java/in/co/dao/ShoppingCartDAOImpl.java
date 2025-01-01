package in.co.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import in.co.common.BaseDAOImpl;
import in.co.dto.ShoppingCartDTO;

@Repository
public class ShoppingCartDAOImpl extends BaseDAOImpl<ShoppingCartDTO> implements ShoppingCartDAOInt {

	@Override
	public List<ShoppingCartDTO> findByUserId(Long userId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ShoppingCartDTO> cq = builder.createQuery(getDTOClass());
		Root<ShoppingCartDTO> qRoot = cq.from(getDTOClass());
		Predicate condition = builder.equal(qRoot.get("userId"), userId);
		cq.where(condition);
		TypedQuery<ShoppingCartDTO> query = entityManager.createQuery(cq);
		List<ShoppingCartDTO> list = query.getResultList();
//		ShoppingCartDTO dto = null;
//		if (list.size() > 0) {
//			dto = list.get(0);
//		}
		return list;
	}

	@Override
	public ShoppingCartDTO findByProductId(Long productId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ShoppingCartDTO> cq = builder.createQuery(getDTOClass());
		Root<ShoppingCartDTO> qRoot = cq.from(getDTOClass());
		Predicate condition = builder.equal(qRoot.get("productId"), productId);
		cq.where(condition);
		TypedQuery<ShoppingCartDTO> query = entityManager.createQuery(cq);
		List<ShoppingCartDTO> resultList = query.getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@Override
	protected List<Predicate> getWhereClause(ShoppingCartDTO dto, CriteriaBuilder builder, Root qRoot) {
		List<Predicate> predicates = new ArrayList<>();

		if (dto.getUserId() != null) {
			predicates.add(builder.equal(qRoot.get("userId"), dto.getUserId()));
		}
		if (dto.getProductId() != null) {
			predicates.add(builder.equal(qRoot.get("productId"), dto.getProductId()));
		}
		// Add more conditions based on the properties of ShoppingCartDTO

		return predicates;
	}

	@Override
	public Class<ShoppingCartDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return ShoppingCartDTO.class;
	}

}
