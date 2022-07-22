//package com.skillstorm.test;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import com.skillstorm.beans.Expenses;
//import com.skillstorm.beans.ReimbStatus;
//import com.skillstorm.data.DAO;
//
//public class Testing {
//
//	// SessionFactory MUST be a singleton
//		public static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
//
//		public static void main(String[] args) {
//			Session session = SESSION_FACTORY.openSession();
//			DAO dao = new DAO(session);
//
//			System.out.println("Get open");
//				System.out.println(dao.getAll());
//			System.out.println("Get close");
//			
////			System.out.println("Post open");
////				ReimbStatus status = new ReimbStatus();
////				status.setStatus("Pending");
////				status.setStatusId(1);
////				Expenses e = new Expenses(20,"House","Bought one", "please", status);
////				dao.save(e);
////				System.out.println(dao.getAll());
////			System.out.println("Post close");
//		
////			System.out.println("Put open");
////				System.out.println(dao.getAll());
////			System.out.println("Put close");
////	
//			System.out.println("Delete open");
//				System.out.println(dao.getAll());
//			System.out.println("Delete close");
//			
//			
//			
//			session.close();
//		}
//	
//}
