package fr.ynov.pokemon.gui.frame;

import fr.ynov.pokemon.domain.Items;
import fr.ynov.pokemon.domain.Player;
import fr.ynov.pokemon.domain.Pokemon;
import fr.ynov.pokemon.domain.Heal;
import fr.ynov.pokemon.domain.Moves;
import fr.ynov.pokemon.domain.BattleSystem;

import fr.ynov.pokemon.gui.panels.BattlePanel;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Dimension;



public class BagFrame extends JPanel {

    private  final JList<String> itemsList;
    private final Player player;
    private final Pokemon pokemon;
    private final LifeBar lifeBar;

    public BagFrame(Player player, Pokemon pokemon, BattleSystem battleSystem , LifeBar lifeBar ,BattlePanel battlePanel ) {

        this.player = player;
        this.pokemon = pokemon;
        this.lifeBar = lifeBar;


        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Bag"));
        setPreferredSize(new Dimension(200 , 400));

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {setVisible(false);});
        add(closeButton, BorderLayout.NORTH);

        DefaultListModel<String> items = new DefaultListModel<>();
        for (Items item : player.getInventory().getInventory()) {
            items.addElement(item.getName());
        }

        itemsList = new JList<>(items);
        itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemsList.addListSelectionListener(e -> {handleItemSelection(battlePanel);});

        JScrollPane scrollPane = new JScrollPane(itemsList);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(false);

    }

    private void handleItemSelection(BattlePanel battlePanel) {
        String selectedItem = itemsList.getSelectedValue();
        if (selectedItem != null && selectedItem.equals("Heal")) {
            Heal heal = (Heal) player.getInventory().getInventory().stream()
                    .filter(item -> item instanceof Heal)
                    .findFirst()
                    .orElse(null);

            if (heal != null) {
                heal.use(pokemon, player);
                lifeBar.updateHealth();
                Moves move = new Moves("Heal", 0, 0, 0 , 0 , null , null);
                battlePanel.executeMove(move);

                DefaultListModel<String> model = (DefaultListModel<String>) itemsList.getModel();
                model.clear();
                for (Items item : player.getInventory().getInventory()) {
                    model.addElement(item.getName());
                }
            }
        }

    }
}
