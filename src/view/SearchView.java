package view;

import interface_adapter.convert.ConvertState;
import interface_adapter.search_exchangerate.SearchController;
import interface_adapter.search_exchangerate.SearchState;
import interface_adapter.search_exchangerate.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "search";
    private final SearchViewModel searchViewModel;
    private final SearchController searchController;

    private final JTextField dateInputField = new JTextField(15);
    private final JTextField baseCurrencyInputField = new JTextField(15);
    private final JTextField symbolsInputField = new JTextField(15);

    private final JLabel dateErrorField = new JLabel();
    private final JLabel baseCurrencyErrorField = new JLabel();
    private final JLabel symbolsErrorField = new JLabel();

    private final JButton search;
    private final JButton home;

    public SearchView(SearchViewModel searchViewModel, SearchController searchController) {
        this.searchViewModel = searchViewModel;
        this.searchController = searchController;
        this.searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Search Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel dateInfo = new LabelTextPanel(
                new JLabel("Date to Search"), dateInputField);
        LabelTextPanel baseCurrencyInfo = new LabelTextPanel(
                new JLabel("Base Currency Code"), baseCurrencyInputField);
        LabelTextPanel symbolsInfo = new LabelTextPanel(
                new JLabel("Currency Code to Receive"), symbolsInputField);

        JPanel buttons = new JPanel();
        search = new JButton(SearchViewModel.Search_BUTTON_LABEL);
        buttons.add(search);
        home = new JButton(SearchViewModel.HOME_BUTTON_LABEL);
        buttons.add(home);

        search.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            SearchState currentState = searchViewModel.getState();
                            displayPopUpWindow(searchController.execute(
                                    currentState.getDate(),
                                    currentState.getBaseCurrency(),
                                    currentState.getSymbols())
                            );
                        }
                    }
                }
        );

        home.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(home)) {
                            SearchState currentState = searchViewModel.getState();
                            currentState.setSymbols("HOME");

                            searchController.execute(
                                    currentState.getDate(),
                                    currentState.getBaseCurrency(),
                                    currentState.getSymbols()
                            );
                        }
                    }
                }
        );

        dateInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SearchState currentState = searchViewModel.getState();
                currentState.setDate(dateInputField.getText() + e.getKeyChar());
                searchViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        baseCurrencyInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SearchState currentState = searchViewModel.getState();
                currentState.setBaseCurrency(baseCurrencyInputField.getText() + e.getKeyChar());
                searchViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        symbolsInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SearchState currentState = searchViewModel.getState();
                currentState.setSymbols(symbolsInputField.getText() + e.getKeyChar());
                searchViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });



        this.add(title);
        this.add(dateInfo);
        this.add(dateErrorField);
        this.add(baseCurrencyInfo);
        this.add(baseCurrencyErrorField);
        this.add(symbolsInfo);
        this.add(symbolsErrorField);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        // Handle home button click or any other common actions
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState) evt.getNewValue();
        // Update UI elements based on the state
        setFields(state);
    }

    private void setFields(SearchState state) {
        dateInputField.setText(state.getDate());
        baseCurrencyInputField.setText(state.getBaseCurrency());
        symbolsInputField.setText(state.getSymbols());
    }

    public void displayPopUpWindow(String msg) {
        JOptionPane.showMessageDialog(this,
                msg, "Search Result", JOptionPane.INFORMATION_MESSAGE);
    }
}


