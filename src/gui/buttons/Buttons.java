package gui.buttons;

import fr.ynov.pokemon.domain.BattleSystem;
import fr.ynov.pokemon.domain.Moves;
import gui.panels.BattlePanel;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.List;


public class Buttons extends JPanel {

    private BattleSystem battleSystem;
    private JPanel movesPanel;

    public Buttons(BattleSystem battleSystem , BattlePanel battlePanel) {
        this.battleSystem = battleSystem;
        this.movesPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        add(movesPanel, BorderLayout.SOUTH);

        mainBoutton(battlePanel);
    }

    private void mainBoutton(BattlePanel battlePanel ) {

        JButton button = new JButton("Attack");
        JButton button2 = new JButton("Bag");
        JButton button3 = new JButton("Pokemon");
        JButton button4 = new JButton("Escape");

        movesPanel.add(button);

        button.addActionListener(e -> {showMoves(battlePanel);});

        movesPanel.add(button2);
        movesPanel.add(button3);
        movesPanel.add(button4);

    }

    private void showMoves(BattlePanel battlePanel) {

        movesPanel.removeAll();

        List<Moves> moves = battleSystem.getPlayerPokemon().getMoves();
        for (Moves move : moves) {
            JButton button =new JButton(move.getName());
            button.addActionListener(e -> {battlePanel.executeMove(move);});
            movesPanel.add(button);
        }

        movesPanel.revalidate();
        movesPanel.repaint();

    }
}
