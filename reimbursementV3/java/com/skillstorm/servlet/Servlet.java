package com.skillstorm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.skillstorm.beans.Expenses;
import com.skillstorm.beans.ReimbStatus;
import com.skillstorm.data.DAO;

//Controller - glues model to view
@WebServlet(urlPatterns = "/products")
public class Servlet extends HttpServlet{
	public static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
	Session session = SESSION_FACTORY.openSession();
	private DAO dao = new DAO(session);
	
	// Https Requests
		//
		//
		// GET /my-servlet v1.0
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//incoming HTTP request
			//process it
			//outgoing HTTP response
//			resp.setContentType("int.html");
//			resp.getWriter()..append(dao.getAll()); //Writes data to HTTP response body
			
//			
			req.setAttribute(getServletName(), dao.getAll());
			RequestDispatcher dispatcher = req.getRequestDispatcher("SoftWareDeveloperList.jsx");
			dispatcher.forward(req, resp);
		}
		
		//POST /my-servlet v1.0
		
		@Override            //            9:10 his time check vid
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.sendRedirect("success.html");
			//req.getRequestDispatcher("success.html").forward(req, resp);
			Expenses expense = new Expenses();
			ReimbStatus status = new ReimbStatus();
			expense.setId(Integer.getInteger(req.getParameter("id")));
			expense.setName(req.getParameter("name"));
			expense.setNotes(req.getParameter("reason"));
			expense.setReason(req.getParameter("notes"));
			status.setStatus("Pending");
			status.setStatusId(1);
			expense.setStatus(status);
			dao.save(expense);			
		}
		
		//Put(update)
		
		@Override
		protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			System.out.println("put----------------------------------------");
			
		}
		
		//Delete
		
		@Override
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			System.out.println("Delete----------------------------------------");
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public void init() throws ServletException {
//
//	}
//	@Override
//	public void destroy() {
//		session.close();
//	}
//	
//	private DAO dao = new DAO(session);
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		if(req.getSession().getAttribute("addedItem") != null) {
//		System.out.println(req.getSession().getAttribute("addedItem"));
//		}
//		
//		String param =req.getParameter("index");
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		String json = null;
//		resp.setContentType("application/json");
//		if(param != null) {
//			int index = Integer.parseInt(param);
//			json = objectMapper.writeValueAsString(dao.getAll().get(index));
//		}
//		else {
//			json = objectMapper.writeValueAsString(dao.getAll());
//		}
//
//		resp.getWriter().print(json);
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		if(req.getCookies() != null) {
//			for(Cookie cookie : req.getCookies()) {
//				if(cookie.getName().equals("dan")) {
//					System.out.println("Found cookie: " + cookie.getValue());
//				}
//			}
//		}
//		
//		
//		
//		InputStream requestBody = req.getInputStream();
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		//Take inputStream and convert it to Product object
//		Product product = objectMapper.readValue(requestBody, Product.class);
//		dao.add(product);
//		req.getSession().setAttribute("addedItem", product);
//		
//		// 200 OK
//		// 201 CREATED (POST is unsafe)   (GET is safe)   (DELETE is unsafe)
//		resp.setStatus(201);  //resp is committed
//		
//	}
//	
	

}
