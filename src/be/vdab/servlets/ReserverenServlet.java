package be.vdab.servlets;

import java.io.IOException;
import java.util.Calendar;
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

import be.vdab.dao.DAOException;
import be.vdab.dao.GenreDAO;
import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.Reservatie;
import be.vdab.entities.Voorstelling;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			try {
				long id = Long.parseLong(request.getParameter("id"));
				try {
					Voorstelling tmp = voorstellingDAO.findById(id);					
					if (Calendar.getInstance().getTime().after(tmp.getDatum())) {
						request.setAttribute("fout", "Deze voorstelling is al gedaan.");
					} else {
						request.setAttribute("voorstelling", tmp);
						//checken voorstelling al in reservatiemandje
						HttpSession session = request.getSession();
						if (session.getAttribute("reservaties") != null) {
							HashMap reservaties = (HashMap<Long, Reservatie>) session.getAttribute("reservaties");
							if (reservaties.containsKey(id)) {
								request.setAttribute("aantalGereserveerdePlaatsen",
										((Reservatie) reservaties.get(id)).getAantalPlaatsen());
							}
						}
					}
				} catch (DAOException ex) {
					request.setAttribute("fout", "Probleem met de database, probeer het later opnieuw.");
				}
			} catch (Exception ex) {
				request.setAttribute("fout", "Er werd geen geldig id meegegeven.");
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int plaatsen = Integer.parseInt(request.getParameter("plaatsen"));
			long id = Long.parseLong(request.getParameter("id"));
			
			int maxPlaatsen = Integer.parseInt(request.getParameter("maxplaatsen"));
			if (plaatsen > 0 && plaatsen <= maxPlaatsen) {
				HttpSession session = request.getSession();
				Map<Long, Reservatie> reservaties;
				if (session.getAttribute("reservaties") != null) {
					reservaties = (Map<Long, Reservatie>) session.getAttribute("reservaties");
				} else {
					reservaties = new HashMap<Long, Reservatie>();
				}
				reservaties.put(id, new Reservatie(voorstellingDAO.findById(id), plaatsen));
				session.setAttribute("reservaties", reservaties);
				response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
			} else {
				request.setAttribute("fout", "Tik een getal tussen 1 en " + maxPlaatsen);
				doGet(request, response);
			}
		} catch (Exception ex) {
			request.setAttribute("fout", "Er werd geen geldig aantal plaatsen meegegeven.");
			doGet(request, response);
		}
	}

}
