package view;

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

    JLabel username;

    final JButton logOut;
    final JButton exchange;

    /**
     * A window with a title and a JButton.
     */
    public AccountView(AccountViewModel accountViewModel) {
        this.accountViewModel = accountViewModel;
        this.accountViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Account Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        logOut = new JButton(accountViewModel.LOGOUT_BUTTON_LABEL);
        exchange = new JButton(accountViewModel.EXCHANGE_BUTTON_LABEL);
        buttons.add(logOut);
        buttons.add(exchange);

        logOut.addActionListener(this);
        exchange.addActionListener(this);

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
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AccountState state = (AccountState) evt.getNewValue();
        username.setText(state.getUsername());
    }
}