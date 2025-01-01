package in.co.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import in.co.common.BaseDAOImpl;
import in.co.dto.WishlistDTO;

@Repository
public class WishlistDAOImpl extends BaseDAOImpl<WishlistDTO> implements WishlistDAOInt {

	@Override
	protected List<Predicate> getWhereClause(WishlistDTO dto, CriteriaBuilder builder, Root qRoot) {
		return null;
	}

	@Override
	public Class<WishlistDTO> getDTOClass() {
		return WishlistDTO.class;
	}

	@Override
	public List<WishlistDTO> findByUserId(Long userId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WishlistDTO> query = builder.createQuery(WishlistDTO.class);
        Root<WishlistDTO> root = query.from(WishlistDTO.class);
        query.select(root).where(builder.equal(root.get("userId"), userId));
        return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void deleteByUserIdAndProductId(Long userId, Long productId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WishlistDTO> query = builder.createQuery(WishlistDTO.class);
        Root<WishlistDTO> root = query.from(WishlistDTO.class);
        query.select(root).where(builder.equal(root.get("userId"), userId),
                                 builder.equal(root.get("productId"), productId));
        WishlistDTO wishlistItem = entityManager.createQuery(query).getSingleResult();
        entityManager.remove(wishlistItem);
		
	}

}
