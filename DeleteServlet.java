package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProvider;

public class DeleteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
   
    public DeleteServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			
			int noteID = Integer.parseInt(request.getParameter("note_id").trim());
			
			Session s = FactoryProvider.getFactory().openSession();
			Transaction  tx = s.beginTransaction();
			
		     Note note=(Note)s.get(Note.class, noteID);
			
		     	s.delete(note);
			    tx.commit();
		    	response.sendRedirect("all_notes.jsp");
			
			
			
			
			s.close();
			
		}catch(Exception e)
		
		{
			
			e.getStackTrace();
			
		}
		
		
	}

}
