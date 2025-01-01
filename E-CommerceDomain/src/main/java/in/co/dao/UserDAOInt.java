package in.co.dao;

import in.co.common.BaseDAOInt;
import in.co.dto.UserDTO;

public interface UserDAOInt extends BaseDAOInt<UserDTO> {

	public UserDTO findByEmail(String attribute, String val);
}
