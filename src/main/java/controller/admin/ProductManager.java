package controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import entity.LoaiSP;
import entity.SanPham;


@Transactional
@Controller
@RequestMapping("/admin")
public class ProductManager {

	@Autowired
	ServletContext context;
	
	@Autowired
	SessionFactory factory;
		
	public List<LoaiSP> getAllCategory() {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiSP";
		Query query = session.createQuery(hql);
		List<LoaiSP> list = query.list();
		return list;
}
	
	public List<SanPham> getAllProduct() {
			Session session = factory.getCurrentSession();
			String hql = "FROM SanPham";
			Query query = session.createQuery(hql);
			List<SanPham> list = query.list();
			return list;
	}
	
	@RequestMapping(value = "/product_manager")
	public String dataTable(Model model) {
		model.addAttribute("view_product", this.getAllProduct());
		return "admin/product-manager/manager";
	}

	@RequestMapping(value = "addPro", method = RequestMethod.GET)
	public String insertPro(ModelMap model) {
		SanPham sp = new SanPham();
		model.addAttribute("sanpham", sp);
		model.addAttribute("view_category", this.getAllCategory());
		return "admin/product-manager/addPro";
	}

	@RequestMapping(value = "addPro", method = RequestMethod.POST)
	public String insertPro_(HttpServletRequest request, ModelMap model,
			@Validated @ModelAttribute("sanpham") SanPham sp,
			@RequestParam("image") MultipartFile image,
			BindingResult errors) throws IOException{
		if (errors.hasErrors()) {
			model.addAttribute("massage_insert", "Thêm không thành công");
			model.addAttribute("view_category", this.getAllCategory());
			return "product-manager/addPro";
		} else {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			
			try {
				String img = StringUtils.cleanPath(image.getOriginalFilename()); 
				saveFile(image);
				sp.setHINHANH(img);
				sp.setTINHTRANG(true);
				session.save(sp);
				t.commit();
				model.addAttribute("message", "Thêm thành công!");
				
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm thất bại!");
			} finally {
				session.close();
			}
			return "redirect:/admin/product_manager.htm";
		}
	}
	
	
	@RequestMapping(value="deletePro/{product-id}")
	public String deletePro(ModelMap model, @PathVariable("product-id") int product_id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		SanPham prod = (SanPham) session.get(SanPham.class, product_id);
		try {
			session.delete(prod);
			t.commit();
			model.addAttribute("message", "Xoá thành công!");
		}
		catch(Exception e){
			t.rollback();
			model.addAttribute("message", "Xóa thất bại");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/product_manager.htm";
	}

	@RequestMapping(value="updatePro/{product-id}",method=RequestMethod.GET)
	public String editProduct(@PathVariable("product-id") int productId, ModelMap model) {
		Session session = factory.openSession();
		SanPham sp = (SanPham) session.get(SanPham.class, productId);
		model.addAttribute("sanpham", sp);
		model.addAttribute("view_category", this.getAllCategory());
		return "admin/product-manager/updatePro";
	}
	
	@RequestMapping(value="updatePro")
	public String update(ModelMap model, @Validated @ModelAttribute("sanpham") SanPham sp, 
			BindingResult result, @RequestParam("image") MultipartFile image) throws IOException {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();	
		if(!image.isEmpty()) {
			String img = StringUtils.cleanPath(image.getOriginalFilename()); 
			saveFile(image);
			sp.setHINHANH(img);
		}		
		
		try {
			session.update(sp);
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
		return "redirect:/admin/product_manager.htm";
	}

	@SuppressWarnings("null")
	public String saveFile(MultipartFile file) throws IOException {
		if(file != null || !file.isEmpty()) {
			String img = StringUtils.cleanPath(file.getOriginalFilename()); 
			String root = context.getRealPath("/");
			Path path = Paths.get(root + "assets/images/shop/" + img);
			//System.out.println(path);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		}
//			try {
//				byte[] bytes = file.getBytes();
//				
//				String root = context.getRealPath("/");
//
//				File dir = new File(rootPath + File.separator + "assets/images/shop");
//				if(!dir.exists()) {
//					dir.mkdir();
//				}
//				
//				//create file on server
//				String name = String.valueOf(file.getOriginalFilename());
//				File serverFile = new File(dir.getAbsoluteFile()+File.separator+name);
//				
//				System.out.println("==========Path of image on server: "+serverFile.getPath());
//				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//				stream.write(bytes);
//				stream.close();
//				
//				return name;
//			} catch (Exception e) {
//				System.out.println("==========Path of image on server: "+e.getMessage());
//			}
//			
//		} 
//		else {
//			System.out.println("=============File not exits=============");
//		}
		return null;
	}
}
