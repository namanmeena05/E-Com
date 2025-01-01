package in.co.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.common.BaseServiceImpl;
import in.co.dao.PaymentDAOInt;
import in.co.dto.PaymentDTO;

@Service
@Transactional
public class PaymentServiceImpl extends BaseServiceImpl<PaymentDTO, PaymentDAOInt> implements PaymentServiceInt {

}
