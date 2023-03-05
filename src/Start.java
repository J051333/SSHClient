import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame {
    JPanel p;
    JTextField username;
    JPasswordField password;
    JTextField ip;
    JTextField port;
    JButton openSession;
    public static void main(String[] args) {
        Start start = new Start();
        start.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel p = new JPanel(new GraphPaperLayout(new Dimension(4, 5)));

        start.openSession = new JButton("START");
        start.openSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame(start.username.getText(), String.valueOf(start.password.getPassword()), start.ip.getText(), start.port.getText());
            }
        });

        start.username = new JTextField();
        start.password = new JPasswordField();
        start.ip = new JTextField();
        start.port = new JTextField();

        p.add(new JLabel("Username: "), new Rectangle(0, 0, 1, 1));
        p.add(start.username, new Rectangle(1, 0, 3, 1));
        p.add(new JLabel("Password: "), new Rectangle(0, 1, 1, 1));
        p.add(start.password, new Rectangle(1, 1, 3, 1));
        p.add(new JLabel("IP: "), new Rectangle(0, 2, 1, 1));
        p.add(start.ip, new Rectangle(1, 2, 3, 1));
        p.add(new JLabel("Port: "), new Rectangle(0, 3, 1, 1));
        p.add(start.port, new Rectangle(1, 3, 3, 1));
        p.add(start.openSession, new Rectangle(0, 4, 4, 1));


        start.add(p);
        start.setSize(500, 500);
        start.setVisible(true);
    }
}