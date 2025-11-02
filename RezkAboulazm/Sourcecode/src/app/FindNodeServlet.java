package app;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findnode")
public class FindNodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private double lon = 0;
	private double lat = 0;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FindNearestNode find = new FindNearestNode();

		lon = Double.parseDouble(request.getParameter("lon"));
		lat = Double.parseDouble(request.getParameter("lat"));

		find.findNearest(lon, lat);

		response.getWriter().write(Double.toString(FindNearestNode.nearestLon) + ","+Double.toString(FindNearestNode.nearestLat) + "," + Double.toString(FindNearestNode.nearestIndex));
	}

}

