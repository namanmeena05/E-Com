package in.co.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import in.co.common.BaseForm;

public class LoginForm extends BaseForm{

	@NotEmpty(message = "Please enter loginId")
	private String loginId;
	
	@NotEmpty(message = "Please enter password")
	private String password;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
