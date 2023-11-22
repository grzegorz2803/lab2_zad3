import java.util.Scanner;
import java.util.concurrent.ExecutionException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        taskManager.submitTask(new Task("A"));
        taskManager.submitTask(new Task("B"));
        taskManager.submitTask(new Task("C"));

        boolean exit = false;

        while (!exit){
            System.out.println("Menu:");
            System.out.println("1. Wyświetl listę zadań");
            System.out.println("2. Sprawdź stan zadania");
            System.out.println("3. Anuluj zadanie");
            System.out.println("4. Wyświetl wynik zadania");
            System.out.println("5. Wyjdź");

            System.out.println("Wybierz opcję: ");
            int option = scanner.nextInt();

            switch (option){
                case 1:
                    taskManager.displayTasks();
                    break;
                case 2:
                    System.out.println("Podaj numer zadania: ");
                    int checkIndex = scanner.nextInt();
                    taskManager.checkTaskStatus(checkIndex);
                    break;
                case 3:
                    System.out.println("Podaj numer zadania do anulowania: ");
                    int cancelIndex = scanner.nextInt();
                    taskManager.cancelTask(cancelIndex);
                    break;
                case 4:
                    System.out.println("Podaj numer zadania do wyświetlenia wyniku: ");
                    int resultIndex = scanner.nextInt();
                    taskManager.displayTaskResult(resultIndex);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja. Spróbuj ponownie");
                    break;
            }
        }
        taskManager.shutdown();
        scanner.close();
    }
}