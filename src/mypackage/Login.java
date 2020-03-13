package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username= request.getParameter("username");  //abbiamo preso username e password
		String password=request.getParameter("password");

		
		Connection conn=Connessione.getCon(); //abbiamo stabilito una connessione ad db
		try {     
			PreparedStatement smt=conn.prepareStatement("select  username, password from studente where (username=? and password= AES_ENCRYPT(?,'key'));");   //ci siamo presi le user e password dalla tabella studente 
			smt.setString(1, username);
			smt.setString(2, password);
			ResultSet rs=smt.executeQuery();
			HttpSession session;
			while(rs.next()) {
					/* Abbiamo interrogato il database per estrapolare i dati 
					 * di interesse per le successive operazioni */
					PreparedStatement smt1=conn.prepareStatement("select matricola,nome,cognome from studente where username=?");
					smt1.setString(1, username);  
					ResultSet rs1 =smt1.executeQuery(); 
					rs1.next(); 
					String matricola=rs1.getString("matricola");
					String nome=rs1.getString("nome");
					String cognome=rs1.getString("cognome");
					Statement smt2=conn.createStatement();
					ResultSet rs2=smt2.executeQuery("select idcorso,materia,nome,cognome from corso inner join professore on cattedra=idprofessore");
					PreparedStatement smt11 =conn.prepareStatement("SELECT p.idpren,a.Data,c.materia,a.aula FROM prenotazione p,appello a,corso c where a.materia=c.idcorso and a.idAppello=p.app_prenotato and p.stud_prenotato=(cast(? as unsigned integer))");
					smt11.setString(1, matricola);
					ResultSet rs11=smt11.executeQuery();
					session=request.getSession(true); 
					session.setAttribute("matricola", matricola);
					session.setAttribute("nome", nome);
					session.setAttribute("cognome", cognome);
					RequestDispatcher rd= request.getRequestDispatcher("studente.jsp"); 
                    request.setAttribute("tabella_corso", rs2);
                    request.setAttribute("tabella_prenotazioni", rs11);
                    rd.forward(request,response);
           
            
				
				

			}
			PreparedStatement smt3=conn.prepareStatement("select  username, password from professore where (username=? and password= AES_ENCRYPT(?,'key'));");   //ci siamo presi le user e password dalla tabella professore
			smt3.setString(1, username);
			smt3.setString(2, password);
			ResultSet rs3=smt3.executeQuery();
			while(rs3.next()) {
				
					session=request.getSession(true);
					PreparedStatement smt4=conn.prepareStatement("select nome,cognome,idProfessore from professore where username=?"); //verifichiamo il nome del professore alla username e password inserita
					smt4.setString(1, username);
					ResultSet rs4= smt4.executeQuery();
					rs4.next();
					String nome=rs4.getString("nome");
					String cognome=rs4.getString("cognome");
					int idProfessore=rs4.getInt("idProfessore");
					PreparedStatement smt5=conn.prepareStatement("select idcorso,materia from corso where cattedra=?");
					smt5.setInt(1, idProfessore);
					ResultSet rs5=smt5.executeQuery();
					rs5.next();
					int idcorso=rs5.getInt("idcorso");
					String materia=rs5.getString("materia");
					PreparedStatement smt6=conn.prepareStatement("select idAppello,Data,aula from appello where Materia=?");
					smt6.setInt(1, idcorso);
					ResultSet appelli=smt6.executeQuery();
					session.setAttribute("nome", nome);
					session.setAttribute("cognome", cognome);
					RequestDispatcher rd4=request.getRequestDispatcher("professore.jsp");
					session.setAttribute("materia", materia);
					session.setAttribute("idcorso", idcorso);
					request.setAttribute("appelli", appelli);
					
					rd4.forward(request, response);
				}
			RequestDispatcher rd3= request.getRequestDispatcher("index.jsp");
			String messaggio="username e password non sono presenti";
			request.setAttribute("messaggio", messaggio);
			rd3.forward(request, response);
			
		}catch (SQLException e ) {

			System.out.println(e.getMessage());
		}
		
		
				
	}
}
