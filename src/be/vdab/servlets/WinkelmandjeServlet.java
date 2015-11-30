package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Reservatie;

@WebServlet(name = "winkelmandje.htm", urlPatterns = { "/winkelmandje.htm" })
public class WinkelmandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/winkelmandje.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameterValues("id") != null) {
			@SuppressWarnings("unchecked")
			HttpSession session = request.getSession();
			Map<Long,Reservatie> reservaties = (HashMap<Long,Reservatie>) session.getAttribute("reservaties");
			for (String id : request.getParameterValues("id")) {
				reservaties.remove(Long.parseLong(id));
			}
			session.setAttribute("reservaties", reservaties);
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}

}
