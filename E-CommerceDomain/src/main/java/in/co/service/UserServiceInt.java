package in.co.service;

import in.co.common.BaseServiceInt;
import in.co.dto.UserDTO;

public interface UserServiceInt extends BaseServiceInt<UserDTO> {

	public UserDTO authenticate(String loginID, String password);

	public UserDTO findByLoginId(String loginId);
	
	public UserDTO findByEmail(String loginId);
}
