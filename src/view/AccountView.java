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

public class AccountView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final AccountViewModel accountViewModel;
    private ViewExchangeHistoryViewModel viewExchangeHistoryViewModel;

    JLabel username;

    final JButton logOut;
    final JButton exchange;
    final JButton viewExchangeHistory;

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
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

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

                            accountController.execute(
                                    currentState.getUsername()
                            );
                        }
                    }
                }
        );

        logOut.addActionListener(this);
        exchange.addActionListener(this);
        viewExchangeHistory.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logOut)) {
            System.out.println("Click " + evt.getActionCommand());
        } else if (evt.getSource().equals(viewExchangeHistory)) {
            // Show exchange history popup directly from AccountView
            viewExchangeHistoryViewModel.onExchangeHistoryRequested();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AccountState state = (AccountState) evt.getNewValue();
        username.setText(state.getUsername());
    }
}