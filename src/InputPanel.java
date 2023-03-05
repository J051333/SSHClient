import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputPanel extends JPanel {
    public JTextField inputField;
    private final JButton goButton;
    public InputPanel() {
        inputField = new JTextField("Command");
        goButton = new JButton("RUN");

        goButton.addActionListener(e -> SSHHandler.handler.runSSH(inputField.getText()));

        setLayout(new GraphPaperLayout(new Dimension(20, 20)));
        add(inputField, new Rectangle(4, 2, 10, 2));
        add(goButton, new Rectangle(15, 2, 4, 2));
    }
}
