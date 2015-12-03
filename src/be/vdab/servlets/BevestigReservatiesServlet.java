package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.KlantDAO;
import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.Klant;
import be.vdab.util.BCrypt;

@WebServlet("/bevestig.htm")
public class BevestigReservatiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";

	private final transient KlantDAO klantDAO = new KlantDAO();
	private final transient VoorstellingDAO voorstellingDAO = new VoorstellingDAO();

	@Resource(name = KlantDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantDAO.setDataSource(dataSource);
		voorstellingDAO.setDataSource(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("zoekmeop") != null) {
			zoekMeOp(request, response);
		}else
			doGet(request, response);
	}

	private void zoekMeOp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gebruikersnaam = (String) request.getParameter("gebruikersnaam");
		String wachtwoord = (String) request.getParameter("wachtwoord");
		Klant klantInDB = klantDAO.findByGebruikersnaam(gebruikersnaam);
		if (klantInDB != null) {
			if (BCrypt.checkpw(wachtwoord, klantInDB.getWachtwoord())) {
				request.getSession().setAttribute("user", klantInDB);
			} else
				request.setAttribute("fout", "verkeerde gebruikersnaam of paswoord.");
		} else {
			request.setAttribute("fout", "verkeerde gebruikersnaam of paswoord.");
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
