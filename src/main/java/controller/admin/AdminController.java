package controller.admin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import entity.TKNV;
@Transactional
@Controller
@RequestMapping(value="/")
public class AdminController {
	@Autowired
	SessionFactory factory;

	@RequestMapping(value="admin")
	public String admin(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("role") == null) {
			return "redirect:admin/login.htm";
		}
		
		return "admin/admin";
	}
	@RequestMapping(value="admin/login")
	public String login(HttpSession session, ModelMap model) {
		if (session.getAttribute("role") != null) {
			return "redirect:/admin.htm";
		}
		return "admin/login";
	}
	
public TKNV loginAuth(String username, String passwd) {
		
		Session session = factory.getCurrentSession(); 
		String hql="FROM TKNV u WHERE u.TK = :username";
		Query qr = session.createQuery(hql);
		TKNV u = (TKNV) qr.setParameter("username",username).uniqueResult();
		if (u != null && u.getMK().equals(passwd)) {
            return u;
        }
		return null;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("username_login") String username,
			@RequestParam("password_login") String password, HttpSession session) {
		
		TKNV tk = loginAuth(username, password);	
		if (tk != null) {
			//System.out.println(tk.getTK());
			//System.out.println(tk.getNhanvien().getChucvu().getMACV());
			session.setAttribute("role", tk.getNhanvien().getChucvu().getMACV());
			//System.out.println(session.getAttribute("role").toString());	
			return "redirect:admin.htm";
		} 
		else {
			String message = "Thông tin tài khoản không đúng!";
			session.setAttribute("message", message);
		}	
		return "login";
	}

	@RequestMapping(value = "logoutAD")
	public String LoginAccount(HttpSession session, HttpServletRequest request) {
		session.removeAttribute("LoginInfo");
		return "index";
	}

}
