package gui.buttons;

import fr.ynov.pokemon.domain.BattleSystem;
import fr.ynov.pokemon.domain.Moves;
import gui.frame.BagFrame;
import gui.panels.BattlePanel;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;


public class Buttons extends JPanel {

    private final BattleSystem battleSystem;
    private final JPanel movesPanel;

    public Buttons(BattleSystem battleSystem , BattlePanel battlePanel) {
        this.battleSystem = battleSystem;
        this.movesPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        movesPanel.setFocusable(true);
        setFocusable(true);

        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    removeMoves(battlePanel);
                }
            }
        };

        movesPanel.addKeyListener(keyListener);
        addKeyListener(keyListener);

        add(movesPanel, BorderLayout.SOUTH);
        mainButton(battlePanel);
    }

    private void mainButton(BattlePanel battlePanel ) {

        JButton buttonAttack = new JButton("Attack");
        JButton buttonBag = new JButton("Bag");
        JButton buttonPokemon = new JButton("Pokemon");
        JButton buttonEscape = new JButton("Escape");

        movesPanel.add(buttonAttack);

        buttonAttack.addActionListener(e -> {showMoves(battlePanel);movesPanel.requestFocusInWindow();});

        movesPanel.add(buttonBag);


        buttonBag.addActionListener(e -> {
             BagFrame bagframe = new BagFrame(battleSystem.getPlayer() ,
                battleSystem.getPokemonFactory().getPlayerPokemon(),
                battleSystem , battlePanel.getLifeBar() , battlePanel);
                bagframe.setVisible(true);
        });

        movesPanel.add(buttonPokemon);

        movesPanel.add(buttonEscape);
        buttonEscape.addActionListener(e -> {System.exit(0);});

    }

    private void showMoves(BattlePanel battlePanel) {

        movesPanel.removeAll();

        List<Moves> moves = battleSystem.getPokemonFactory().getPlayerPokemon().getMoves();
        for (Moves move : moves) {
            JButton button =new JButton(move.getName());
            button.addActionListener(e -> {battlePanel.executeMove(move); removeMoves(battlePanel);});
            movesPanel.add(button);
        }
        movesPanel.requestFocusInWindow();
        movesPanel.revalidate();
        movesPanel.repaint();

    }

    private void removeMoves(BattlePanel battlePanel) {

        movesPanel.removeAll();
        mainButton(battlePanel);
        movesPanel.revalidate();
        movesPanel.repaint();
    }


}
