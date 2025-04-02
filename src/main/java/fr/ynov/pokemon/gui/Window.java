package fr.ynov.pokemon.gui;

import fr.ynov.pokemon.domain.BattleSystem;
import fr.ynov.pokemon.gui.buttons.Buttons;
import fr.ynov.pokemon.gui.frame.LifeBar;
import fr.ynov.pokemon.gui.panels.BattlePanel;

import javax.swing.JFrame;
import java.awt.BorderLayout;


public class Window extends JFrame {

    /**
     * Constructor for the Window class.
     *
     * @param battleSystem The BattleSystem instance to be used for the battle.
     */
    public Window(BattleSystem battleSystem) {

        super("Pokemon Battle");

        LifeBar lifeBar = new LifeBar(battleSystem);
        BattlePanel battlePanel = new BattlePanel(battleSystem , lifeBar);
        Buttons buttonPanel = new Buttons(battleSystem, battlePanel);

        // add all Class created to the Window
        add(lifeBar, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(battlePanel);

        setSize(500, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setLocationRelativeTo(null);

    }
    // Set the window to be visible
    public void display() {
        setVisible(true);
    }
}