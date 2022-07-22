package com.skillstorm.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="expenses")
public class Expenses {

	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "ExpenseName")
	private String name;
	
	@Column(name = "Reason")
	private String reason;
	
	@Column(name = "Notes")
	private String notes;


	@ManyToOne
	@JoinColumn(name = "StatusId")
	private ReimbStatus status;

	
	
	public Expenses() {
		super();
	}
	public Expenses(int id, String name, String reason, String notes, ReimbStatus status) {
		this.id = id;
		this.name = name;
		this.reason = reason;
		this.notes = notes;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status.getStatus();
	}

	public void setStatus(ReimbStatus status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

	@Override
	public String toString() {
		return "Expenses [id=" + id + ", name=" + name + ", notes=" + notes + ", reason=" + reason + ", status=" + status + "]";
	}
	
}
