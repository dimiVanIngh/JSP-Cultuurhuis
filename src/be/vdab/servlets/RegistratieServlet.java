package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.KlantDAO;
import be.vdab.util.Validator;

@WebServlet("/registreren.htm")
public class RegistratieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/registreren.jsp";

	private final transient KlantDAO klantDAO = new KlantDAO();

	@Resource(name = KlantDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantDAO.setDataSource(dataSource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> fouten = new ArrayList<String>();
		checkVeldenIngevuld(request, fouten);
		//checkVeldenAlfaNumeriek(request, fouten);
		if(!wachtwoordenGelijk(request)){
			fouten.add("Paswoorden komen niet overeen.");
		} 
		if (fouten.isEmpty()) {
			//klantDAO.addBericht(new GastenboekBericht(naam, bericht));
			//response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	private void checkVeldenIngevuld(HttpServletRequest request,List<String> fouten){
		checkVeldIngevuld(request, fouten, "voornaam");
		checkVeldIngevuld(request, fouten, "familienaam");
		checkVeldIngevuld(request, fouten, "straat");
		checkVeldIngevuld(request, fouten, "huisnr");
		checkVeldIngevuld(request, fouten, "postcode");
		checkVeldIngevuld(request, fouten, "gemeente");
		checkVeldIngevuld(request, fouten, "gebruikersnaam");
		checkVeldIngevuld(request, fouten, "wachtwoord");
	}
	private void checkVeldIngevuld(HttpServletRequest request,List<String> fouten,String parameter){
		if (request.getParameter(parameter).isEmpty()) {
			fouten.add(parameter + " niet ingevuld.");
		}
	}
	private void checkVeldenAlfaNumeriek(HttpServletRequest request,List<String> fouten){
		checkVeldAlfaNumeriek(request, fouten, "huisnr");
		checkVeldAlfaNumeriek(request, fouten, "postcode");
	}
	private void checkVeldAlfaNumeriek(HttpServletRequest request,List<String> fouten,String parameter){
		if(!Validator.isAlphaNumeric(request.getParameter(parameter))){
			fouten.add(parameter + " mag enkel cijfers en letters bevatten, geen speciale tekens.");
		}
	}
	private boolean wachtwoordenGelijk(HttpServletRequest request){
		return request.getParameter("wachtwoord").equals(request.getParameter("confirmwachtwoord"));
	}
}
