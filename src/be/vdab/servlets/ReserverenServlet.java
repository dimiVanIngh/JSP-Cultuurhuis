package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.dao.GenreDAO;
import be.vdab.dao.VoorstellingDAO;

@WebServlet("/reserveren.htm")
public class ReserverenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reserveren.jsp";
	private static final String REDIRECT_URL = "%s/winkelmandje.htm";

	private final transient VoorstellingDAO voorstellingDAO = new VoorstellingDAO();

	@Resource(name = GenreDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingDAO.setDataSource(dataSource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			try {
				long id = Long.parseLong(request.getParameter("id"));
				try {
					request.setAttribute("voorstelling", voorstellingDAO.findById(id));
				} catch (Exception ex) {
					request.setAttribute("fout", "Er is een probleem met de database, probeer het later eens opnieuw.");
				} 
			} catch (Exception ex) {
				request.setAttribute("fout", "Er werd geen geldig id meegegeven.");
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				int plaatsen = Integer.parseInt(request.getParameter("plaatsen"));
				long id = Long.parseLong(request.getParameter("voorstellingid"));
				if(plaatsen <= voorstellingDAO.getAantalVrijePlaatsen(id)){
					Map<Long, Integer> reservaties = new HashMap<>();
					HttpSession session = request.getSession();
					reservaties.put(id, plaatsen);
					session.setAttribute("reservaties", reservaties);
				}			
			} catch (Exception ex) {
				request.setAttribute("fout", "Er werd geen geldig aantal plaatsen meegegeven.");
			}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
