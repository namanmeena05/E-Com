package in.co.ctl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.common.BaseCtl;
import in.co.common.ORSResponse;
import in.co.dto.UserDTO;
import in.co.form.UserForm;
import in.co.service.UserServiceInt;

@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt> {

	@GetMapping("logout")
	ORSResponse logout(HttpServletRequest req, HttpServletResponse resp) {
		ORSResponse res = new ORSResponse(true);
		HttpSession session = req.getSession();
		session.invalidate();
		res.addMessage("Successfully Logout");

		return res;

	}
	
	@PostMapping("/register")
	public ORSResponse register(@RequestBody @Valid UserForm form, BindingResult bindingResult) {
	    ORSResponse res = validate(bindingResult);
	    if (!res.isSuccess()) {
	        return res;
	    }

	    UserDTO dto = (UserDTO) form.getDTO();
	    System.out.println(form.getRole());
	    
	    // Dynamically assign roles based on input or custom logic
	    if (form.getRole() != null && form.getRole().equalsIgnoreCase("ADMIN")) {
	        dto.setRole("ADMIN");
	    } else {
	        dto.setRole("USER");
	    }

	    baseService.add(dto);
	    res.addMessage("Registration successful!");
	    return res;
	}


}
