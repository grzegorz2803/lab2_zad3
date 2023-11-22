import java.util.Random;
import java.util.concurrent.Callable;

public class Task implements Callable {
    private String name;

    public Task(String name){
        this.name = name;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(10000);
        return  "Wynik zadania "+name;
    }
    public  String getName(){
        return name;
    }
}
