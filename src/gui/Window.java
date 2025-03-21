package gui;

import gui.buttons.TestButtons;
import javax.swing.JFrame;


public class Window extends JFrame {
    private TestButtons buttonPanel;

    public Window() {
        super("My Window");
        buttonPanel = new TestButtons();
        add(buttonPanel);
    }

    public void display() {
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}