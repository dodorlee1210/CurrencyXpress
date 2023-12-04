package view;

import interface_adapter.view_exchangehistory.ViewExchangeHistoryState;
import interface_adapter.view_exchangehistory.ViewExchangeHistoryViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewExchangeHistoryView extends JFrame implements PropertyChangeListener {

    private final ViewExchangeHistoryViewModel viewModel;

    private JLabel titleLabel;
    private JLabel exchangeHistoryLabel;
    private JTextArea exchangeHistoryTextArea;
    private JButton viewExchangeHistoryButton;

    public ViewExchangeHistoryView(ViewExchangeHistoryViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        initializeComponents();
        layoutComponents();
        setProperties();
    }

    private void initializeComponents() {
        titleLabel = new JLabel(viewModel.TITLE_LABEL);

        exchangeHistoryLabel = new JLabel("Exchange History:");
        exchangeHistoryTextArea = new JTextArea();
        exchangeHistoryTextArea.setEditable(false);

        viewExchangeHistoryButton = new JButton("View Exchange History");
        viewExchangeHistoryButton.addActionListener(new ViewExchangeHistoryButtonListener());
    }

    private void layoutComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel exchangeHistoryPanel = new JPanel(new GridLayout(2, 1));
        exchangeHistoryPanel.add(exchangeHistoryLabel);
        exchangeHistoryPanel.add(exchangeHistoryTextArea);

        mainPanel.add(exchangeHistoryPanel, BorderLayout.CENTER);
        mainPanel.add(viewExchangeHistoryButton, BorderLayout.SOUTH);

        this.add(mainPanel);
    }

    private void setProperties() {
        this.setTitle("View Exchange History");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void updateExchangeHistoryDisplay() {
        ViewExchangeHistoryState state = viewModel.getState();
        exchangeHistoryTextArea.setText(state.getExchangeHistory().toString());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            updateExchangeHistoryDisplay();
        }
    }

    private class ViewExchangeHistoryButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewModel.onExchangeHistoryRequested();
        }
    }
}