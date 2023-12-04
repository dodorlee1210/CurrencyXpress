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

    public ViewExchangeHistoryState getState() {
        return state;
    }

    public void onExchangeHistoryReturned() {
    }

    public void onExchangeHistoryRequested() {
    }
}