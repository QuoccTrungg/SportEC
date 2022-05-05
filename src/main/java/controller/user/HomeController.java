package controller.user;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entity.GioHang;
import entity.LoaiSP;
import entity.PhieuDat;
import entity.SanPham;
//import dao.UserDao;
//import entity.Cart;
import service.admin.AdminService;
//import service.user.CartService;
//import service.user.CategoryService;
//import service.user.ProductService;
import service.user.UserService;
@Transactional
@Controller
public class HomeController {
//	@Autowired
//	ProductService productService;
//	@Autowired
//	CategoryService categoryService;
//	@Autowired
//	UserService userService;
//	@Autowired
//	UserDao userDao;
//	@Autowired
//	AdminService adminService;
	@Autowired
	SessionFactory factory;
//	@Autowired
//	CartService cartService;
	
	
	@RequestMapping(value = "index")
	public String Index(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		GioHang gh = (GioHang)session.getAttribute("gioHang");
		if(gh == null) {
			gh= new GioHang();
			session.setAttribute("gioHang",gh);
		}
		model.addAttribute("cartCount", gh.getItems().size());
		

		model.addAttribute("all_category", getAllLoaiSP());
		model.addAttribute("all_product", getAllSP());
//		model.addAttribute("user", session.getAttribute("LoginInfo"));
		return "index";
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String load(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		GioHang gh = (GioHang)session.getAttribute("gioHang");
		if(gh == null) {
			gh= new GioHang();
			session.setAttribute("gioHang",gh);
		}
		model.addAttribute("cartCount", gh.getItems().size());
//		model.addAttribute("user", session.getAttribute("LoginInfo"));
		model.addAttribute("all_category", getAllLoaiSP());
		model.addAttribute("all_product", getAllSP());		
		return "index";
	}

	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public String checkout(HttpSession session,ModelMap model, HttpServletRequest request) {
			session = request.getSession();
		model.addAttribute("all_category", getAllLoaiSP());
		GioHang gh = (GioHang)session.getAttribute("gioHang");
		if(gh == null) {
			gh= new GioHang();
			session.setAttribute("gioHang",gh);
		}
		model.addAttribute("cartCount", gh.getItems().size());

//		model.addAttribute("user", session.getAttribute("LoginInfo"));
		if (session.getAttribute("LoginInfo") == null) {
			return "redirect:/login_register.htm";
		}
		return "checkout";
	}
	///// function
	
	public List<LoaiSP> getAllLoaiSP() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM LoaiSP";
			Query query = session.createQuery(hql);
			List<LoaiSP> list = query.list();
			return list;

		} catch (Exception e) {

			return null;
		}
	}
	
	public List<SanPham> getAllSP() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM SanPham WHERE SOLUONG!=0 AND TINHTRANG=True";
			Query query = session.createQuery(hql);
			List<SanPham> list = query.list();
			return list;

		} catch (Exception e) {
			return null;
		}
	}
	/////
}
