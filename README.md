# Pokemon Project - Combat Simulator

## Description
Pokemon battle simulator developed in Java with Swing graphical interface. The game reproduces the basic mechanics of a Pokemon battle.

### Prérequis
- Java JDK 17 ou supérieur
- Gradle 7.x ou supérieur

## Installation
1. Clone repository
```bash
  git clone https://github.com/AlexandreRiv/ProjectPokemon.git
```    

2. Open project 
 ```bash
  cd ProjectPokemon
  ./gradlew run
 ```
Or run ApplicationRunner class in the main directory


## Features
- 2 Pokemon (Player and Opponent) 
- Pokemon are created with a Json in random order
- Type system (Fire, Water, Flight, etc.)
- Type advantages and disadvantages
- moves with different types and damages
- A Pokemon can have 4 moves of his type or Normal type 
- Full graphical interface with :
    - Action buttons (Attack, Bag, Pokemon, Escape)
    - Life bars
    - Item inventory
    - Keyboard navigation

## Architecture
- Pattern Factory for Pokemon creation
- Enumeration system for types
- Graphical interface with Swing
- MVC architecture


## Controls
- Left click: Select action and moves
- - Mouse navigation:
  - Click on moves to attack
  - Click on bag to access items
  - Click on items to use them
- Back key: Return to main menu
- Escape key: Exit game

## Contributors
- Alexandre Riviere
