package jp.trans_it.world.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.trans_it.world.model.dao.CountryDAO;
import jp.trans_it.world.model.entity.Country;

@WebServlet("/top")
public class WorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public WorldServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String sort = request.getParameter("sort");
		String previousSort = (String)session.getAttribute("sort");
		String previousDirection = (String)session.getAttribute("direction");
		String direction = null;
		if(sort != null && previousSort != null) {
			if(sort.equals(previousSort)) {
				if(previousDirection == null) {
					direction = "DESC";
				}
			}
		}

		session.setAttribute("sort", sort);
		session.setAttribute("direction", direction);

		try {
			CountryDAO dao = new CountryDAO();
			List<Country> countries = dao.findAll(sort, direction);
			request.setAttribute("countries", countries);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
		String jsp = "/WEB-INF/jsp/world.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);

		dispatcher.forward(request,  response);
	}
}
