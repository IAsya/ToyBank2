package ru.javaSchool.toyBank2;

public class RequestHandler extends Thread {
    FrontSystem frontSystem;
    BackSystem backSystem;
    String name;

    public RequestHandler(FrontSystem frontSystem, BackSystem backSystem, String name) {
        this.frontSystem = frontSystem;
        this.backSystem = backSystem;
        this.name = name;
    }

    public void run() {
        while (true) {
            Request newRequest = null;
            try {
                newRequest = frontSystem.takeRequest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ": Получена заявка на отработку по клиенту " + newRequest.clientName);
            backSystem.acceptRequest(newRequest, this);
        }
    }
}
