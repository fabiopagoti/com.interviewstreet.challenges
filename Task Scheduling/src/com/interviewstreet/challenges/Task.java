package com.interviewstreet.challenges;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Task {

	private static ArrayList<Task> tasks_list;

	private int id; 				//1 <= T <= 100000
	private int deadline;			//	1 <= Di <= 100000
	private int delay;
	private int minutes;			//	1 <= Mi <= 1000
	private int minutes_worked;
	private int minutes_remaining;

	/**
	 * @param id
	 * @param deadline
	 * @param minutes
	 */
	public Task(int id, int deadline, int minutes) {
		this.id = id;
		this.deadline = deadline;
		this.delay = 0;
		this.minutes = minutes;
		this.minutes_worked = 0;
		this.minutes_remaining = this.minutes;
	}

	public int calculateRemainingMinutes(){
		minutes_remaining = minutes - minutes_worked;
		return minutes_remaining;
	}

	public void work() throws TaskCompleted {
		if (this.minutes_remaining > 0) {
			this.minutes_worked++;
			this.minutes_remaining--;
		}else{
			throw new TaskCompleted("Task:\r" + this.id + " completed");
		}
		
	}
	
	public int calculateExpectedDelay(int _current_time){
		return (this.deadline - (this.minutes_remaining + _current_time)) * -1;
	}
	
	public boolean isCompleted(){
		return (this.getMinutes_remaining() == 0) ? true : false ;
	}
	
	public static int sumRemainingMinutes(){
		int sum_minutes = 0;
		for (Task current_task : Task.tasks_list) {
			sum_minutes += current_task.getMinutes_remaining();
		}
		return sum_minutes;
	}

	public static void initTaskList(Collection<Task> _tasks) {
		if (_tasks != null) {
			Task.tasks_list.addAll(_tasks);	
		}		
		for (Task task : Task.tasks_list) {
			
		}
	}

	public static Task getBestTaskToMinimizeDeadline() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static boolean isTasksCompleted() {
		for (Task current_task : Task.tasks_list) {
			if (!current_task.isCompleted()) {
				return false;
			}  
		}
		return true;
	}
	
	public static ArrayList<Task> getTasks_list() {
		return tasks_list;
	}

	public static void setTasks_list(ArrayList<Task> tasks_list) {
		Task.tasks_list = tasks_list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getMinutes_worked() {
		return minutes_worked;
	}

	public void setMinutes_worked(int minutes_worked) {
		this.minutes_worked = minutes_worked;
	}

	public int getMinutes_remaining() {
		return minutes_remaining;
	}

	public void setMinutes_remaining(int minutes_remaining) {
		this.minutes_remaining = minutes_remaining;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

}
