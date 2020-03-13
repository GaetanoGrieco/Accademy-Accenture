package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class InserisciApp
 */
@WebServlet("/InserisciApp")
public class InserisciApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciApp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data= request.getParameter("data");  
		String aula=request.getParameter("aula");
		String idcorso=request.getParameter("idmateria");
		Connection conn=Connessione.getCon();
		try {
			
			PreparedStatement smt=conn.prepareStatement("insert into appello (data,materia,aula) values(?,cast(? as unsigned integer),?)");
			
			
			
			smt.setString(1, data);
			smt.setString(2,idcorso);
			smt.setString(3, aula);
			smt.executeUpdate();
			
			
		    RequestDispatcher rd8=request.getRequestDispatcher("inserisci_appello.jsp");
			String messaggio="appello registrato con successo";
			request.setAttribute("messaggio", messaggio);
     		rd8.forward(request, response);
     		
     		
			
			} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
	
	}

}
