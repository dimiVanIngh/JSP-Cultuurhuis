package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.GenreDAO;
import be.vdab.dao.VoorstellingDAO;

@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";

	private final transient GenreDAO genreDAO = new GenreDAO();
	private final transient VoorstellingDAO voorstellingDAO = new VoorstellingDAO();

	@Resource(name = GenreDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		genreDAO.setDataSource(dataSource);
		voorstellingDAO.setDataSource(dataSource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("genres", genreDAO.findAll());
		if(request.getParameter("id") != null){
			request.setAttribute("voorstellingen", voorstellingDAO.findAllById(Integer.parseInt(request.getParameter("id"))));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
