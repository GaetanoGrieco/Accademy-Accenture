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
 */
@WebServlet("/CancellaPren")
public class CancellaPren extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaPren() {
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
		int idprenotazione= Integer.parseInt(request.getParameter("idprenotazione"));
		
		
		Connection conn=Connessione.getCon();
		try {
			PreparedStatement smt22=conn.prepareStatement("delete from prenotazione where idpren=?");
			smt22.setInt(1, idprenotazione);
			smt22.executeUpdate();
			
		    RequestDispatcher rd10=request.getRequestDispatcher("studente.jsp");
			String messaggio="Prenotazione cancellata con successo";
			request.setAttribute("messaggio", messaggio);
     		rd10.forward(request, response);
			
			} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}

}
