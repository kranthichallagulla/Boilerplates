package com.stackroute.keepnote.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.stackroute.keepnote.model.Note;
import org.hibernate.query.Query;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */

public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */
	private SessionFactory sessionFactory;

	public NoteDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
	}

	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveNote(Note note) {
		Session session = sessionFactory.getCurrentSession();
		session.save(note);
		session.flush();
		return true;

	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteNote(int noteId) {
		Session session = sessionFactory.getCurrentSession();
		String queryStr = "from Note where noteId= :id";
		Query query = session.createQuery(queryStr);
		query.setParameter("id", noteId);
		Note note = (Note) query.uniqueResult();
		session.delete(note);
		session.flush();
		return true;

	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	public List<Note> getAllNotes() {

		Session session = sessionFactory.getCurrentSession();
		// Note notes1=null;
		String queryStr = "from Note order by createdAt DESC";
		List<Note> noteList = session.createQuery(queryStr).getResultList();
		session.flush();
		return noteList;
	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	public Note getNoteById(int noteId) {
		Session session = sessionFactory.getCurrentSession();
		String queryStr = "from Note where noteId= :id";
		Query query = session.createQuery(queryStr);
		query.setParameter("id", noteId);
		Note note = (Note) query.uniqueResult();
		session.flush();
		return note;

	}

	/* Update existing note */

	public boolean UpdateNote(Note note) {
		Session session = sessionFactory.getCurrentSession();
		session.update(note);
		session.flush();
		return true;


	}

}
