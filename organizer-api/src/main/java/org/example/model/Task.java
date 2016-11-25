package org.example.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {
	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private Priority priority;
	private LocalDateTime dueDateTime;
	
	protected Task() {}
	
	public Task(String description, Priority priority, LocalDateTime dueDateTime) {
		this.description = description;
		this.priority = priority;
		this.dueDateTime = dueDateTime;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public LocalDateTime getDueDateTime() {
		return dueDateTime;
	}

	public void setDueDateTime(LocalDateTime dueDateTime) {
		this.dueDateTime = dueDateTime;
	}
	
}
