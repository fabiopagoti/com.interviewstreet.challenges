package com.interviewstreet.challenges;

import java.util.ArrayList;
import java.util.Collection;

public class Task implements Comparable<Task>{

	protected static Collection<Task> tasks_list;

	protected int id; 				//1 <= T <= 100000
	protected int deadline;			//	1 <= Di <= 100000
	protected int delay;
	protected int minutes;			//	1 <= Mi <= 1000
	protected int minutes_worked;
	protected int minutes_remaining;

	public String toString() {
		return 
		" I:" + this.id + 
		" D:" + this.deadline +
		" M:" + this.minutes +
		" W:" + this.minutes_worked +
		" R:" + this.minutes_remaining +
		" L:" + this.delay;
	}
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
		this.minutes_remaining = this.minutes - this.minutes_worked;
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
		Task.tasks_list = new ArrayList<Task>(_tasks.size());
		if (_tasks != null) {
			Task.tasks_list.addAll(_tasks);	
		}
	}

	public static Task getBestTaskToMinimizeDeadline(int _time) {
		Task best_task = null;
		int best_task_exp_delay;
		int current_task_exp_delay;

		for (Task task : Task.tasks_list) {

			if (task.getMinutes_remaining() > 0) {
				best_task = task;
			}

			current_task_exp_delay = task.calculateExpectedDelay(_time);
			best_task_exp_delay = best_task.calculateExpectedDelay(_time);

			if (current_task_exp_delay > best_task_exp_delay){			
				best_task = task ;}
		}
		return best_task;
		
	}

	public static boolean isTasksCompleted() {
		for (Task current_task : Task.tasks_list) {
			if (!current_task.isCompleted()) {
				return false;
			}  
		}
		return true;
	}

	public static Collection<Task> getTasks_list() {
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
	@Override
	public int compareTo(Task o) {
		if (this.getId() == o.getId()) {
			return 0;
		} else {
			if (this.getId() > o.getId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

}
