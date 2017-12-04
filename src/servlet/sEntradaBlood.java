package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.beanData;

/**
 * Servlet implementation class sEntradaBlood
 */
@WebServlet("/sEntradaBlood")
public class sEntradaBlood extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sEntradaBlood() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void doDo(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("nom");
		String code = request.getParameter("code");
		beanData beanData = new beanData();
		HttpSession session;
		
		beanData.setName(name);
		beanData.setCode(code);
		
		session = request.getSession(true);
		session.setAttribute("bean.beanData", beanData);
		
		
		try {
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/jspFinal");
			rd.forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
