package no.uib.inf101.sem2.model.fruit;

import no.uib.inf101.sem2.utils.Vector2D;
import no.uib.inf101.sem2.view.Inf101Graphics;

import java.awt.image.BufferedImage;

public class Fruit implements UFO {

    // The type of fruit (e.g. APPLE, BANANA, ORANGE)
    private final FruitType type;
    
    // The position of the fruit
    private Vector2D position;
    
    // The velocity of the fruit
    private Vector2D velocity;
    
    // The acceleration of the fruit
    private Vector2D acceleration;
    
    // The image of the fruit
    private BufferedImage img;
    
    // The status of the fruit (e.g. NORMAL, HIT, EXPLODED)
    private UFOStatus status = UFOStatus.NORMAL;

    /**
     * Constructor for the Fruit class
     * @param type The type of fruit (e.g. APPLE, BANANA, ORANGE)
     * @param position The position of the fruit
     * @param velocity The velocity of the fruit
     * @param acceleration The acceleration of the fruit
     */
    public Fruit(FruitType type, Vector2D position, Vector2D velocity, Vector2D acceleration) {
        this.type = type;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        
        // Load the image for the fruit from the resources folder
        this.img = Inf101Graphics.loadImageFromResources("images/ufos/fruit/" + type.toString().toLowerCase() + ".png");
    }

    /**
     * Getter method for the status of the fruit
     * @return The status of the fruit
     */
    public UFOStatus getStatus() {
        return status;
    }

    /**
     * Setter method for the status of the fruit
     * @param status The new status of the fruit
     */
    public void setStatus(UFOStatus status) {
        this.status = status;
    }

    /**
     * Getter method for the position of the fruit
     * @return The position of the fruit
     */
    @Override
    public Vector2D getPosition() {
        return position;
    }

    // Setter method for the position of the fruit
    /**
     * Setter method for the position of the fruit
     * @param position The new position of the fruit
     */
    @Override
    public void setPosition(Vector2D position) {
        this.position = position;
    }

    /**
     * Getter method for the velocity of the fruit
     * @return The velocity of the fruit
     */
    @Override
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Setter method for the velocity of the fruit
     * @param velocity The new velocity of the fruit
     */
    @Override
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    /**
     * Getter method for the acceleration of the fruit
     * @return The acceleration of the fruit
     */
    @Override
    public Vector2D getAcceleration() {
        return acceleration;
    }

    /**
     * Setter method for the acceleration of the fruit
     * @param acceleration The new acceleration of the fruit
     */
    @Override
    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * Getter method for the type of the fruit
     * @return The type of the fruit
     */
    public FruitType getType() {
        return type;
    }


    /**
     * Returns a string representation of the fruit object
     * @return A string representation of the fruit object
     */
    @Override
    public String toString() {
        String positionString = position.toString();
        String velocityString = velocity.toString();
        String accelerationString = acceleration.toString();
        return "Fruit [type=" + type + ", position=" + positionString + ", velocity=" + velocityString + ", acceleration="
                + accelerationString + "]";
    }


    /**
     * Returns the image of the fruit
     * @return The image of the fruit
     */
    @Override
    public BufferedImage getImage() {
        return this.img;
    }

    /**
     * Updates the position and velocity of the fruit
     * @return The status of the fruit
     */
    @Override
    public UFOStatus update() {
        this.setVelocity(velocity.add(acceleration));
        this.setPosition(position.add(velocity));
        return getStatus();
    }

    /**
     * Returns true if the fruit is hit by the player, false otherwise
     * @param pos The position of the player
     * @return True if the fruit is hit by the player, false otherwise
     */
    @Override
    public boolean isHit(Vector2D pos) {
        int radius = 80;

        if(this.getPosition().distanceTo(pos) < radius) {
            return true;
        };

        return false;
    }

    /**
     * Smashes the fruit
     */
    @Override
    public void smash() {
    }

    @Override
    public void slowmotion(float i) {
        setVelocity(velocity.times(i));
        setAcceleration(acceleration.times(i));
        
    }

}