package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CancellazioneAppello
 */
@WebServlet("/CancellazioneAppello")
public class CancellazioneAppello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellazioneAppello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idAppello= Integer.parseInt(request.getParameter("ID_appello"));
		
		
		Connection conn=Connessione.getCon();
		try {
			PreparedStatement smt4=conn.prepareStatement("delete from appello where idAppello=?");
			smt4.setInt(1, idAppello);
			smt4.executeUpdate();
			
		    RequestDispatcher rd9=request.getRequestDispatcher("professore.jsp");
			String messaggio="Appello cancellato con successo";
			request.setAttribute("messaggio", messaggio);
     		rd9.forward(request, response);
			
			} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}

}
