package in.co.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.common.BaseServiceImpl;
import in.co.dao.UserDAOInt;
import in.co.dto.UserDTO;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserDTO, UserDAOInt> implements UserServiceInt {

	@Autowired
	public UserDAOInt userDAO;

	@Override
	public UserDTO authenticate(String loginId, String password) {
		UserDTO dto = findByLoginId(loginId);
		if (dto != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(password, dto.getPassword())) {
				return dto;
			}
		}
		return null;
	}

	@Override
	public UserDTO findByLoginId(String loginId) {

		return userDAO.findByEmail("loginId", loginId);
	}

	@Override
	public UserDTO findByEmail(String loginId) {
		return userDAO.findByEmail("loginId", loginId);

	}

}
