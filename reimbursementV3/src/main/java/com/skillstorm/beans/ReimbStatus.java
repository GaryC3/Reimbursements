package com.skillstorm.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="statustype")
public class ReimbStatus {
		@Id
		@Column(name = "StatusId")
		private int statusId;
		
		@Column(name = "Confirmation")
		private String status;
		
		@OneToMany(mappedBy = "status")
		private Set<Expenses> expenses;

		
		public ReimbStatus() {
			super();
		}
		

		public int getStatusId() {
			return statusId;
		}

		public void setStatusId(int statusId) {
			this.statusId = statusId;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Set<Expenses> getExpenses() {
			return expenses;
		}

		public void setExpenses(Set<Expenses> expenses) {
			this.expenses = expenses;
		}

		@Override
		public String toString() {
			return "ReimbStatus [statusId=" + statusId + ", status=" + status + "]";
		}
		
		
	
}
