package gui.panels;

import fr.ynov.pokemon.domain.BattleSystem;
import fr.ynov.pokemon.domain.Moves;
import gui.frame.LifeBar;

import javax.swing.JLabel;
import javax.swing.JPanel;
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

        battleSystem.setLifeBar(lifeBar);
    }



    public void executeMove(Moves playerMoves) {

        battleSystem.executeMoveGUI(battleSystem.getPokemonFactory().getPlayerPokemon() , battleSystem.getPokemonFactory().getOpponentPokemon(), playerMoves);
        if (!battleSystem.getPokemonFactory().getOpponentPokemon().isFainted()) {
            Moves enemyMoves = battleSystem.selectEnemyMove();
            battleSystem.executeMoveGUI(battleSystem.getPokemonFactory().getOpponentPokemon(), battleSystem.getPokemonFactory().getPlayerPokemon(), enemyMoves);
        }


        lifeBar.updateHealth();
        checkEndGame();
    }



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
