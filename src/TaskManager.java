import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TaskManager {
    private ExecutorService executorService;
    private List<Future<String>> taskResults;

    public TaskManager(){
        this.executorService = Executors.newFixedThreadPool(5);
        this.taskResults = new ArrayList<>();
    }
    public void  submitTask(Task task){
        Future<String> futureResult = executorService.submit(task);
        taskResults.add(futureResult);
    }
    public void displayTasks() {
        System.out.println("Lista zadań:");
        for (int i = 0; i < taskResults.size(); i++) {
            Future<String> future = taskResults.get(i);
            Callable<String> task = (Callable<String>) future;
            if (task instanceof Task) {
                System.out.println(i + ": " + ((Task) task).getName());
            } else {
                System.out.println(i + ": Nieznane zadanie");
            }
        }
    }
    public  void  checkTaskStatus(int index){
        Future<String> future  = taskResults.get(index);
        if(future.isDone()){
            System.out.println("Zadanie "+index+" jest zakończone.");
        }else if (future.isCancelled()){
            System.out.println("Zadanie "+index+" został anulowany.");
        }else {
            System.out.println("Zadanie "+index+" jest w trakcie wykonywania.");
        }
    }
    public void cancelTask(int index){
        Future<String> future = taskResults.get(index);
        if(!future.isDone() && !future.isCancelled()){
            future.cancel(true);
            System.out.println("Zadanie "+index+" zostało anbulowane");
        }
    }
    public void displayTaskResult(int index){
        Future<String> future = taskResults.get(index);
        try{
            if(future.isDone()){
                System.out.println("Wynik zadania "+index+": "+future.get());
            }else {
                System.out.println("Task "+index+" jest w trakcie wykonywania. Brak wyniku ");
            }
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }
    public void shutdown(){
        executorService.shutdown();
    }

}
