import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false; // Insufficient funds
        }
    }
}

public class ATM extends JFrame implements ActionListener {
    private final BankAccount account;
    private final JTextField amountField;
    private final JTextArea displayArea;

    public ATM(BankAccount account) {
        this.account = account;

        setTitle("ATM Simulator");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField();
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton balanceButton = new JButton("Check Balance");

        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        balanceButton.addActionListener(this);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(balanceButton);

        add(panel, BorderLayout.CENTER);
        add(displayArea, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        double amount;

        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException ex) {
            displayArea.setText("Invalid input.");
            return;
        }

        if ("Withdraw".equals(action)) {
            if (account.withdraw(amount)) {
                displayArea.setText("Withdrawal successful. New balance: " + account.getBalance());
            } else {
                displayArea.setText("Insufficient funds.");
            }
        } else if ("Deposit".equals(action)) {
            account.deposit(amount);
            displayArea.setText("Deposit successful. New balance: " + account.getBalance());
        } else if ("Check Balance".equals(action)) {
            displayArea.setText("Current balance: " + account.getBalance());
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0);
        new ATM(account);
    }
}

