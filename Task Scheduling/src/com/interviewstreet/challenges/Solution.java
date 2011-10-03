package com.interviewstreet.challenges;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution implements IChallenge{

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Task> all_tasks = null;
		ArrayList<Task> tasks_to_be_optimized = null;

		int max_task_number = 0;
		Solution schedule = new Solution();

		try {

			max_task_number = Integer.valueOf(in.readLine());
			all_tasks = new ArrayList<Task>(max_task_number);
			for (int current_task = 0; current_task < max_task_number; current_task++) {
				String line = in.readLine();
				args = line.split(" ");
				int task_deadline = Integer.parseInt(args[0]);
				int task_minutes =  Integer.parseInt(args[1]);
				all_tasks.add(new Task(current_task, task_deadline, task_minutes));
			}

			// Collecting Tasks
			for (int all_tasks_index = 0; all_tasks_index < max_task_number; all_tasks_index++) {
				tasks_to_be_optimized = new ArrayList<Task>(all_tasks_index+1);

				for (int current_task_index = 0; current_task_index <= all_tasks_index; current_task_index++) {
					tasks_to_be_optimized.add((Task) all_tasks.get(current_task_index).clone());
				}
				Task.initTaskList(tasks_to_be_optimized);
				schedule.solveChallenge();
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void solveChallenge() {

		Task task_to_be_done;;
		int allWorkingMinutes = Task.sumRemainingMinutes();

		for (int time = 0; time < allWorkingMinutes; time++) {
			task_to_be_done = Task.getBestTaskToMinimizeDeadline(time);
			try {
				task_to_be_done.work(time);
			} catch (TaskCompleted e) {
				e.printStackTrace();
			}
		}
		Task.printMaximumTaskDelay();	

	}
}
