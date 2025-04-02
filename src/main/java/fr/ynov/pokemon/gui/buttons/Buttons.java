package fr.ynov.pokemon.gui.buttons;

import fr.ynov.pokemon.domain.BattleSystem;
import fr.ynov.pokemon.domain.Moves;
import fr.ynov.pokemon.gui.frame.BagFrame;
import fr.ynov.pokemon.gui.panels.BattlePanel;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
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
        // Grid 2x2 for the main button
        this.movesPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        //
        movesPanel.setFocusable(true);

        /**
         * Creates a KeyListener that detects when the backspace key is pressed.
         * When the backspace key is pressed, it removes the move buttons and returns to the main menu.
         *
         * @param battlePanel The panel containing the battle interface elements
         */
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

    /**
     * Creates and initializes the main battle menu buttons.
     * This method sets up a 2x2 grid of buttons for the main battle actions:
     * - Attack: Shows the Pokemon's moves when clicked
     * - Bag: Toggles the visibility of the item bag frame
     * - Pokemon: (Currently not implemented)
     * - Escape: Exits the application
     *
     * The method also initializes a BagFrame that appears on the east side of the battle panel
     * when the Bag button is clicked.
     *
     * @param battlePanel The panel containing the battle interface where the buttons will be added
     */
    private void mainButton(BattlePanel battlePanel ) {

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton buttonAttack = new JButton("Attack");
        JButton buttonBag = new JButton("Bag");
        JButton buttonPokemon = new JButton("Pokemon");
        JButton buttonEscape = new JButton("Escape");

        BagFrame bagFrame = new BagFrame(battleSystem.getPlayer() ,
                battleSystem.getPokemonFactory().getPlayerPokemon(),
                battleSystem , battlePanel.getLifeBar() , battlePanel );

        battlePanel.add(bagFrame , BorderLayout.EAST);


        buttonAttack.addActionListener(e -> {bagFrame.setVisible(false);showMoves(battlePanel);movesPanel.requestFocusInWindow();});

        // Set the bagFrame to visible when buttonBag is pressed
        buttonBag.addActionListener(e ->{
            bagFrame.setVisible(!bagFrame.isVisible());
            battlePanel.revalidate();
            battlePanel.repaint();
        });


        buttonEscape.addActionListener(e -> {System.exit(0);});

        movesPanel.add(buttonAttack);
        movesPanel.add(buttonBag);
        movesPanel.add(buttonPokemon);
        movesPanel.add(buttonEscape);
    }


    /**
     * Displays the moves of the player's Pokemon in the battle panel.
     * This method removes all existing buttons from the movesPanel and adds buttons for each move
     * of the player's Pokemon. When a move button is clicked, it executes the corresponding move
     * and removes the move buttons, returning to the main menu.
     *
     * @param battlePanel The panel containing the battle interface where the moves will be displayed
     */
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

    /**
     * Removes the move buttons from the movesPanel and returns to the main menu.
     * This method calls the mainButton method to reinitialize the main menu buttons.
     *
     * @param battlePanel The panel containing the battle interface where the moves will be removed
     */
    private void removeMoves(BattlePanel battlePanel) {

        movesPanel.removeAll();
        mainButton(battlePanel);
        movesPanel.revalidate();
        movesPanel.repaint();

    }


}
