package gui;

import fr.ynov.pokemon.domain.BattleSystem;
import gui.buttons.Buttons;
import gui.frame.LifeBar;
import gui.panels.BattlePanel;

import javax.swing.JFrame;
import java.awt.BorderLayout;


public class Window extends JFrame {

    public Window(BattleSystem battleSystem) {

        super("Pokemon Battle");

        LifeBar lifeBar = new LifeBar(battleSystem);
        BattlePanel battlePanel = new BattlePanel(battleSystem , lifeBar);
        Buttons buttonPanel = new Buttons(battleSystem, battlePanel);

        add(lifeBar, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(battlePanel);

        setSize(500, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setLocationRelativeTo(null);

    }

    public void display() {
        setVisible(true);
    }
}