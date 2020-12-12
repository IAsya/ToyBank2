package ru.javaSchool.toyBank2;

import java.util.concurrent.*;

import java.util.Random;
public class Client implements Runnable{
    final Random random = new Random();
    FrontSystem frontSystem;
    String name;

    public Client(String name,FrontSystem frontSystem) {
        this.name = name;
        this.frontSystem = frontSystem;
    }


    public void run() {
        int amount = random.nextInt(100000);
        OperationType operationType = (random.nextInt(2) == 1) ? OperationType.REPAYMENT : OperationType.CREDIT;
        Request newRequest = new Request(operationType,amount,name);
        try {
            System.out.println(name + ": заявка {clientThreadName = '"+ name +"' amount = " + amount +" requestType = "+ operationType +"} отправлна в банк");
            frontSystem.addRequest(newRequest);
        } catch (InterruptedException e) {
          System.out.println("InterruptedException");
        }


    }

}
