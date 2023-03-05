import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MainFrame extends JFrame {
    private InputPanel inputPanel;
    private OutputPanel outputPanel;
    private JPanel panel;
    public Process powershell;

    public MainFrame(String _username, String _password, String _ip, String _portStr) {
        initializeSSHWindow();
        SSHHandler.handler.setOutputPanel(outputPanel);
        SSHHandler.handler.setCredentials(_username, _password, _ip, _portStr);
    }

    private void initializeSSHWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); // when this window is closed, the process will end

        setSize(1000, 500);

        inputPanel = new InputPanel();
        outputPanel = new OutputPanel();
        panel = new JPanel(new GridLayout());

        add(panel);
        panel.add(inputPanel);
        panel.add(outputPanel);

        setVisible(true);
    }

    public void execute(String command) {
        try {
            powershell = Runtime.getRuntime().exec("powershell.exe -c " + command);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    public static int tryParse(String val, int def) {
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return def;
        }
    }
}