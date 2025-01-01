package in.co.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import in.co.common.BaseDAOImpl;
import in.co.dto.UserDTO;

@Repository
public class UserDAOImpl extends BaseDAOImpl<UserDTO> implements UserDAOInt {

	public UserDTO findByEmail(String attribute, String val) {
		Class<UserDTO> dtoClass = getDTOClass();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		System.out.println(val + "----->>>>>userDao");
		CriteriaQuery<UserDTO> cq = builder.createQuery(dtoClass);

		Root<UserDTO> qRoot = cq.from(dtoClass);

		Predicate condition = builder.equal(qRoot.get(attribute), val);

		cq.where(condition);
		System.out.println(cq + "Query created in user dao");
		TypedQuery<UserDTO> query = entityManager.createQuery(cq);

		List<UserDTO> list = query.getResultList();
		System.out.println(list.get(0) + "list------");
		  if (list == null || list.isEmpty()) {
	            return null; // or throw a custom exception like UserNotFoundException
	        }
		UserDTO dto = null;

		if (list.size() > 0) {
			dto = list.get(0);
			System.out.println(dto.getLoginId() + "Login iid get from db in userDao");
		}
		System.out.println("going to return dto");
		return dto;

	}

	

	@Override
	protected List<Predicate> getWhereClause(UserDTO dto, CriteriaBuilder builder, Root qRoot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<UserDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return UserDTO.class;
	}


}
