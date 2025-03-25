package gui.frame;

import fr.ynov.pokemon.domain.BattleSystem;
import fr.ynov.pokemon.domain.Pokemon;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class LifeBar extends JPanel {

    private BattleSystem battleSystem;
    private JProgressBar playerHealthBar;
    private JProgressBar enemyHealthBar;
    private JPanel lifeBarPanel;

    public LifeBar(BattleSystem battleSystem) {

        setLayout(new BorderLayout());

        this.battleSystem = battleSystem;
        this.lifeBarPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        this.playerHealthBar = new JProgressBar(0, battleSystem.getPlayerPokemon().getMaxHp());
        this.enemyHealthBar = new JProgressBar(0, battleSystem.getOpponentPokemon().getMaxHp());

        add(lifeBarPanel, BorderLayout.NORTH);

        lifeBarPanel.add(new JLabel("Enemy: " + battleSystem.getOpponentPokemon().getName()));
        lifeBarPanel.add(enemyHealthBar);

        lifeBarPanel.add(new JLabel("Player: " + battleSystem.getPlayerPokemon().getName()));
        lifeBarPanel.add(playerHealthBar);

    }

    public void updateHealth(JLabel statusLabel) {
        Pokemon player = battleSystem.getPlayerPokemon();
        Pokemon enemy = battleSystem.getOpponentPokemon();

        playerHealthBar.setValue(player.getHp());
        enemyHealthBar.setValue(enemy.getHp());

        statusLabel.setText(String.format("%s HP: %d/%d | %s HP: %d/%d",
                player.getName(), player.getHp(), player.getMaxHp(),
                enemy.getName(), enemy.getHp(), enemy.getMaxHp()));
    }
}
