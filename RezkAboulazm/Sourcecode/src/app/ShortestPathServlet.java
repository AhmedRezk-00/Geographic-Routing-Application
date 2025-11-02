package app;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/shortestpath")
public class ShortestPathServlet extends HttpServlet {

	private static final long serialVersionUID = 3L;

	private int sourceNode = 0;
	private int targetNode = 0;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Dijkstra dijkstra = InitServlet.getDijkstra();
		ArrayList<ArrayList<AdjListNode>> adjList = InitServlet.getList();

		sourceNode = (int) Double.parseDouble((request.getParameter("source")));
		targetNode = (int) Double.parseDouble((request.getParameter("target")));
		System.out.println(sourceNode);
		System.out.println(targetNode);
		int ogSource = (int)ReadFile.nodeList[2][sourceNode];
		int ogTarget = (int)ReadFile.nodeList[2][targetNode];

		List<Integer> list = dijkstra.oneToOne(adjList,ogSource, ogTarget);

		String path = "[";

		for(int i = 0; i < list.size()-2; i++) {
			int edge = list.get(i);

			double lat = ReadFile.ogNodeList[0][edge];
			double lon = ReadFile.ogNodeList[1][edge];
			path += "[" +lon +","+ lat+"]"+",";
		}

		StringBuffer sb= new StringBuffer(path);

		sb.append("[" + ReadFile.ogNodeList[1][ogSource] + "," + ReadFile.ogNodeList[0][ogSource]+"]");
		sb.append("]");

		path = sb.toString();

		response.getWriter().write(path);

	}



}
