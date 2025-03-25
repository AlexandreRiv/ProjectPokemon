package fr.ynov.pokemon.main;

import fr.ynov.pokemon.domain.Pokemon;
import fr.ynov.pokemon.domain.PokemonLoader;
import gui.Window;
import fr.ynov.pokemon.domain.BattleSystem;

import javax.swing.*;

public class ApplicationRunner {
    public static void main(String[] args) {
        BattleSystem battleSystem = new BattleSystem();
        Window window = new Window(battleSystem);
        window.display();
    }
}