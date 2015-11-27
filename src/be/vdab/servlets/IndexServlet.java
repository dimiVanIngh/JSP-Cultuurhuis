package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.GenreDAO;
import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.Voorstelling;

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

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("genres", genreDAO.findAll());
		if (request.getParameter("id") != null) {
			try {
				long id = Long.parseLong(request.getParameter("id"));
				try {
					request.setAttribute("voorstellingen", voorstellingDAO.findAllByIdWithoutExpired(id));
					if(((ArrayList<Voorstelling>) request.getAttribute("voorstellingen")).isEmpty()){
						request.setAttribute("leeg", true);
					}
				} catch (Exception ex) {
					request.setAttribute("fout", "Er is een probleem met de database, probeer het later eens opnieuw.");
				}
			} catch (Exception ex) {
				request.setAttribute("fout", "Er werd geen geldig id meegegeven.");
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
