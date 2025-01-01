package in.co.ctl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.co.common.BaseCtl;
import in.co.common.ORSResponse;
import in.co.config.JwtTokenUtil;
import in.co.dto.UserDTO;
import in.co.form.LoginForm;
import in.co.form.UserForm;
import in.co.service.UserServiceInt;

@RestController
@RequestMapping(value = "Auth")
public class LoginCtl extends BaseCtl<LoginForm, UserDTO, UserServiceInt> {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtService;

	@PostMapping("login")
	public ORSResponse Authenticate(@RequestBody @Valid LoginForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		}
		UserDTO dto = baseService.authenticate(form.getLoginId(), form.getPassword());
		if (dto == null) {
			res.setSuccess(false);
			res.addMessage("Invalide login credentials");
			return res;

		} else {
			res.setSuccess(true);
			res.addData(dto);

		}

		final UserDetails userDetails = jwtService.loadUserByUsername(form.getLoginId());
		final String token = jwtTokenUtil.generateToken(userDetails);
		res.addResult("token", token);
		res.addResult("name", dto.getFirstName());
		res.addResult("id", dto.getId());
		res.addResult("role", dto.getRole());
		return res;

	}

	@PostMapping("/register")
	public ORSResponse register(@RequestBody @Valid UserForm form, BindingResult bindingResult) {
		ORSResponse res = validate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = (UserDTO) form.getDTO();
		// Hash the password using BCrypt
		String hashedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
		dto.setPassword(hashedPassword);

		baseService.add(dto);
		res.addMessage("Registration successful!");
		return res;

	}
//	@GetMapping("login")
//	public ORSResponse authenticate(@RequestParam("loginId") String loginId,
//	                                 @RequestParam("password") String password) {
//
//	    ORSResponse res = new ORSResponse();
//	    
//	    // Manual validation
//	    if (loginId == null || loginId.isEmpty() || password == null || password.isEmpty()) {
//	        res.setSuccess(false);
//	      //  res.setMessage("Login ID and password are required.");
//	        return res;
//	    }
//	    
//	    // Authenticate user
//	    UserDTO dto = baseService.authenticate(loginId, password);
//	    if (dto == null) {
//	        res.setSuccess(false);
//	      //  res.setMessage("Authentication failed.");
//	    } else {
//	        res.setSuccess(true);
//	        res.addData(dto);
//
//	        // Generate token
//	        final UserDetails userDetails = jwtService.loadUserByUsername(loginId);
//	        final String token = jwtTokenUtil.generateToken(userDetails);
//	        res.addResult("token", token);
//	    }
//
//	    return res;
//	}

}
