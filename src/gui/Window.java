package gui;

import fr.ynov.pokemon.domain.BattleSystem;
import gui.buttons.Buttons;
import gui.frame.LifeBar;
import gui.panels.BattlePanel;

import javax.swing.JFrame;
import java.awt.BorderLayout;


public class Window extends JFrame {
    private BattlePanel battlePanel;
    private Buttons buttonPanel;
    private LifeBar lifeBar;

    public Window(BattleSystem battleSystem) {

        super("Pokemon Battle");

        this.lifeBar = new LifeBar(battleSystem);
        this.battlePanel = new BattlePanel(battleSystem);
        this.buttonPanel = new Buttons(battleSystem, battlePanel);

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