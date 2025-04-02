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


    /**
     * Initializes the bag panel that displays the player's inventory.
     * This constructor sets up the graphical interface of the bag with the following elements:
     *
     * - Stores references to the player, pokemon and life bar
     * - Configures the layout using BorderLayout
     * - Sets a titled border "Bag" and a preferred size of 200x400
     * - Adds a "Close" button at the top to close the panel
     * - Creates a scrollable list of the player's inventory items
     * - Configures single item selection with an event listener
     * - Places the list in a JScrollPane in the center
     * - Initially hides the panel
     *
     * @param player The player whose inventory will be displayed
     * @param pokemon The player's current pokemon
     * @param lifeBar The life bar to update when using items
     * @param battleSystem The current battle system
     * @param battlePanel The main battle panel
     */
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

        // Create a list model to hold the items
        DefaultListModel<String> items = new DefaultListModel<>();
        // Populate the list model with the items from the player's inventory
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
        // Check if the selected item is "Heal"
        if (selectedItem != null && selectedItem.equals("Heal")) {
            // Use the Heal item on the inventory of the player
            Heal heal = (Heal) player.getInventory().getInventory().stream()
                    .filter(item -> item instanceof Heal)
                    .findFirst()
                    .orElse(null);


            if (heal != null) {

                heal.use(pokemon, player);
                lifeBar.updateHealth();
                // Execute a Heal move to update the battle panel (Moves heal as no power just skip the turn and heal the pokemon)
                Moves move = new Moves("Heal", 0, 0, 0 , 0 , null , null);
                battlePanel.executeMove(move);
                // Remove the used item from the inventory
                DefaultListModel<String> model = (DefaultListModel<String>) itemsList.getModel();
                model.clear();
                // Update the list with the remaining items
                for (Items item : player.getInventory().getInventory()) {
                    model.addElement(item.getName());
                }
            }
        }

    }
}
