package in.co.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class BaseDAOImpl<T extends BaseDTO> implements BaseDAOInt<T> {

	@PersistenceContext
	public EntityManager entityManager;

	protected abstract List<Predicate> getWhereClause(T dto, CriteriaBuilder builder, Root qRoot);

	@Override
	public long add(T dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(T dto) {
		entityManager.merge(dto);
	}

	@Override
	public void delete(T dto) {

		entityManager.remove(dto);
	}

	@Override
	public List search(T dto, int pageNo, int pageSize) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(getDTOClass());
		Root<T> qRoot = cq.from(getDTOClass());
		List<Predicate> whereClause = getWhereClause(dto, builder, qRoot);
		cq.where(whereClause.toArray(new Predicate[whereClause.size()]));
		TypedQuery<T> query = entityManager.createQuery(cq);
		if (pageSize > 0) {
			query.setFirstResult(pageNo * pageSize);
			query.setMaxResults(pageSize);
		}
		List list = query.getResultList();
		return list;
	}

	public T findByPk(Long id) {

		T dto = entityManager.find(getDTOClass(), id);
		System.out.println("basedao findbypk id =" + dto);
		return dto;
	}

	public T findByUniqueKey(String attribute, Object val) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(getDTOClass());
		Root<T> qRoot = cq.from(getDTOClass());
		Predicate condition = builder.equal(qRoot.get(attribute), val);
		cq.where(condition);
		TypedQuery<T> tq = entityManager.createQuery(cq);
		List<T> list = tq.getResultList();
		T dto = null;
		if (list.size() > 0) {
			dto = list.get(0);
		}
		return dto;

	}

	protected boolean isEmptyString(String val) {
		return val == null || val.trim().length() == 0;
	}

	public abstract Class<T> getDTOClass();

}
