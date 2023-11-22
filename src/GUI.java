import javax.swing.*;
import java.awt.*;
import java.lang.String;

public class GUI {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("CurrencyXpress");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(400, 500);

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            JPanel startCard = createStartCard();
            JPanel signUpCard = createSignUpCard();

            cardPanel.add(startCard, "StartCard");
            cardPanel.add(signUpCard, "SignUpCard");

            JButton signUpButton = new JButton("Sign Up");
            signUpButton.addActionListener(e -> cardLayout.show(cardPanel, "StartCard"));

            JButton logInButton = new JButton("Log In");
            signUpButton.addActionListener(e -> cardLayout.show(cardPanel, "StartCard"));

            JButton createButton = new JButton("Create Account");
            createButton.addActionListener(e -> cardLayout.show(cardPanel, "SignUpCard"));

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(signUpButton);
            buttonPanel.add(logInButton);

            frame.getContentPane().add(cardPanel, BorderLayout.CENTER);
            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

            frame.setVisible(true);

        });
    }


    private static JPanel createStartCard() {
        JPanel startCard = new JPanel();
        startCard.setLayout(new BorderLayout());

        JLabel infoLabel = new JLabel("Welcome to CurrencyXPress!");
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));

        startCard.add(infoLabel, BorderLayout.CENTER);

        return startCard;
    }

    private  static JPanel createSignUpCard() {
        JPanel signUpCard = new JPanel();
        signUpCard.setLayout(new BorderLayout());

        JTextField username = new JTextField("username");
        JTextField password = new JTextField("password");

        signUpCard.add(username);
        signUpCard.add(password);

        return signUpCard;
    }
}