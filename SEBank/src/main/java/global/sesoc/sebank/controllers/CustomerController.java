package global.sesoc.sebank.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import global.sesoc.sebank.dao.CustomerRepository;
import global.sesoc.sebank.vo.Customer;

@Controller
public class CustomerController {

	@Autowired
	CustomerRepository repo;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "customer/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String actionLogin(String custid, String password, HttpSession session, Model model) {
		System.out.println("custid=" + custid + "  //  password =" + password);
		String result = "";
		Customer c = repo.selectOne(custid);
		System.out.println("들어온 데이터는? : " + c);
		if (c != null && c.getPassword().equals(password)) {
			session.setAttribute("loginId", c.getCustid());
			session.setAttribute("loginName", c.getName());
			result = "redirect:/";
		} else {
			// 아이디가 틀렸을때
			model.addAttribute("errorMsg", "ID 또는 비밀번호가 틀렸습니다.");
			result = "customer/login";
		}

		return result;
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "customer/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String register(Customer c) {
		int result = repo.register(c);
		String what = "";
		if (result < 1) {
			what = "customer/join";
		} else {
			what = "redirect:/";
		}
		return what;
	}

	@RequestMapping(value = "/idcheck", method = RequestMethod.GET)
	public String idcheck() {
		return "customer/idcheck";
	}

	@RequestMapping(value = "/idcheck", method = RequestMethod.POST)
	public String idchecking(String searchId, Model model) {
		Customer c = repo.selectOne(searchId);
		System.out.println(c);
		model.addAttribute("searchId", searchId);
		if (c != null)
			model.addAttribute("searchResult", searchId);
		return "customer/idcheck";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateForm(HttpSession session, Model model) {
		Customer c = repo.selectOne((String) session.getAttribute("loginId"));
		model.addAttribute("customer", c);
		return "customer/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Customer c) {
		int result = 0;
		String where = "";
		result = repo.update(c);
		if (result < 1) {
			where = "customer/update";
		} else {
			where = "redirect:/";
		}

		return where;
	}
}
