package no.uib.inf101.sem2.model.fruit;

import java.util.Iterator;

/**
 * FruitFactory is an interface that extends the Iterator interface for generating UFOs in a Fruit Ninja game.
 */
public interface FruitFactory extends Iterator<UFO> {

    /**
     * Returns the chance of a bomb appearing as a float value.
     */
    float getChanceOfBomb();

    /**
     * Returns true if there are more UFOs to generate, false otherwise.
     */
    @Override
    boolean hasNext();

    /**
     * Returns the next UFO to generate.
     */
    @Override
    UFO next();
    
}
