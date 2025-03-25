package gui.panels;

import fr.ynov.pokemon.domain.BattleSystem;
import fr.ynov.pokemon.domain.Moves;
import gui.frame.LifeBar;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;



public class BattlePanel extends JPanel {

    private BattleSystem battleSystem;
    private LifeBar lifeBar;
    private JLabel statusLabel;

    public BattlePanel(BattleSystem battleSystem) {

        this.battleSystem = battleSystem;
        this.lifeBar = new LifeBar(battleSystem);

        setLayout(new BorderLayout());

        statusLabel = new JLabel("Battle Started!");

        add(statusLabel, BorderLayout.CENTER);

        lifeBar.updateHealth(statusLabel);
        checkEndGame();
    }

    public void executeMove(Moves playerMoves) {
        checkEndGame();
        battleSystem.executeMoveGUI(battleSystem.getPlayerPokemon() , battleSystem.getOpponentPokemon(), playerMoves);

        if (!battleSystem.getOpponentPokemon().isFainted()) {
            Moves enemyMoves = battleSystem.selectEnemyMove();
            battleSystem.executeMoveGUI(battleSystem.getOpponentPokemon(), battleSystem.getPlayerPokemon(), enemyMoves);
        }

        lifeBar.updateHealth(statusLabel);
        checkEndGame();
    }

    private void checkEndGame() {
        if (battleSystem.getOpponentPokemon().isFainted()) {
            statusLabel.setText(battleSystem.getPlayerPokemon().getName() + " has won the battle!");
            statusLabel.setText("You have won the battle!");
        } else if (battleSystem.getPlayerPokemon().isFainted()) {
              statusLabel.setText(battleSystem.getOpponentPokemon().getName() + " has won the battle!");
              statusLabel.setText("You have lost the battle!");
        }
    }


}
