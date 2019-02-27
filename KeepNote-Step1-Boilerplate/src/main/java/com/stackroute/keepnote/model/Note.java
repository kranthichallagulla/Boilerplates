package com.stackroute.keepnote.model;

import java.time.LocalDateTime;

/*
 * The class "Note" will be acting as the data model for the Note data in the ArrayList.
 */
public class Note {

	/*
	 * This class should have five fields (noteId, noteTitle, noteContent,
	 * noteStatus and createdAt). This class should also contain the getters and
	 * setters for the fields. The value of createdAt should not be accepted from
	 * the user but should be always initialized with the system date
	 */
	private int noteid;
	private String notetitle;
	private String notecontent;

	public int getNoteId() {
		return noteid;
	}

	public void setNoteId(int noteid) {
		this.noteid = noteid;
	}

	public String getNoteTitle() {
		return notetitle;
	}

	public void setNoteTitle(String notetitle) {
		this.notetitle = notetitle;
	}

	public String getNoteContent() {
		return notecontent;
	}

	public void setNoteContent(String notecontent) {
		this.notecontent = notecontent;
	}

	@Override
	public String toString() {
		return "Note{" +
				"noteid=" + noteid +
				", notetitle='" + notetitle + '\'' +
				", notecontent='" + notecontent + '\'' +
				", notestatus='" + notestatus + '\'' +
				", createdat=" + createdat +
				'}';
	}

	public String getNoteStatus() {
		return notestatus;
	}



	public void setNoteStatus(String notestatus) {
		this.notestatus = notestatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdat;
	}

	public void setCreatedAt(LocalDateTime createdat) {
		this.createdat = createdat;
	}

	private String notestatus;
	private LocalDateTime createdat;





	/* All the getters/setters definition should be implemented here */



	/* Override the toString() method */

}