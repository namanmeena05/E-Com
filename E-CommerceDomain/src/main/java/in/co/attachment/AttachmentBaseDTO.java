package in.co.attachment;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import in.co.common.BaseDTO;

@MappedSuperclass
public class AttachmentBaseDTO extends BaseDTO{
	
	@Column(name = "NAME",length = 50)
	protected String name;
	
	
	@Column(name = "TYPE",length = 50)
	protected String type;

	
	
	@Column(name = "DESCRIPTION",length = 50)
	protected String description;

	
	@Column(name = "USER_ID",length = 50)
	protected Long userId;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}