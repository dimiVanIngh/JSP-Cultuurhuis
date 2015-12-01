package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.KlantDAO;
import be.vdab.entities.Klant;
import be.vdab.util.Validator;

@WebServlet("/registreren.htm")
public class RegistratieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/registreren.jsp";
	private static final String REDIRECT_URL = "%s/bevestig.htm";
	
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
		checkVeldenAlfaNumeriek(request, fouten);
		if(!wachtwoordenGelijk(request)){
			fouten.add("Paswoorden komen niet overeen.");
		} 
		// TODO checken username -> add / error
		if (fouten.isEmpty()) {
			Klant klant = createKlant(request);
			klant.hashWachtwoord();
			if(klantDAO.insertKlant(klant)){
				request.getSession().setAttribute("user", klant);
				response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
			} else fouten.add("Gebruikersnaam is al in gebruik.");
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
		
	private Klant createKlant(HttpServletRequest request){
		String voornaam = request.getParameter("voornaam");
		String familienaam = request.getParameter("familienaam");
		String straat = request.getParameter("straat");
		String huisnr = request.getParameter("huisnr");
		String gemeente = request.getParameter("gemeente");
		String postcode = request.getParameter("postcode");
		String gebruikersnaam = request.getParameter("gebruikersnaam");
		String wachtwoord = request.getParameter("wachtwoord");
		return new Klant(voornaam,familienaam,straat,huisnr,gemeente,postcode,gebruikersnaam,wachtwoord);
	}

	//TODO test postcode atleast 1 number + max 10 cijfers
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
			fouten.add(parameter + " mag enkel cijfers en/of letters bevatten, geen speciale tekens.");
		}
	}
	private boolean wachtwoordenGelijk(HttpServletRequest request){
		return request.getParameter("wachtwoord").equals(request.getParameter("confirmwachtwoord"));
	}
}
