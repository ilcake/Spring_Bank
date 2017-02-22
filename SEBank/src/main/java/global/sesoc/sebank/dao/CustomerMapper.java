package global.sesoc.sebank.dao;

import global.sesoc.sebank.vo.Customer;

public interface CustomerMapper {

	public int register(Customer c) throws Exception;

	public Customer selectOne(String id) throws Exception;

	public int update(Customer c) throws Exception;

}
