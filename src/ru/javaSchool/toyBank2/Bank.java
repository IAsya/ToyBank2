package ru.javaSchool.toyBank2;
import java.util.concurrent.*;


public class Bank {
    public static final int CLIENT_AMOUNT = 4;
    public static final int REQUEST_HANDLER_AMOUNT = 2;
    public static void main(String[] args) {
        BackSystem backSystem = new BackSystem(1000);
        FrontSystem frontSystem = new FrontSystem();
        ExecutorService executor = Executors.newFixedThreadPool(CLIENT_AMOUNT);
        ExecutorService executorHandler = Executors.newFixedThreadPool(CLIENT_AMOUNT);

        for(int i=1; i <= CLIENT_AMOUNT ; i++)
            executor.execute(new Client("Клиент №" + i, frontSystem));

        for (int j = 1;j  <= REQUEST_HANDLER_AMOUNT; j++) {
            executorHandler.execute(new RequestHandler(frontSystem, backSystem,"Обработчик заявок №" + j));
        }

    }

}
