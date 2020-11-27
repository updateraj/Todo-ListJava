import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;

public class ToDoList {

	private HashMap<String, Task> tasks = new HashMap<String, Task>();
	
	public void addTask (Task task) {
		tasks.put(task.getDescription(), task);
	}
	public void completeTask(String description) {
		Task task = null;
		if ((task = tasks.get(description)) != null){
			task.setComplete(true);
		};
	}
	public boolean getStatus(String description) {
		Task task = null;
		if ((task = tasks.get(description)) != null){
			return task.isComplete();
		};
		return false;
	}
	public Task getTask(String description) {
		return tasks.get(description);
	}
	public Task removeTask(String description) {
		return tasks.remove(description);
	}
	public Collection<Task> getAllTasks() {
		return tasks.values();
	}
	public Collection<Task> getCompletedTasks() {
		Collection<Task> completedTasks = new ArrayList<Task> ();
		Collection<Task> allTasks = new ArrayList<Task> ();
		allTasks = getAllTasks();
		for (Task task: allTasks) 
			if (task.isComplete() == true) completedTasks.add(task);
		return completedTasks;
	}
	public void archiveTasks() {
		Collection<Task> completedTasks = new ArrayList<Task> ();
		Collection<Task> allTasks = getAllTasks();
		for (Task task: allTasks) 
			if (task.isComplete() == true) completedTasks.add(task);
		for (Task completedTask: completedTasks)
			tasks.remove(completedTask.getDescription());
		
	}
	
	public void ExportTask(String filename) {
		FileWriter  fw = null;
		try {
			File file = new File(filename);
			fw = new FileWriter(file);
			Collection<Task> allTasks = new ArrayList<Task> ();
			allTasks = getAllTasks();
			for (Task task: allTasks) {
				fw.append(task.getDescription()+","
						+ (task.isComplete() ? "true":"false") +"\n");
			}
			fw.close();
		}catch(Exception e) {
			
		}finally {
			 try{ if(fw!=null)fw.close(); } catch(Exception e) {}
		}
		
	}
}
