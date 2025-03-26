package gui.frame;

import fr.ynov.pokemon.domain.*;
import gui.panels.BattlePanel;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;


public class BagFrame extends JDialog {
    public BagFrame(Player player, Pokemon pokemon, BattleSystem battleSystem , LifeBar lifeBar , BattlePanel battlePanel) {
        setTitle("Bag");
        setSize(200, 100);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));

        for (Items items : player.getInventory().getInventory()) {
            JButton itemButton = new JButton(items.getName());
            itemButton.addActionListener(e -> {
                items.use(pokemon , battleSystem.getPlayer());
                Moves move = new Moves(items.getName(), 0, 0 , 0 , 0 ,null , "");
                battlePanel.executeMove(move);
                battleSystem.setLifeBar(lifeBar);
                dispose();
            });
            panel.add(itemButton);
        }


        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());

        panel.add(backButton);

        add(panel);
    }
}
