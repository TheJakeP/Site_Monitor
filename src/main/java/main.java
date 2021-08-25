
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class main
 */
@WebServlet("/main")
public class main extends HttpServlet {
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public main() {
		
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		pageCrawler crawler = new pageCrawler("https://stackoverflow.com");
		String vars = crawler.getPageHTML();
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Date date = new Date();
		out.println(HTML_START);
		out.println("<h2>Hi There!</h2>");
		out.print(vars);
		out.println("<h3>Today's Date is: "+date +"</h3>");
		out.println(HTML_END);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
