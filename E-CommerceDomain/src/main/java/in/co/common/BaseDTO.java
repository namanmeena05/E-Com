package in.co.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseDTO implements DropdownListInt, Serializable, Comparable<BaseDTO> {

	@Id
	@GeneratedValue(generator = "nscPk")
	@GenericGenerator(name = "nscPk", strategy = "increment")
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	public int compareTo(BaseDTO next) {
		return getValue().compareTo(next.getValue());
	}

}
