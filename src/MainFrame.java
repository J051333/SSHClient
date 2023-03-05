import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private InputPanel inputPanel;
    private OutputPanel outputPanel;
    private JPanel panel;

    public MainFrame(String _username, String _password, String _ip, String _portStr) {
        initializeSSHWindow();
        SSHHandler.handler.setOutputPanel(outputPanel);
        SSHHandler.handler.setCredentials(_username, _password, _ip, _portStr);
    }

    private void initializeSSHWindow() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // when this window is closed, a new ssh connection can be
        // opened through the MainFrame

        setSize(1000, 500);

        inputPanel = new InputPanel();
        outputPanel = new OutputPanel();
        panel = new JPanel(new GridLayout());

        add(panel);
        panel.add(inputPanel);
        panel.add(outputPanel);

        setVisible(true);
    }

    public static int tryParse(String val, int def) {
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return def;
        }
    }
}
