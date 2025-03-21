package gui.buttons;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;


public class TestButtons extends JPanel {
    public TestButtons() {
        setLayout(new BorderLayout());
        JButton button = new JButton("Test");
        button.setPreferredSize(new Dimension(150, 50));

        addButtons(button , BorderLayout.CENTER);
    }

    private void addButtons(JButton button , String position) {
        add(button , position);
    }


}
