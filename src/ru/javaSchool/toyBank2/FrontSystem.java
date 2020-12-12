package ru.javaSchool.toyBank2;

 import java.util.concurrent.*;

import java.util.LinkedList;

public class FrontSystem {
    LinkedBlockingQueue<Request> requestQueue = new LinkedBlockingQueue<>(MAX_SIZE);
    public static final int MAX_SIZE = 4;

    public void addRequest(Request newRequest) throws InterruptedException {
        requestQueue.put(newRequest);
    }

    public  Request takeRequest() throws InterruptedException {
        Request returnRequest = requestQueue.take();
        return returnRequest;

    }
}
