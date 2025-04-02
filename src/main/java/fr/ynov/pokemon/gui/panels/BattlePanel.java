package fr.ynov.pokemon.gui.panels;

import fr.ynov.pokemon.domain.BattleSystem;
import fr.ynov.pokemon.domain.Moves;
import fr.ynov.pokemon.gui.frame.LifeBar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;



public class BattlePanel extends JPanel {

    private final BattleSystem battleSystem;
    private final LifeBar lifeBar;
    private final JLabel statusLabel;

    /**
     * Initializes the battle panel that displays the battle status.
     * This constructor sets up the graphical interface of the battle with the following elements:
     *
     * - Stores references to the battle system and life bar
     * - Configures the layout using BorderLayout
     * - Sets a JLabel to display the battle status
     * - Sets the life bar for the battle system
     *
     * @param battleSystem The current battle system
     * @param lifeBar The life bar to be used in the battle
     */
    public BattlePanel(BattleSystem battleSystem , LifeBar lifeBar) {

        this.battleSystem = battleSystem;
        this.lifeBar = lifeBar;

        setLayout(new BorderLayout());

        statusLabel = new JLabel("Battle Started!");
        add(statusLabel, BorderLayout.CENTER);

        battleSystem.setLifeBar(lifeBar);
    }


    /**
     * Executes the player's move in the battle.
     * This method takes the player's selected move and executes it against the opponent's Pokemon.
     * It also checks if the opponent's Pokemon has fainted and executes the enemy's move if not.
     * Finally, it updates the health bars and checks for the end of the game.
     */
    public void executeMove(Moves playerMoves) {

        battleSystem.executeMoveGUI(battleSystem.getPokemonFactory().getPlayerPokemon() , battleSystem.getPokemonFactory().getOpponentPokemon(), playerMoves);
        if (!battleSystem.getPokemonFactory().getOpponentPokemon().isFainted()) {
            Moves enemyMoves = battleSystem.selectEnemyMove();
            battleSystem.executeMoveGUI(battleSystem.getPokemonFactory().getOpponentPokemon(), battleSystem.getPokemonFactory().getPlayerPokemon(), enemyMoves);
        }


        lifeBar.updateHealth();
        checkEndGame();
    }


    /**
     * check if the game has ended.
     * This method checks if either the player's or the opponent's Pokemon has fainted.
     */
    private void checkEndGame() {

        if (battleSystem.getPokemonFactory().getOpponentPokemon().isFainted()) {

            statusLabel.setText(battleSystem.getPokemonFactory().getPlayerPokemon().getName() + " has won the battle!");
            statusLabel.setText("You have won the battle!");

        } else if (battleSystem.getPokemonFactory().getPlayerPokemon().isFainted()) {

              statusLabel.setText(battleSystem.getPokemonFactory().getOpponentPokemon().getName() + " has won the battle!");
              statusLabel.setText("You have lost the battle!");

        }
    }


    public LifeBar getLifeBar() {
        return lifeBar;
    }


}
