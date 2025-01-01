package in.co.common;

import java.util.List;

public interface BaseDAOInt<T extends BaseDTO> {

	public long add(T dto);

	public void update(T dto);

	public void delete(T dto);

	public T findByPk(Long pk);

	public List search(T dto, int pageNo, int pageSize);

	public T findByUniqueKey(String attribute, Object val);

}
