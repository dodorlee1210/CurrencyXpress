package interface_adapter.convert;

import interface_adapter.ViewModel;
import interface_adapter.account.AccountState;

import javax.swing.text.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ConvertViewModel extends ViewModel {
    public final String TITLE_LABEL = "Convert View";

    private ConvertState state = new ConvertState();

    public static final String CONVERT_BUTTON_LABEL = "Convert";
    public static final String HOME_BUTTON_LABEL = "Home";
    private String loggedInUser;

    public ConvertViewModel() {
        super("convert");
    }

    public void setState(ConvertState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ConvertState getState() {
        return state;
    }
}
