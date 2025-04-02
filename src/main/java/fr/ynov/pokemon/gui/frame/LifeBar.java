package fr.ynov.pokemon.gui.frame;

import fr.ynov.pokemon.domain.BattleSystem;
import fr.ynov.pokemon.domain.Pokemon;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class LifeBar extends JPanel {

    private final BattleSystem battleSystem;
    private final JProgressBar playerHealthBar;
    private final JProgressBar enemyHealthBar;
    private final JLabel statusLabel;


    /**
     * Constructor for the LifeBar class.
     *
     * @param battleSystem The BattleSystem instance to be used for updating health.
     */
    public LifeBar(BattleSystem battleSystem) {

        this.battleSystem = battleSystem;

        setLayout(new BorderLayout());

        JPanel lifeBarPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Set the size of the life bar panel
        this.playerHealthBar = new JProgressBar(0, battleSystem.getPokemonFactory().getPlayerPokemon().getMaxHp());
        // Set the initial value of the health bar to the player's current HP
        playerHealthBar.setValue(battleSystem.getPokemonFactory().getPlayerPokemon().getHp());
        // Set the string painted to true to show the current HP
        playerHealthBar.setStringPainted(true);

        this.enemyHealthBar = new JProgressBar(0, battleSystem.getPokemonFactory().getOpponentPokemon().getMaxHp());
        enemyHealthBar.setValue(battleSystem.getPokemonFactory().getOpponentPokemon().getHp());
        enemyHealthBar.setStringPainted(true);

        this.statusLabel = new JLabel();
        updateStatusLabel();

        // Add the status label to the life bar panel
        lifeBarPanel.add(new JLabel("Enemy: " + battleSystem.getPokemonFactory().getOpponentPokemon().getName()));
        lifeBarPanel.add(enemyHealthBar);

        lifeBarPanel.add(new JLabel("Player: " + battleSystem.getPokemonFactory().getPlayerPokemon().getName()));
        lifeBarPanel.add(playerHealthBar);

        add(lifeBarPanel, BorderLayout.NORTH);

       battleSystem.setLifeBar(this);

    }

    /**
     * Updates the health bars for both the player and the enemy.
     * This method retrieves the current HP of both Pokemon and updates the progress bars accordingly.
     */
    public void updateHealth() {

        playerHealthBar.setValue(battleSystem.getPokemonFactory().getPlayerPokemon().getHp());
        enemyHealthBar.setValue(battleSystem.getPokemonFactory().getOpponentPokemon().getHp());

        updateStatusLabel();
        revalidate();
        repaint();
    }

    /**
     * Updates the status label to display the current HP of both the player and the enemy Pokemon.
     * This method retrieves the current HP and maximum HP of both Pokemon and updates the label accordingly.
     */
    private void updateStatusLabel() {

        Pokemon pokemonPlayer = battleSystem.getPokemonFactory().getPlayerPokemon();
        Pokemon pokemonEnemy = battleSystem.getPokemonFactory().getOpponentPokemon();

        // Update the status label with the current HP of both Pokemon
        statusLabel.setText(String.format("%s HP: %d/%d | %s HP: %d/%d",
                pokemonPlayer.getName(), pokemonPlayer.getHp(), pokemonPlayer.getMaxHp(),
                pokemonEnemy.getName(), pokemonEnemy.getHp(), pokemonEnemy.getMaxHp()));
    }
}
