package com.interviewstreet.challenges.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.interviewstreet.challenges.Task;
import com.interviewstreet.challenges.TaskCompleted;

public class TaskTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testTask() {
		Task actual = new Task(0, 5, 2);
		Task expected = new Task(0, 5, 2);
		expected.setDelay(0);
		expected.setMinutes_remaining(2);
		expected.setMinutes_worked(0);	
		assertTrue(actual.compareTo(expected) == 0);
	}

	@Test
	public final void testCalculateRemainingMinutes() {
		Task task;
		int act_remaining_minutes;
		
		task = new Task(0, 1, 1);
		act_remaining_minutes = task.calculateRemainingMinutes(); 
		assertEquals(task.getMinutes_remaining(), act_remaining_minutes);
		
		task = new Task(0, 2, 2);
		act_remaining_minutes = task.calculateRemainingMinutes(); 
		assertEquals(task.getMinutes_remaining(), act_remaining_minutes);
		
		task = new Task(0, 2, 1);
		act_remaining_minutes = task.calculateRemainingMinutes(); 
		assertEquals(task.getMinutes_remaining(), act_remaining_minutes);
		
		task = new Task(0, 1, 2);
		act_remaining_minutes = task.calculateRemainingMinutes(); 
		assertEquals(task.getMinutes_remaining(), act_remaining_minutes);
		
	}

	@Test
	public final void testWork() {
		Task task;
//		int[] {Worked / Remaining}
		try {

			task = new Task(0, 1, 1);
			assertArrayEquals(new int[] {0,1}, new int[] {task.getMinutes_worked(), task.getMinutes_remaining()});

			task = new Task(0, 2, 1);
			task.work(0);
			assertArrayEquals(new int[] {1,0}, new int[] {task.getMinutes_worked(), task.getMinutes_remaining()});
			
			task = new Task(0, 2, 2);
			assertArrayEquals(new int[] {0,2}, new int[] {task.getMinutes_worked(), task.getMinutes_remaining()});
			
			task = new Task(0, 2, 2);
			task.work(0);
			task.work(1);
			assertArrayEquals(new int[] {2,0}, new int[] {task.getMinutes_worked(), task.getMinutes_remaining()});
			
			
		} catch (TaskCompleted e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void testCalculateExpectedDelay() {
		Task task;
		int actual_delay;
		
		task = new Task(0, 0, 0);
		actual_delay = task.calculateExpectedDelay(0);
		assertEquals(0, actual_delay);
		
		task = new Task(0, 0, 1);
		actual_delay = task.calculateExpectedDelay(0);
		assertEquals(1, actual_delay);
		
		task = new Task(0, 0, 2);
		actual_delay = task.calculateExpectedDelay(0);
		assertEquals(2, actual_delay);

		task = new Task(0, 1, 0);
		actual_delay = task.calculateExpectedDelay(1);
		assertEquals(0, actual_delay);
		
		task = new Task(0, 1, 1);
		actual_delay = task.calculateExpectedDelay(1);
		assertEquals(1, actual_delay);
		
		task = new Task(0, 1, 2);
		actual_delay = task.calculateExpectedDelay(1);
		assertEquals(2, actual_delay);

		task = new Task(0, 3, 1);
		actual_delay = task.calculateExpectedDelay(0);
		assertEquals(-2, actual_delay);
		
		task = new Task(0, 3, 1);
		actual_delay = task.calculateExpectedDelay(1);
		assertEquals(-1, actual_delay);
		
		task = new Task(0, 3, 1);
		actual_delay = task.calculateExpectedDelay(2);
		assertEquals(0, actual_delay);
		
	}

	@Test
	public final void testIsCompleted() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSumRemainingMinutes() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testInitTaskList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetBestTaskToMinimizeDeadline() {
		ArrayList<Task> task_list;
		Task actual_task;
		Task expect_task;

		// ---
		task_list = new ArrayList<Task>(1);
		actual_task = new Task(0, 1, 1);
		task_list.add(actual_task);
		expect_task = actual_task;
		
		Task.initTaskList(task_list);
		actual_task = Task.getBestTaskToMinimizeDeadline(0);
		
		assertEquals(expect_task, actual_task);

		// ---
		task_list = new ArrayList<Task>(2);
		actual_task = new Task(0, 1, 1);
		task_list.add(actual_task);
		expect_task = actual_task;
		actual_task = new Task(1, 2, 1);
		task_list.add(actual_task);
		
		Task.initTaskList(task_list);
		actual_task = Task.getBestTaskToMinimizeDeadline(0);
		
		assertEquals(expect_task, actual_task);
		
		// ---
		task_list = new ArrayList<Task>(3);
		actual_task = new Task(0, 2, 2);
		task_list.add(actual_task);
		
		actual_task = new Task(1, 1, 1);
		task_list.add(actual_task);
		expect_task = actual_task;
		
		actual_task = new Task(2, 4, 3);
		task_list.add(actual_task);
		
		Task.initTaskList(task_list);
		actual_task = Task.getBestTaskToMinimizeDeadline(0);
		
		assertEquals(expect_task, actual_task);
		
	}

	@Test
	public final void testIsTasksCompleted() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetTasks_list() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetTasks_list() {
		fail("Not yet implemented"); // TODO
	}

}
