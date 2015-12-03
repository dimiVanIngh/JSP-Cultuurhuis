package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.dao.KlantDAO;
import be.vdab.dao.ReservatieDAO;
import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.Klant;
import be.vdab.entities.Reservatie;

@WebServlet("/overzicht.htm")
public class OverzichtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/overzicht.jsp";
	
	private final transient ReservatieDAO reservatieDAO = new ReservatieDAO();
	private final transient VoorstellingDAO voorstellingDAO = new VoorstellingDAO();

	@Resource(name = KlantDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		reservatieDAO.setDataSource(dataSource);
		voorstellingDAO.setDataSource(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("reservaties") != null) {
			List<Reservatie> gelukteReservaties = new ArrayList<Reservatie>();
			List<Reservatie> mislukteReservaties = new ArrayList<Reservatie>();
			List<Integer> vrijePlaatsen = new ArrayList<>();
			for (Reservatie reservatie : ((HashMap<Long, Reservatie>) session.getAttribute("reservaties")).values()) {
				reservatie.setKlant((Klant) session.getAttribute("user"));
				if (reservatieDAO.bevestigReservatie(reservatie)) {
					gelukteReservaties.add(reservatie);
				} else {
					mislukteReservaties.add(reservatie);
					vrijePlaatsen.add(voorstellingDAO.getAantalVrijePlaatsen(reservatie.getVoorstelling().getId()));
				}
			}
			request.setAttribute("vrijePlaatsen", vrijePlaatsen);
			request.setAttribute("gelukteReservaties", gelukteReservaties);
			request.setAttribute("mislukteReservaties", mislukteReservaties);
			session.removeAttribute("reservaties");
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	

}
