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

    public LifeBar(BattleSystem battleSystem) {

        this.battleSystem = battleSystem;

        setLayout(new BorderLayout());

        JPanel lifeBarPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        this.playerHealthBar = new JProgressBar(0, battleSystem.getPokemonFactory().getPlayerPokemon().getMaxHp());
        playerHealthBar.setValue(battleSystem.getPokemonFactory().getPlayerPokemon().getHp());
        playerHealthBar.setStringPainted(true);

        this.enemyHealthBar = new JProgressBar(0, battleSystem.getPokemonFactory().getOpponentPokemon().getMaxHp());
        enemyHealthBar.setValue(battleSystem.getPokemonFactory().getOpponentPokemon().getHp());
        enemyHealthBar.setStringPainted(true);

        this.statusLabel = new JLabel();
        updateStatusLabel();

        lifeBarPanel.add(new JLabel("Enemy: " + battleSystem.getPokemonFactory().getOpponentPokemon().getName()));
        lifeBarPanel.add(enemyHealthBar);

        lifeBarPanel.add(new JLabel("Player: " + battleSystem.getPokemonFactory().getPlayerPokemon().getName()));
        lifeBarPanel.add(playerHealthBar);

        add(lifeBarPanel, BorderLayout.NORTH);

       battleSystem.setLifeBar(this);

    }

    public void updateHealth() {
        Pokemon pokemonPlayer = battleSystem.getPokemonFactory().getPlayerPokemon();
        Pokemon pokemonEnemy = battleSystem.getPokemonFactory().getOpponentPokemon();

        playerHealthBar.setValue(battleSystem.getPokemonFactory().getPlayerPokemon().getHp());
        enemyHealthBar.setValue(battleSystem.getPokemonFactory().getOpponentPokemon().getHp());

        updateStatusLabel();
        revalidate();
        repaint();
    }

    private void updateStatusLabel() {
        Pokemon pokemonPlayer = battleSystem.getPokemonFactory().getPlayerPokemon();
        Pokemon pokemonEnemy = battleSystem.getPokemonFactory().getOpponentPokemon();
        statusLabel.setText(String.format("%s HP: %d/%d | %s HP: %d/%d",
                pokemonPlayer.getName(), pokemonPlayer.getHp(), pokemonPlayer.getMaxHp(),
                pokemonEnemy.getName(), pokemonEnemy.getHp(), pokemonEnemy.getMaxHp()));
    }
}
