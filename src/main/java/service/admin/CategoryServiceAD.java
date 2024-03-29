//package service.admin;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import dao.CategoryDao;
//import entity.Category;
//import entity.Product;
//import service.user.ProductService;
//
//@Service
//@Transactional
//public class CategoryServiceAD {
//	@Autowired
//	CategoryDao categoryDao;
//	@Autowired
//	ProductService productService;
//
//	@Transactional
//	public boolean addCategory(Category category) {
//		return categoryDao.addCategory(category);
//	}
//
//	@Transactional
//	public boolean updateCategory(Category category) {
//		List<Product> list = productService.getProductByCategoryID(category.getCategory_id());
//		category.setProduct(list);
//		return categoryDao.updateCategory(category);
//	}
//
//	@Transactional
//	public boolean deleteCategory(Category category) {
//		return categoryDao.deleteCategory(category);
//	}
//}
