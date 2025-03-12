package no.uib.inf101.sem2.view;

import java.util.List;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.fruit.UFO;
import no.uib.inf101.sem2.utils.Vector2D;

public interface ViewableNinjaModel {

    /**
     * Get fruits and bombs as list
     * @return List of UFOs
     */
    List<UFO> getUFOs();


    GameState getGameState();


    Vector2D getHitPosition();


    boolean getDebugStatus();


    int getScore();
    

}
