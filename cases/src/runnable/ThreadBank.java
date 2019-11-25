package runnable;

public class ThreadBank {
    public static void main(String[] args) {

    }
}

class DrawMoneyRunnabel implements Runnable {
    private Account account;
    private double drawAmount;

    public DrawMoneyRunnabel(Account account, double drawAmount) {
        super();
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        if (account.getBalance() >= drawAmount) {
            System.out.println("取钱成功，取出钱数为：" + drawAmount);

        }
    }
}

class Account {
    private String accountNo;
    private double balance;

    public Account() {

    }

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}