package interface_adapter.account;

import interface_adapter.ViewModel;
import interface_adapter.account.AccountState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AccountViewModel extends ViewModel {
    public final String TITLE_LABEL = "Account View";

    private AccountState state = new AccountState();

    public static final String LOGOUT_BUTTON_LABEL = "Log out";
    private String loggedInUser;

    public AccountViewModel() {
        super("logged in");
    }

    public void setState(AccountState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public AccountState getState() {
        return state;
    }


    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}