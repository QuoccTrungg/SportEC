//package service.user;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import dao.CategoryDao;
//import entity.Category;
//
//@Service
//@Transactional
//public class CategoryService {
//	@Autowired
//	CategoryDao categoryDao = new CategoryDao();
//
//	public Category getCategoryID(int category_id) {
//		return categoryDao.getCategoryID(category_id);
//	}
//
//	public List<Category> getAllCategory() {
//		return categoryDao.getAllCategory();
//	}
//
//	public Category getCategoryByName(String category_name) {
//		return categoryDao.getCategoryByName(category_name);
//	}
//}
