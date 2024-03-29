//package service.admin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import dao.CategoryDao;
//import dao.ProductDao;
//import entity.Category;
//import entity.Product;
//
//@Service
//@Transactional
//public class ProductServiceAD {
//	@Autowired
//	ProductDao productDao;
//
//	@Autowired
//	CategoryDao categoryDao;
//
//	public boolean addProduct(Product product) {
//		Category category = categoryDao.getCategoryID(product.getCategoryID().getCategory_id());
//		if (category == null) {
//			return false;
//		} else {
//			product.setCategoryID(category);
//		}
//		return productDao.addProduct(product);
//	}
//
//	public boolean update(Product product) {
//		Category category = categoryDao.getCategoryID(product.getCategoryID().getCategory_id());
//		if (category == null) {
//			return false;
//		} else {
//			product.setCategoryID(category);
//		}
//		return productDao.updateProduct(product);
//	}
//
//	public boolean delete(Product product) {
//		return productDao.deleteProduct(product);
//	}
//}
