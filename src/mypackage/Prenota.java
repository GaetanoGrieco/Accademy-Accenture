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
 * Servlet implementation class Prenota
 */
@WebServlet("/Prenota")
public class Prenota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prenota() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		String appello = request.getParameter("appello");
		String matricola=(String) session.getAttribute("matricola");
		Connection conn= Connessione.getCon();
		
		try {
			PreparedStatement smtverifica = conn.prepareStatement("select stud_prenotato,app_prenotato from prenotazione where stud_prenotato=(CAST(? AS UNSIGNED INTEGER)) and app_prenotato=(CAST(? AS UNSIGNED INTEGER))");
			smtverifica.setString(1, matricola);
			smtverifica.setString(2,appello);
			ResultSet rsverifica=smtverifica.executeQuery();
			if (!rsverifica.next()) {
			PreparedStatement smt2 = conn.prepareStatement("insert into prenotazione (stud_prenotato,app_prenotato) values (CAST(? AS UNSIGNED INTEGER),CAST(? AS UNSIGNED INTEGER))");
			smt2.setString(1, matricola);
			smt2.setString(2,appello);
			smt2.executeUpdate();
			PreparedStatement recuperoData = conn.prepareStatement("select data,materia from appello where idAppello=CAST(? AS UNSIGNED INTEGER)");
			recuperoData.setString(1, appello);
			ResultSet data=recuperoData.executeQuery();
			data.next();
			String dataScelta=data.getString(1);
			String idcorso=data.getString(2);
			PreparedStatement recuperoMateria=conn.prepareStatement("select materia from corso where idcorso=CAST(? AS UNSIGNED INTEGER)");
			recuperoMateria.setString(1, idcorso);
			ResultSet materia=recuperoMateria.executeQuery();
			materia.next();
			String nomeMateria=materia.getString(1);
			RequestDispatcher rd1=request.getRequestDispatcher("studente.jsp");
			request.setAttribute("data", dataScelta);
			request.setAttribute("materia2", nomeMateria);
			rd1.forward(request, response);
			}
			else {
				RequestDispatcher rd1=request.getRequestDispatcher("studente.jsp");
				String messaggio="Sei già prenotato per questo appello";
				request.setAttribute("messaggio", messaggio);
				rd1.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

