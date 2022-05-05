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
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entity.CTPD;
import entity.GioHang;
import entity.LoaiSP;
import entity.PhieuDat;

import entity.SanPham;

@Transactional
@Controller
public class CategoryController {
	@Autowired
//	private CategoryService categoryService;
//
//	@Autowired
//	private ProductService productService;
//
//	@Autowired
//	CartService cartService;
	SessionFactory factory;
	
	////requestmapping
	@RequestMapping(value = "product")
	public String showCategory(ModelMap model, HttpSession session) {
		try {
//			Session ss = factory.getCurrentSession();
//			String hql = "FROM SanPham";
//			Query query = ss.createQuery(hql);
////			query.setMaxResults(1);
////			SanPham sp = (SanPham)query.uniqueResult();
////			System.out.println(sp.getMASP());
			List<SanPham> list = getAllSP();
			model.addAttribute("all_product",list);
		}
		catch(Exception ex){
			
		}
		
//		List<Product> listPro = productService.getAllProduct();
//		int page = listPro.size() / 8;
//		if (listPro.size() % 8 != 0) {
//			page += 1;
//		}
//		model.addAttribute("page", page);
//		model.addAttribute("all_category", categoryService.getAllCategory());
//		model.addAttribute("all_product", productService.getProductLimit(0));
//		Cart cart = cartService.getGioHang(session);
//		model.addAttribute("cartCount", cart.getItems().size());
		GioHang gh = (GioHang)session.getAttribute("gioHang");
		if(gh == null) {
			gh= new GioHang();
			session.setAttribute("gioHang",gh);
		}
		model.addAttribute("cartCount", gh.getItems().size());
		
		return "product";
	}
	
//	
//	
//	

	
//	
//	
//	
//	
	@RequestMapping(value = "product/{product-id}")
	public String productSingle(@PathVariable("product-id") int Masp, ModelMap model, HttpSession session) {
		Session ss = factory.getCurrentSession();
		SanPham sp = (SanPham) ss.get(SanPham.class, Masp);
		model.addAttribute("productsingle", sp);
		int masp=sp.getMASP();
		int maloai=sp.getLoaisp().getMALOAI();
		LoaiSP lsp = (LoaiSP) ss.get(LoaiSP.class,maloai);
				//		System.out.println(lsp.getMALOAI());
		model.addAttribute("all_product",getSPKhac(lsp.getMALOAI(),masp));
//		Cart cart = cartService.getGioHang(session);
		//		Product p = productService.getProductID(productID);
//		model.addAttribute("productsingle", sp);
//		model.addAttribute("all_product", productService.getProductByCategoryID(p.getCategoryID().getCategory_id()));
//		Cart cart = cartService.getGioHang(session);
//		model.addAttribute("cartCount", cart.getItems().size());
		GioHang gh = (GioHang)session.getAttribute("gioHang");
		if(gh == null) {
			gh= new GioHang();
			session.setAttribute("gioHang",gh);
		}
		model.addAttribute("cartCount", gh.getItems().size());
		return "product_single";
	}
//
	@RequestMapping(value = "pages/{page-id}")
	public String showSLimit(@PathVariable("page-id") int first, ModelMap model, HttpSession session) {
		List<SanPham> listsp = getAllSP();
		int page = listsp.size() / 8;
		if (listsp.size() % 8 != 0) {
			page += 1;
		}
		model.addAttribute("page", page);
		model.addAttribute("all_category",getAllLoaiSP());
		model.addAttribute("all_product", getSPLimit((first - 1) * 8));
		GioHang gh = (GioHang)session.getAttribute("gioHang");
		if(gh == null) {
			gh= new GioHang();
			session.setAttribute("gioHang",gh);
		}
		model.addAttribute("cartCount", gh.getItems().size());
		
		
		
//		List<Product> listPro = productService.getAllProduct();
//		int page = listPro.size() / 8;
//		if (listPro.size() % 8 != 0) {
//			page += 1;
//		}
//		model.addAttribute("page", page);
//		model.addAttribute("all_category", categoryService.getAllCategory());
//		model.addAttribute("all_product", productService.getProductLimit((first - 1) * 8));
//		Cart cart = cartService.getGioHang(session);
//		model.addAttribute("cartCount", cart.getItems().size());
		return "product";
	}
//
	@RequestMapping(value = { "category/{category-id}" })
	public String showCategoryByID(@PathVariable("category-id") int MALOAI, ModelMap model, HttpSession session) {
		Session ss = factory.getCurrentSession();
		LoaiSP lsp = (LoaiSP) ss.get(LoaiSP.class, MALOAI);
		List<SanPham> listsp = getSPMALoai(MALOAI);
		int page = listsp.size() / 8;
		if (listsp.size() % 8 != 0) {
			page += 1;
		}
		model.addAttribute("pagecategory", page);
		model.addAttribute("category",lsp);
		model.addAttribute("all_category", getAllLoaiSP());
		model.addAttribute("all_product",getSPLLimit(MALOAI,0) );
		GioHang gh = (GioHang)session.getAttribute("gioHang");
		if(gh == null) {
			gh= new GioHang();
			session.setAttribute("gioHang",gh);
		}
		model.addAttribute("cartCount", gh.getItems().size());

		
		//		Category c = categoryService.getCategoryID(categoryID);
//		List<Product> listPro = productService.getProductByCategoryID(c.getCategory_id());
//		int page = listPro.size() / 8;
//		if (listPro.size() % 8 != 0) {
//			page += 1;
//		}
//		model.addAttribute("pagecategory", page);
//		model.addAttribute("category", c);
//		model.addAttribute("all_category", categoryService.getAllCategory());
//		model.addAttribute("all_product", productService.getProductByCategoryIDLimit(categoryID, 0));
//		Cart cart = cartService.getGioHang(session);
//		model.addAttribute("cartCount", cart.getItems().size());
		return "productcategory";
	}
/////
	
	
	
	/////
	
	
	//
	@RequestMapping(value = "pages/{category-id}/{page-id}")
	public String showProductLimitCategory(@PathVariable("category-id") int maloai,
			@PathVariable("page-id") int first, ModelMap model, HttpSession session) {
		Session ss = factory.getCurrentSession();
		LoaiSP lsp = (LoaiSP) ss.get(LoaiSP.class, maloai);
		
		List<SanPham> listsp = getSPMALoai(maloai);
		int page = listsp.size() / 8;
		if (listsp.size() % 8 != 0) {
			page += 1;
		}
		model.addAttribute("category", lsp);
		model.addAttribute("pagecategory", page);
		model.addAttribute("all_category", getAllLoaiSP());
		model.addAttribute("all_product", getSPLLimit(maloai, (first - 1) * 8));
		GioHang gh = (GioHang)session.getAttribute("gioHang");
		if(gh == null) {
			gh= new GioHang();
			session.setAttribute("gioHang",gh);
		}
		
		model.addAttribute("cartCount", gh.getItems().size());
		///
//		Category c = categoryService.getCategoryID(categoryid);
//		List<Product> listPro = productService.getProductByCategoryID(categoryid);
//		int page = listPro.size() / 8;
//		if (listPro.size() % 8 != 0) {
//			page += 1;
//		}
//		model.addAttribute("category", c);
//		model.addAttribute("pagecategory", page);
//		model.addAttribute("all_category", categoryService.getAllCategory());
//		model.addAttribute("all_product", productService.getProductByCategoryIDLimit(categoryid, (first - 1) * 8));
//		Cart cart = cartService.getGioHang(session);
//		model.addAttribute("cartCount", cart.getItems().size());
		return "productcategory";	
		}
//
	////
	////
	///
	
	@RequestMapping(value = "cart")
	public String cart(HttpSession session, ModelMap model) {
		
		GioHang gh = (GioHang)session.getAttribute("gioHang");
		if(gh == null) {
			gh= new GioHang();
			session.setAttribute("gioHang",gh);
		}
		
		model.addAttribute("cartCount", gh.getItems().size());
		//		Cart cart = cartService.getGioHang(session);
//		model.addAttribute("cartCount", cart.getItems().size());
		
		gh.getTong();
		return "cart";
	}
	@RequestMapping(value = "tracuu")
	public String tracuu(HttpSession session, ModelMap model) {
		return "order_history";
	}


	@RequestMapping(value = "search", method=RequestMethod.POST)
	public String search(HttpSession session, ModelMap model,
			@RequestParam("order_phone") String phone,RedirectAttributes rd) {
		System.out.println(phone);
		model.addAttribute("waitingOrder", this.getPD(phone));
	if(getPD(phone).size()==0) 
		model.addAttribute("message","Không  có đơn đặt hàng nào");
		return "order_history";
	}
	
	
	
	@RequestMapping(value="/orderDetails/{id}")
	public String orderDetails(ModelMap model, @PathVariable("id") int id, HttpServletRequest request) {
		
		Session session = factory.getCurrentSession();
		String hql = "FROM CTPD WHERE MAPD LIKE '" + id + "'";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CTPD> od = query.list();
		//System.out.println(od.get(0).getSp().getTENSP());
		model.addAttribute("od", od);
		model.addAttribute("id", id);
		model.addAttribute("size", od.size());
		//PhieuDat order = (PhieuDat) session.get(PhieuDat.class, id);
		int total=0;
		for(CTPD item : od) total +=item.getGIA()*item.getSOLUONG(); 
		model.addAttribute("total", total);
		return "Details_user";
	}
	
	@RequestMapping(value="deny/{order_id}")
	public String deny(ModelMap model, @PathVariable("order_id") int order_id) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		PhieuDat pd = (PhieuDat) session.get(PhieuDat.class,order_id);
		/// HUY đơn : TRANGTHAI=0///////

		pd.setTRANGTHAI(0);
		List<CTPD> listct=getCTPD(pd.getMAPD());

		for(CTPD item : listct) {

			Session session2 = factory.openSession();
			Transaction t2 = session2.beginTransaction();
			SanPham sp = (SanPham)session2.get(SanPham.class,item.getSp().getMASP());
			sp.setSOLUONG(sp.getSOLUONG()+item.getSOLUONG());
//			System.out.println(sp.getSOLUONG());
			try {
				session2.update(sp);
				t2.commit();
				model.addAttribute("message", "Cập nhật thành công");
			}
			catch(Exception e){
				t2.rollback();
				model.addAttribute("message", "Cập nhật thất bại");
			}
			finally {
				session2.close();
			}
	
		}

		try {
			session.update(pd);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công");
		}
		catch(Exception e){
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại");
		}
		finally {
			session.close();
		}
		

		return "redirect:/tracuu.htm";
	}
	
////////function//////////
	///////
	public List<SanPham> getSPLLimit(int maloai, int first) {
		try {
			
			Session session = factory.getCurrentSession();
			String hql = "FROM SanPham WHERE MALOAI LIKE '" +  maloai + "'";
			Query query = session.createQuery(hql).setFirstResult(first).setMaxResults(8);
			List<SanPham> list = query.list();
			return list;

		} catch (Exception e) {
			return null;
		}

	}
	////
	public List<SanPham> getSPMALoai(int maloai) {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM SanPham WHERE MALOAI LIKE '" + maloai + "'" ;
			Query query = session.createQuery(hql);
			List<SanPham> list = query.list();
			return list;

		} catch (Exception e) {
			return null;
		}

	}
	////
	public List<PhieuDat> getPD(String sdt){
		//try {
			Session session = factory.getCurrentSession();
			String hql = "FROM PhieuDat WHERE SDT LIKE '" + sdt + "'AND TRANGTHAI= 1";
//			System.out.println(hql);
			Query query = session.createQuery(hql);
//			System.out.println(hql);
			List<PhieuDat> list = query.list();
//			System.out.println(list.get(0).getSDT());
			return list;
		//} catch (Exception e) {
		//	return null;
		//}
	}
	///
	public List<SanPham> getSPLimit(int first) {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM SanPham ORDER BY MASP DESC";
			Query query = session.createQuery(hql).setFirstResult(first).setMaxResults(8);
			List<SanPham> list = query.list();
			return list;

		} catch (Exception e) {
			return null;
		}
	}
////
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
/////
	public List<SanPham> getSPKhac(int maloai, int masp) {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM SanPham WHERE MALOAI LIKE '" + maloai + "'AND MASP != '" + masp + "' AND SOLUONG!=0 AND TINHTRANG=True";
			Query query = session.createQuery(hql);
			List<SanPham> list = query.list();
			return list;

		} catch (Exception e) {
			return null;
		}

	}
	//////
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
	///
	public List<CTPD> getCTPD(int mapd) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTPD WHERE MAPD LIKE '" + mapd + "'";
		Query query = session.createQuery(hql);
		List<CTPD> list = query.list();
		return list;
}
	//////////////////////////////

}
