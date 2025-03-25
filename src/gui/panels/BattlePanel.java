package gui.panels;

import fr.ynov.pokemon.domain.BattleSystem;
import fr.ynov.pokemon.domain.Moves;
import gui.frame.LifeBar;

import javax.swing.*;
import java.awt.BorderLayout;



public class BattlePanel extends JPanel {

    private final BattleSystem battleSystem;
    private final LifeBar lifeBar;
    private final JLabel statusLabel;

    public BattlePanel(BattleSystem battleSystem , LifeBar lifeBar) {

        this.battleSystem = battleSystem;
        this.lifeBar = lifeBar;
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Battle Started!");
        add(statusLabel, BorderLayout.CENTER);

        battleSystem.setUIComponents(lifeBar);
    }



    public void executeMove(Moves playerMoves) {

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
