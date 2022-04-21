package com.bus.mythread;

class BankBook extends Thread {
    AccountThread account;
    private boolean f = true;
    public BankBook(AccountThread account) {
        this.account = account;
    }

    @Override
    public void run() {
        while (f) {
            if (account.getNumber() > 0) {
                account.out(1000);
            } else {
                break;
            }
        }
    }
}

class AccountThread {
    private  int number = 100000;
    private boolean f = true;

    public synchronized void out(int num) {
        if (number > 0) {
            number = number - num;
            System.out.println(Thread.currentThread().getName() + " : 取走" + num + "元，余额为" + number + "元");
        } else {
            System.out.println("账户余额不足！");
        }
    }

    public int getNumber() {
        return number;
    }
}

public class TestAccount {
    public static void main(String[] args) {
        AccountThread accountThread = new AccountThread();
        BankBook bankBook01 = new BankBook(accountThread);
        BankBook bankBook02 = new BankBook(accountThread);
        bankBook01.setName("张三");
        bankBook02.setName("妻子");
        bankBook01.start();
        bankBook02.start();
    }
}
