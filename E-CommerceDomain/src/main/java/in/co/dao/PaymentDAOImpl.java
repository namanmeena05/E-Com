package in.co.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import in.co.common.BaseDAOImpl;
import in.co.dto.PaymentDTO;
@Repository
public class PaymentDAOImpl extends BaseDAOImpl<PaymentDTO> implements PaymentDAOInt{

	@Override
	protected List<Predicate> getWhereClause(PaymentDTO dto, CriteriaBuilder builder, Root qRoot) {
		return null;
	}

	@Override
	public Class<PaymentDTO> getDTOClass() {
		return PaymentDTO.class;
	}

}
