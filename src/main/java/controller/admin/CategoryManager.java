package controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import entity.LoaiSP;


@Transactional
@Controller
@RequestMapping("/admin")
public class CategoryManager {

	@Autowired
	SessionFactory factory;
	
	public List<LoaiSP> getAllCategory() {
			Session session = factory.getCurrentSession();
			String hql = "FROM LoaiSP";
			Query query = session.createQuery(hql);
			List<LoaiSP> list = query.list();
			return list;
	}
	
	@RequestMapping("category_manager")
	public String dataTable(Model model) {
		model.addAttribute("view_category", getAllCategory());
		return "admin/category-manager/manager";
	}

	@RequestMapping(value = "/addCat", method = RequestMethod.GET)
	public String insertCategory(Model model) {
		model.addAttribute("loaiSP", new LoaiSP());
		System.out.println("Them Loai");
		return "admin/category-manager/addCat";
	}

	@RequestMapping(value = "/addCat", method = RequestMethod.POST)
	public String insertCategory_(HttpServletRequest request, Model model,
			@Validated @ModelAttribute("loaisp") LoaiSP loaisp, BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("massage_insert", "Thêm không thành công");
			return "admin/category-manager/addCat";
		} else {
			boolean check = true;
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(loaisp);
				t.commit();
			} catch (Exception e) {
				t.rollback();
				check = false;
			} finally {
				session.close();
			}
			if (check == true) {
				return "redirect:category_manager.htm";
			} else {
				model.addAttribute("massage_insert", "Thêm không thành công");
				return "redirect:" + request.getHeader("Referer");
			}
		}

	}
	@RequestMapping(value="editCat/{category-id}")
	public String editCategory(@PathVariable("category-id") int categoryID, ModelMap model) {
		Session session = factory.openSession();
		LoaiSP lsp = (LoaiSP) session.get(LoaiSP.class, categoryID);
		model.addAttribute("Category", lsp);
		return "/admin/category-manager/editCat";
	}
	

	@RequestMapping("editCat")
	public String editCategory_(ModelMap model, @RequestParam("category_id") int category_id,
												@RequestParam("category_name") String category_name) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		LoaiSP lsp = (LoaiSP) session.get(LoaiSP.class, category_id);
		lsp.setTENLOAI(category_name);
		try {
			session.update(lsp);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công!");
		}
		catch(Exception e){
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/category_manager.htm";
	}
//
//	
//	
//	@RequestMapping(value = "deleteCat/{category-id}")
//	public String deleteCat(HttpServletRequest request, @PathVariable("category-id") int categoryID, ModelMap model) {
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		Category cat = (Category) session.load(Category.class, categoryID);
//		try {
//			session.delete(cat);
//			t.commit();
//			model.addAttribute("message", "Xoá thành công!");
//		}
//		catch(Exception e){
//			t.rollback();
//			model.addAttribute("message", "Xóa thất bại");
//		}
//		finally {
//			session.close();
//		}
//		return "redirect:/admin/manager_category.htm";
//		
//	}
}

