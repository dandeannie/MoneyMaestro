package mm;

import javax.swing.*;
import java.awt.*;

public class AboutUs extends JFrame {
    public AboutUs() {
        super("About MoneyMaestro");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setText("MoneyMaestro\n\nA simple expense tracker demo.\n\nThis placeholder screen was added to allow the project to compile. You can customize it later.");
        add(new JScrollPane(ta), BorderLayout.CENTER);
    }
}
