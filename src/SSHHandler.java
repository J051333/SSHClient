import com.jcraft.jsch.*;

import javax.swing.*;
import java.io.ByteArrayOutputStream;

public class SSHHandler {
    public static final SSHHandler handler = new SSHHandler();

    private OutputPanel outputPanel;

    private String username = "";
    private String password = "";
    private String ip = "";
    private int port = -1;

    public void setCredentials(String _username, String _password, String _ip, String _portStr) {
        username = _username;
        password = _password;
        ip = _ip;
        port = MainFrame.tryParse(_portStr, 22);
    }

    public void runSSH(String command) {
        // We want a new thread so that it doesn't clog main
        if (username.equals("") || password.equals("") || ip.equals("") || port == -1) {
            JOptionPane.showMessageDialog(null, "It seems your credentials may be off. Try again.");
        }
        System.out.println(ip);
        new Thread(() -> establishConnection(command)).start();
    }

    public void establishConnection(String command) {
        Session session = null;
        ChannelExec channel = null;

        if (outputPanel == null) {
            System.err.println("Uhm.. no?");
        }

        try {
            session = new JSch().getSession(username, ip, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");

            channel.setCommand(command);

            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);

                outputPanel.updateFeed(responseStream.toString());
//                System.out.println(responseStream);
            }

            outputPanel.updateFeed("-------Finished-------");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "!", "Uh owh, ewwow! ÒwÓ", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    public void setOutputPanel(OutputPanel op) {
        outputPanel = op;
    }
}
