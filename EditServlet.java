package com.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProvider;


public class EditServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public EditServlet() {
        super();
      
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		try
		{
			
			String title= request.getParameter("title");
			String content=request.getParameter("content");
			
			int noteId = Integer.parseInt(request.getParameter("noteId").trim());
			
			
			
			Session s = FactoryProvider.getFactory().openSession();
			Transaction tx = s.beginTransaction();
			
			Note note= s.get(Note.class, noteId);
			
			// here it is in transient state so it will update and store the new value.
			
			note.setTitle(title);
			note.setContent(content);
			note.setNoteDate(new Date());
			
			tx.commit();
			s.close();
			
			response.sendRedirect("all_notes.jsp");
			
			
		}catch(Exception  e)
		{
			e.printStackTrace();
		}
		
		
		
	}

}
