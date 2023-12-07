package view;

import interface_adapter.view_exchangehistory.ViewExchangeHistoryViewModel;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountState;
import interface_adapter.account.AccountViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final AccountViewModel accountViewModel;
    private ViewExchangeHistoryViewModel viewExchangeHistoryViewModel;

    JLabel username;
    JLabel balance;
    JLabel bank;

    final JButton logOut;
    final JButton exchange;
    final JButton viewExchangeHistory;
    private JTable otherCurrencies;

    /**
     * A window with a title and a JButton.
     */
    public AccountView(AccountViewModel accountViewModel, AccountController accountController) {
        this.accountViewModel = accountViewModel;
        this.accountViewModel.addPropertyChangeListener(this);
        this.viewExchangeHistoryViewModel = viewExchangeHistoryViewModel;

        viewExchangeHistory = new JButton("Exchange History");
        viewExchangeHistory.addActionListener(this);

        JLabel title = new JLabel("Account Screen");

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();
        JLabel balanceInfo = new JLabel("Current Balance: ");
        balance = new JLabel();
        JLabel bankInfo = new JLabel("Bank Selected: ");
        bank = new JLabel();

//        DefaultTableModel tableModel = new DefaultTableModel();
//        otherCurrencies = new JTable(tableModel);
        String[] column = {"Currency Code","Amount"};
        String[][] example = {{"???", "000"}};
        otherCurrencies = new JTable(example, column);


        JPanel buttons = new JPanel();
        logOut = new JButton(accountViewModel.LOGOUT_BUTTON_LABEL);
        exchange = new JButton(accountViewModel.EXCHANGE_BUTTON_LABEL);
        buttons.add(logOut);
        buttons.add(exchange);
        buttons.add(viewExchangeHistory);

        exchange.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(exchange)) {
                            AccountState currentState = accountViewModel.getState();
                            currentState.setMethod("exchange");
                            accountController.execute(currentState.getUsername(),
                                    currentState.getMethod(), currentState.getBank());
                        }
                    }
                }
        );    

        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            AccountState currentState = accountViewModel.getState();
                            currentState.setMethod("logout");
                            accountController.execute(currentState.getUsername(),
                                    currentState.getMethod(),
                                    currentState.getBank());
                        }
                    }
                }
        );
      
        viewExchangeHistory.addActionListener(this);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernamePanel.add(usernameInfo);
        usernamePanel.add(username);

        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        balancePanel.add(balanceInfo);
        balancePanel.add(balance);

        JPanel bankPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bankPanel.add(bankInfo);
        bankPanel.add(bank);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(titlePanel);
        this.add(usernamePanel);
        this.add(bankPanel);
        this.add(balancePanel);
        this.add(otherCurrencies);
        this.add(Box.createVerticalGlue());
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(viewExchangeHistory)) {
            // Show exchange history popup directly from AccountView
            viewExchangeHistoryViewModel.onExchangeHistoryRequested();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AccountState state = (AccountState) evt.getNewValue();
        username.setText(state.getUsername());
        balance.setText(state.getBalance() + " EUR");
        bank.setText(state.getBank());
        String[] column = {"Currency Code","Amount"};
        String[][] currencies = state.getCurrencies();
        otherCurrencies = new JTable(currencies, column);
        }
    }
