package interface_adapter.search;
import interface_adapter.ViewModel;
import interface_adapter.convert.ConvertState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel{
    public final String TITLE_LABEL = "Search View";

    private SearchState state = new SearchState();

    public static final String Search_BUTTON_LABEL = "Search";
    public static final String HOME_BUTTON_LABEL = "Home";
    private String loggedInUser;

    public SearchViewModel() {
        super("search");
    }

    public void setState(SearchState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchState getState() {
        return state;
    }
}
