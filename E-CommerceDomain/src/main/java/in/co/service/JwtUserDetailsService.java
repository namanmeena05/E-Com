package in.co.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.co.common.BaseServiceImpl;
import in.co.dao.UserDAOInt;
import in.co.dto.UserDTO;

@Service
public class JwtUserDetailsService extends BaseServiceImpl<UserDTO, UserDAOInt> implements UserDetailsService {

	@Autowired
	private UserDAOInt userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = userDao.findByUniqueKey("loginId", username);
		if(user == null) {
			throw new  UsernameNotFoundException("user not found with this username" + username);
		}
		 return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(),
				new ArrayList<>());
	}

}
