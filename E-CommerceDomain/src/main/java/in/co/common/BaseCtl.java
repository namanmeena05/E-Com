package in.co.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseCtl<F extends BaseForm, T extends BaseDTO, S extends BaseServiceInt<T>> {

	private int pageSize = 5;

	@Autowired
	public S baseService;

	private static final Logger logger = LoggerFactory.getLogger(BaseCtl.class);

	public ORSResponse validate(BindingResult bindingResult) {
		ORSResponse res = new ORSResponse(true);
		if (bindingResult.hasErrors()) {
			res.setSuccess(false);
			Map<String, String> errors = new HashMap<>();
			List<FieldError> list = bindingResult.getFieldErrors();
			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});
			res.addInputError(errors);
		}
		return res;
	}

	@PostMapping("/save")
	public ORSResponse save(@RequestBody @Valid F form, BindingResult bindingResult) {
		ORSResponse res = validate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		}

		logger.info("Saving form: {}", form);
		System.out.println("Saving form: {}"+ form);

		T dto = (T) form.getDTO();

		if (dto.getId() != null && dto.getId() > 0) {
		    T dto1 = baseService.findByPk(dto.getId()); // Load entity only if ID exists
		    baseService.update(dto1);
		} else {
		    baseService.add(dto); // Create a new entity if ID is null or 0
		}
		res.addData(dto.getId());
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {
		ORSResponse res = new ORSResponse(true);
		T dto = baseService.findByPk(id);
		System.out.println("inside get ID=" + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	@PostMapping("/search/{pageNo}")
	public ORSResponse search(@RequestBody F form, @PathVariable int pageNo) {
		T dto = (T) form.getDTO();
		ORSResponse res = new ORSResponse(true);
		pageNo = (pageNo < 0) ? 0 : pageNo;

		res.addData(baseService.search(dto, pageNo, pageSize));
		List nextList = baseService.search(dto, pageNo + 1, pageNo);
		res.addResult("nextList", nextList);
		return res;

	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@RequestBody F from, @PathVariable String[] ids) {
		ORSResponse res = new ORSResponse(true);
		for (String id : ids) {
			T dto = baseService.findByPk(Long.parseLong(id));
			baseService.delete(dto);
		}
		res.setSuccess(true);
		res.addMessage("Data deleted Successfully");
		return res;

	}

}
