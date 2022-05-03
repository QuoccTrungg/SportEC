//package service.admin;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import dao.OrderDao;
//import entity.Order;
//
//
//
//@Service
//@Transactional
//public class OrderService {
//	@Autowired
//	OrderDao orderDao;
//	
//	public List<Order> getNullOrders(){
//		return orderDao.getNullOrder();
//	}
//	
//	public List<Order> getConfirmedOrders(){
//		return orderDao.getConfirmedOrder();
//	}
//	
//	public List<Order> getDenyOrders(){
//		return orderDao.getDenyOrder();
//	}
//	
//	public List<Order> getUserOrder(int id){
//		return orderDao.getUserOrder(id);
//	}
//}
