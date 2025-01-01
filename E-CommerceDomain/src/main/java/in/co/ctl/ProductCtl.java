package in.co.ctl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.co.attachment.AttachmentDTO;
import in.co.attachment.AttachmentServiceInt;
import in.co.common.BaseCtl;
import in.co.common.DropdownListInt;
import in.co.common.ORSResponse;
import in.co.dto.ProductDTO;
import in.co.form.ProductForm;
import in.co.service.ProductServiceInt;

@RestController
@RequestMapping(value = "Product")
public class ProductCtl extends BaseCtl<ProductForm, ProductDTO, ProductServiceInt> {

	@Autowired
	public ProductServiceInt service;

	@Autowired
	public AttachmentServiceInt as;

//	@GetMapping("/preload")
//	public ORSResponse preload() {
//		ORSResponse res = new ORSResponse(true);
//		ProductDTO dto = new ProductDTO();
//		List categories = service.search(dto, 0, 10);
//		List price = service.search(dto, 0, 10);
//		res.addResult("categories", categories);
//		res.addResult("price", price);
//		return res;
//
//	}
	
	@GetMapping("/preload")
	public ORSResponse preload() {
	    ORSResponse res = new ORSResponse(true);
	    List<ProductDTO> products = service.search(new ProductDTO(), 0, 10);

	    // Extract distinct categories and prices from the product list
	    Set<String> categories = products.stream()
	                                     .map(ProductDTO::getCategory)
	                                     .filter(Objects::nonNull)
	                                     .collect(Collectors.toSet());

	    Set<BigDecimal> prices = products.stream()
	                                      .map(ProductDTO::getPrice)
	                                      .filter(Objects::nonNull)
	                                      .collect(Collectors.toSet());

	    res.addResult("categories", categories);
	    res.addResult("price", prices);
	    return res;
	}


	@PostMapping("profile/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {
		ProductDTO dto = baseService.findByPk(userId);
		System.out.println("Inside upload pic DTO =" + dto);
		AttachmentDTO doc = new AttachmentDTO(file);
		doc.setDescription("picture");
		doc.setUserId(userId);
		if (dto.getImageId() != null && dto.getImageId() > 0) {
			doc.setId(dto.getImageId());
		}
		Long imageId = as.save(doc);
		System.out.println("upload "+ doc);
		if (dto.getImageId() == null) {
			dto.setImageId(imageId);
			baseService.update(dto);
		}
		ORSResponse res = new ORSResponse(true);
		res.addResult("imageId", imageId);
		return res;

	}

	@GetMapping("profile/{userId}")
	public @ResponseBody void download(@PathVariable Long userId, HttpServletResponse res) {
		ProductDTO dto = baseService.findByPk(userId);
		AttachmentDTO attachmentDTO = as.findByPk(dto.getImageId());
		try {
			if (attachmentDTO != null) {
				res.setContentType(attachmentDTO.getType());
				OutputStream out = res.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}