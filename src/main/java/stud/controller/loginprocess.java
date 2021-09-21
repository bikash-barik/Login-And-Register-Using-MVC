package stud.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stud.dao.LoginDao;
import stud.model.Login;
import stud.model.Student;

/**
 * Servlet implementation class loginprocess
 */
@WebServlet("/loginprocess")
public class loginprocess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Login login = new Login();
		login.setUserId(request.getParameter("userName"));
		login.setPassword(request.getParameter("password"));
		Student stud = new LoginDao().chackUser(login);
		
//		if(stud!=null)
//			response.getWriter().append("User Name: ").append(stud.getName()).append("<Br/> Type : ").append(stud.getType());
//		else
//			response.getWriter().append("User Not Exist ");
		response.getWriter().append("User Name: ").append(login.getUserId()).append("<Br/> Password : ").append(login.getPassword());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
