package global.sesoc.sebank.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.sebank.vo.Customer;

@Repository
public class CustomerRepository {

	@Autowired
	SqlSession sqlsession;

	public int register(Customer c) {
		int result = 0;
		CustomerMapper dao = sqlsession.getMapper(CustomerMapper.class);
		try {
			result = dao.register(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Customer selectOne(String custid) {
		Customer c = null;
		CustomerMapper dao = sqlsession.getMapper(CustomerMapper.class);
		try {
			c = dao.selectOne(custid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public int update(Customer c) {
		int result = 0;
		CustomerMapper dao = sqlsession.getMapper(CustomerMapper.class);
		try {
			result = dao.update(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
