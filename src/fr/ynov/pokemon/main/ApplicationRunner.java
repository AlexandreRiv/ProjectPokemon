package fr.ynov.pokemon.main;

import gui.Window;
import fr.ynov.pokemon.domain.BattleSystem;

public class ApplicationRunner {
    public static void main(String[] args) {
        BattleSystem battleSystem = new BattleSystem();
        Window window = new Window(battleSystem);
        window.display();
    }
}