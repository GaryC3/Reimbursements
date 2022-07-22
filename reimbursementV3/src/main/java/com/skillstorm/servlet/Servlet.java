package com.skillstorm.servlet;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.beans.Expenses;
import com.skillstorm.beans.ReimbStatus;
import com.skillstorm.data.DAO;

//Controller - glues model to view
@WebServlet(urlPatterns = "/*")
public class Servlet extends HttpServlet {
	public static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
	Session session = SESSION_FACTORY.openSession();
	private DAO dao = new DAO(session);

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		System.out.println("-------------------get-------------------");
		List<Expenses> list = dao.getAll();

			ObjectMapper mapper = new ObjectMapper();
			System.out.println(mapper.writeValueAsString(list));
			resp.getWriter().write(mapper.writeValueAsString(list)); //Writes data to HTTP response body
	}

	// POST
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("-------------------post-------------------");
		String payloadRequest = getBody(req);
		System.out.println(payloadRequest);
		int a = payloadRequest.indexOf("id") - 3;
		int b = payloadRequest.indexOf("name") - 3;
		int c = payloadRequest.indexOf("reason") - 3;
		int d = payloadRequest.indexOf("notes") - 3;
		int e = payloadRequest.length() - 2;

		Expenses expense = new Expenses();
		ReimbStatus status = new ReimbStatus();
		expense.setId(Integer.parseInt(payloadRequest.substring(a + 8, b)));
		expense.setName(payloadRequest.substring(b + 10, c));
		expense.setReason(payloadRequest.substring(c + 12, d));
		expense.setNotes(payloadRequest.substring(d + 11, e));
		status.setStatus("Pending");
		status.setStatusId(1);
		expense.setStatus(status);
		dao.save(expense);
	}

	// Put(update)

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("-------------------put-------------------");

		String payloadRequest = getBody(req);
		System.out.println(payloadRequest);
		int a = payloadRequest.indexOf("id") - 3;
		int b = payloadRequest.indexOf("status") - 2;
		int c = payloadRequest.indexOf("notes") - 3;
		
		int id = Integer.parseInt(payloadRequest.substring(a+7, b));
		int statusId = Integer.parseInt(payloadRequest.substring(b+11, c));
		String notes = payloadRequest.substring(c+11, payloadRequest.length()-2);

		if(statusId == 2) {
			dao.approve(id,notes);
		}
		if(statusId == 3) {
			dao.deny(id,notes);
		}
		
		
	}

	// Delete

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String payloadRequest = getBody(req);
		System.out.println("-------------------delete-------------------");
		payloadRequest = payloadRequest.substring(6,payloadRequest.length()-1);
		dao.delete(Integer.parseInt(payloadRequest));
		
		

	}

	public static String getBody(HttpServletRequest request) throws IOException {

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}
}
