package com.interviewstreet.challenges;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TaskScheduling implements IChallenge{
	/*
	 * 

	Task Scheduling (30 Points)
	You have a long list of tasks that you need to do today. Task i is specified by the deadline by which you have to complete it (Di) 
	and the number of minutes it will take you to complete the task (Mi). You need not complete a task at a stretch. You can complete
	 a part of it, switch to another task and then switch back.

	You've realized that it might not actually be possible complete all the tasks by their deadline, so you have decided to complete 
	them so that the maximum amount by which a task's completion time overshoots its deadline is minimized.

	Input:
	The first line contains the number of tasks T. Each of the next T lines contains two integers Di and Mi.

	Output:
	Output T lines. The ith line should contain the minimum maximum overshoot you can obtain by optimally scheduling the first i tasks 
	on your list. See the sample input for clarification.

	Constraints:
	1 <= T <= 100000
	1 <= Di <= 100000
	1 <= Mi <= 1000
	Sample Input:
	5
	2 2
	1 1
	4 3
	10 1
	2 1

	Sample Output:
	0
	1
	2
	2
	3

	With only the first task, it can be completed in 2 minutes, and so you won't overshoot the deadline.

	With the first two tasks, the optimal schedule can be:
	time 1: task 2
	time 2: task 1 
	time 3: task 1
	We've overshot task 1 by 1 minute, hence returning 1 

	With the first three tasks, the optimal schedule can be:
	time 1 : task 2
	time 2 : task 1
	time 3 : task 3
	time 4 : task 1
	time 5 : task 3
	time 6 : task 3
	Task 1 has a deadline 2, and it finishes at time 4. So it exceeds its deadline by 2.
	Task 2 has a deadline 1, and it finishes at time 1. So it exceeds its deadline by 0.
	Task 3 has a deadline 4, and it finishes at time 6. So it exceeds its deadline by 2.

The maximum time by which you overshoot is thus 2. No schedule can do better than this.
 Enter your code here. Read input from STDIN. Print output to STDOUT */


	/**
	 * @param args
	 */
	public static void main(String[] args) {


		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		Collection<Task> tasks_to_be_optmized =null;

		int task_number = 0;

		try {
			task_number = Integer.valueOf(in.readLine());

			tasks_to_be_optmized = new ArrayList<Task>(task_number);

			out.write("Task Number:\r" + task_number);

			// Collecting Tasks
			for (int current_task = 0; current_task < task_number; current_task++) {
				int task_deadline = Integer.valueOf(in.readLine());
				int task_minutes = Integer.valueOf(in.readLine());
				tasks_to_be_optmized.add(new Task(current_task, task_deadline, task_minutes));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Task.initTaskList(tasks_to_be_optmized);

	}

	@Override
	public void solveChallenge() {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Task task_to_be_done;
		for (int time = 0; time < Task.sumRemainingMinutes(); time++) {
			task_to_be_done = Task.getBestTaskToMinimizeDeadline();
			try {
				task_to_be_done.work();
			} catch (TaskCompleted e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (Task.isTasksCompleted()) {
			for (Iterator it = Task.getTasks_list().iterator(); it.hasNext();) {
				task_to_be_done = (Task) it.next();
				try {
					out.write(String.valueOf(task_to_be_done.getDelay()));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

}
