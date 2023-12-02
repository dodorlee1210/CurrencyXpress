package interface_adapter.ViewExchangeHistory;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewExchangeHistoryViewModel extends ViewModel {

    public final String TITLE_LABEL = "View Exchange History View";

    private ViewExchangeHistoryState state = new ViewExchangeHistoryState();

    public ViewExchangeHistoryViewModel() {
        super("view exchange history");
    }

    public void setState(ViewExchangeHistoryState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void onExchangeHistoryRequested() {
        // Notify the presenter that the user has requested to view exchange history
        // This could involve calling a method on the presenter or firing an event
    }

    public ViewExchangeHistoryState getState() {
        return state;
    }
}
