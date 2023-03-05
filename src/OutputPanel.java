import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class OutputPanel extends JScrollPane {
    private JLabel outputFeed;
    private LinkedList<String> output;

    public OutputPanel() {
        output = new LinkedList<>();
        outputFeed = new JLabel("");
        outputFeed.setVerticalAlignment(JLabel.TOP);

        setViewportView(outputFeed);
        getVerticalScrollBar().setUnitIncrement(8);
    }

    public void updateFeed(String append) {
        splitOutputString(append);

        String display = "<html>";

        for (Iterator i = output.iterator(); i.hasNext();) {
            String curr = i.next().toString();
            if (curr.equals("-------Finished-------")) {
                display += curr;
            } else {
                display += "~~~>    " + curr;
            }
            display += "<br>";
        }

        display += "</html>";
        outputFeed.setText(display);

//        getVerticalScrollBar().setValue(getVerticalScrollBar().getMaximum());
    }

    private void splitOutputString(String append) {
        while (append.contains("\n")) {
            int i = append.indexOf("\n");
            String temp = append.substring(0, i + 1);
            append = append.substring(i + 1);
            output.add(temp);
        }
        if (append != "") output.add(append);
    }
}
