package no.uib.inf101.sem2.model.fruit.controller;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.utils.Vector2D;

public interface ControllableNinjaModel {
    

    boolean pauseGame();
    
    GameState getGameState();

    int getSpawnTimerDelay();

    void clockTick();

    void frameUpdate();

    boolean pokePosition(Vector2D posHit);

    int getFrameRate();


}
