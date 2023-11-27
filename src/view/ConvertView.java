package view;

import interface_adapter.convert.ConvertController;
import interface_adapter.convert.ConvertState;
import interface_adapter.convert.ConvertViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ConvertView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "convert";
    private final ConvertViewModel convertViewModel;

    final JTextField symbolBInputField = new JTextField(15);
    private final JLabel symbolBErrorField = new JLabel();
    final JTextField currencyBInputField = new JTextField(15);
    private final JLabel currencyBErrorField = new JLabel();
    final JTextField symbolAInputField = new JTextField(15);
    private final JLabel symbolAErrorField = new JLabel();

    final JButton convert;
    final JButton home;

    private final ConvertController convertController;

    public ConvertView(ConvertViewModel convertViewModel, ConvertController controller) {
        this.convertViewModel = convertViewModel;
        this.convertController = controller;
        this.convertViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Convert Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel symbolBInfo = new LabelTextPanel(
                new JLabel("Code for Currency to Convert"), symbolBInputField);
        LabelTextPanel currencyBInfo = new LabelTextPanel(
                new JLabel("Amount to Exchange"), currencyBInputField);
        LabelTextPanel symbolAInfo = new LabelTextPanel(
                new JLabel("Code for Currency to Receive"), symbolAInputField);

        JPanel buttons = new JPanel();
        convert = new JButton(convertViewModel.CONVERT_BUTTON_LABEL);
        buttons.add(convert);
        home = new JButton(convertViewModel.HOME_BUTTON_LABEL);
        buttons.add(home);

        convert.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(convert)) {
                            ConvertState currentState = convertViewModel.getState();

                            convertController.execute(
                                    currentState.getSymbolB(),
                                    currentState.getCurrencyB(),
                                    currentState.getSymbolA()
                            );
                        }
                    }
                }
        );

        home.addActionListener(this);

        symbolBInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ConvertState currentState = convertViewModel.getState();
                currentState.setSymbolB(symbolBInputField.getText() + e.getKeyChar());
                convertViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        currencyBInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ConvertState currentState = convertViewModel.getState();
                currentState.setCurrencyB(currencyBInputField.getText() + e.getKeyChar());
                convertViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        symbolAInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ConvertState currentState = convertViewModel.getState();
                currentState.setSymbolA(symbolAInputField.getText() + e.getKeyChar());
                convertViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


        this.add(title);
        this.add(symbolBInfo);
        this.add(symbolBErrorField);
        this.add(currencyBInfo);
        this.add(currencyBErrorField);
        this.add(symbolAInfo);
        this.add(symbolAErrorField);
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
        ConvertState state = (ConvertState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(ConvertState state) {
        symbolBInputField.setText(state.getSymbolB());
        currencyBInputField.setText(state.getCurrencyB());
        symbolAInputField.setText(state.getSymbolA());
    }
}
