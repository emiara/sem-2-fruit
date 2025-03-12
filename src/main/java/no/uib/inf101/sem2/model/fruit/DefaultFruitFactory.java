package no.uib.inf101.sem2.model.fruit;

import java.util.Random;
import no.uib.inf101.sem2.utils.Vector2D;

import java.awt.Dimension;


public class DefaultFruitFactory implements FruitFactory {
    private static final Random rand = new Random();
    private Dimension dimensions;

    public DefaultFruitFactory(Dimension dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Returns the chance of a bomb appearing as a float value.
     * @return The chance of a bomb appearing as a float value.
     */
    @Override
    public float getChanceOfBomb() {
        return 0.1f;
    }

    // Always returns true because the factory can always create a new fruit
    @Override
    public boolean hasNext() {
        return true;
    }

    /**
     * Returns the next UFO to generate.
     * @return The next UFO to generate.
     */
    @Override
    public UFO next() {
        Vector2D pos;
        Vector2D vel;
        boolean isLeft = rand.nextBoolean();
        int posDeltaX = 50;
        int posDeltaY = -20;

        // Random Position
        int positionXLeft = -posDeltaX;
        int positionXRight = (int) dimensions.getWidth() - 200 + posDeltaX;
        int positionY = rand.nextInt((int) dimensions.getHeight()) + posDeltaY;

        final int velDeltaOrigin = 15;
        final int velDeltaBound = 17;
        final int GRAVITY = 1;

        // Random velocity
        int velocityXLeft = rand.nextInt(velDeltaOrigin, velDeltaBound);
        int velocityXRight = rand.nextInt(-velDeltaBound, -velDeltaOrigin);
        int velocityY = rand.nextInt(-velDeltaBound,-velDeltaOrigin);

        // Random position
        Vector2D leftPosition = new Vector2D(positionXLeft, positionY);
        Vector2D rightPosition = new Vector2D(positionXRight, positionY);

        // Non-Random velocity
        Vector2D leftVelocity = new Vector2D(velocityXLeft, velocityY);
        Vector2D rightVelocity = new Vector2D(velocityXRight, velocityY);

        Vector2D acc = new Vector2D(0, GRAVITY);

        // Determine whether to create a bomb or a fruit
        if (rand.nextFloat() < getChanceOfBomb()) { // Create a bomb
            System.out.println("BOMB!");
            pos = isLeft ? leftPosition : rightPosition; // Use the appropriate position based on direction
            vel = isLeft ? leftVelocity : rightVelocity; // Use the appropriate velocity based on direction

            return new Bomb(pos, vel, acc, dimensions);

        } else { // Create a fruit
            pos = isLeft ? leftPosition : rightPosition; // Use the appropriate position based on direction
            vel = isLeft ? leftVelocity : rightVelocity; // Use the appropriate velocity based on direction

            // Create a random type of fruit
            FruitType type = FruitType.values()[rand.nextInt(FruitType.values().length)];

            return new Fruit(type, pos, vel, acc);
        }
    }
}
