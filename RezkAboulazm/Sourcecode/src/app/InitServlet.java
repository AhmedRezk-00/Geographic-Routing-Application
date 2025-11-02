package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/init")
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 3L;

	public static ArrayList<ArrayList<AdjListNode>> adjList;
	public static Dijkstra dijkstra;

	public static Dijkstra getDijkstra() {
		return dijkstra;
	}

	public static ArrayList<ArrayList<AdjListNode>> getList() {
		return adjList;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String file = request.getParameter("path");

		response.getWriter().write("reading graph data");
		ReadFile readfile = new ReadFile();
		readfile.readFile(file);

		response.getWriter().write("sorting nodelist");
		QuickSort sort = new QuickSort();
		sort.quickSort(ReadFile.nodeList, 0, ReadFile.numberOfNodes-1);

		response.getWriter().write("init adjacency list");
		dijkstra = new Dijkstra();
		adjList = dijkstra.buildAdjList();

		response.getWriter().write("done");
	}
}
