package no.uib.inf101.sem2.model.fruit;

import java.util.Random;

import no.uib.inf101.sem2.utils.Vector2D;


import java.awt.Dimension;

public class BombOnlyFactory implements FruitFactory {

    // Random object for generating random values
    private Random rand = new Random();

    // Dimensions of the game area
    private Dimension dimensions;

    // Constructor that takes in the game dimensions
    public BombOnlyFactory(Dimension dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Returns the chance of a bomb appearing as a float value.
     * @return the chance of a bomb appearing as a float value.
     */
    @Override
    public float getChanceOfBomb() {
        return 1.0f;
    }

    // Returns true to indicate that the factory always has the next UFO (in this case, always a bomb)
    @Override
    public boolean hasNext() {
        return true;
    }

    /**
     * Returns the next UFO to generate.
     * Position and velocity of the UFO are randomly generated.
     * @return the next UFO to generate.
     */
    @Override
    public Bomb next() {
        
        Vector2D pos; // Position of the bomb
        Vector2D vel; // Velocity of the bomb

        // Randomly determine if the bomb should appear on the left or right side of the screen
        boolean isLeft = rand.nextBoolean();

        // Offsets used to adjust the position of the bomb
        int posDeltaX = 50;
        int posDeltaY = -20;

        // Determine random position for the bomb
        int positionXLeft = -posDeltaX;
        int positionXRight = (int) dimensions.getWidth() - 200 + posDeltaX;
        int positionY = rand.nextInt((int) dimensions.getHeight()) + posDeltaY;

        // Velocity deltas for randomizing the bomb's velocity
        final int velDeltaOrigin = 15;
        final int velDeltaBound = 17;
        final int GRAVITY = 1;

        // Determine random velocity for the bomb
        int velocityXLeft = rand.nextInt(velDeltaOrigin, velDeltaBound);
        int velocityXRight = rand.nextInt(-velDeltaBound, -velDeltaOrigin);
        int velocityY = rand.nextInt(-velDeltaBound,-velDeltaOrigin);

        // Create vectors for the bomb's position, velocity, and acceleration
        Vector2D leftPosition = new Vector2D(positionXLeft, positionY);
        Vector2D rightPosition = new Vector2D(positionXRight,  positionY);
        Vector2D leftVelocity = new Vector2D(velocityXLeft, velocityY);
        Vector2D rightVelocity = new Vector2D(velocityXRight, velocityY);
        Vector2D acc = new Vector2D(0, GRAVITY);

        // Set the bomb's position and velocity based on whether it should appear on the left or right side of the screen
        if(isLeft){
            pos = leftPosition;
            vel = leftVelocity;
        } else {
            pos = rightPosition;
            vel = rightVelocity;
        }

        // Return a new bomb with the determined position, velocity, and acceleration
        return new Bomb(pos, vel, acc, dimensions);
    } 
}
