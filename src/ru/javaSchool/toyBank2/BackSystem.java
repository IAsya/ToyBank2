package ru.javaSchool.toyBank2;

public class BackSystem {
    private long balance;

    public BackSystem(long balance) {
        this.balance = balance;
    }

    public synchronized void acceptRequest(Request request, RequestHandler requestHandler) {
        if (request.operationType == OperationType.CREDIT) {
            try {
                withdraw(request, requestHandler);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (request.operationType == OperationType.REPAYMENT) {
            deposit(request, requestHandler);
        }
    }
    public synchronized void withdraw(Request request, RequestHandler requestHandler) throws InterruptedException {
        if (request.amount < 0) {
            System.out.println("Бэк система: Сумма в заявке не может быть отрицательной");
            return;
        }

        if (balance > request.amount) {
            balance -= request.amount;
            System.out.println("Бэк система: Заявка {clientThreadName = "+ request.clientName +" amount = "
                    + request.amount +" requestType = "+ request.operationType + "} Успешно выполнена. Получена от "+ requestHandler.name +
                            " Баланс банка " + balance);
        }
        else {
            System.out.println("Бэк система: Заявка {clientThreadName = "+ request.clientName +" amount = "
                    + request.amount +" requestType = "+ request.operationType + "} Не выполнена. Получена от "+ requestHandler.name +
                    " Сумма больше баланса банка. Баланс банка " + balance);
            wait();
        }
    }

    public synchronized void deposit(Request request, RequestHandler requestHandler) {
        if (request.amount < 0) {
            System.out.println("Бэк система: Сумма в заявке не может быть отрицательной");
            return;
        }
        balance += request.amount;
        System.out.println("Бэк система: Заявка {clientThreadName = "+ request.clientName +" amount = "
                + request.amount +" requestType = "+ request.operationType + "} Успешно выполнена. Баланс банка " + balance);
        notifyAll();
    }
}
