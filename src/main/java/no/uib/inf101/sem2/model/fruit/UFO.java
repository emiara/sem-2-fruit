package no.uib.inf101.sem2.model.fruit;

import no.uib.inf101.sem2.utils.Vector2D;

import java.awt.image.BufferedImage;

public interface UFO {
     
     // Method to get the position of the fruit
     Vector2D getPosition();

     // Method to set the position of the fruit
     void setPosition(Vector2D position);
 
     // Method to get the velocity of the fruit
     Vector2D getVelocity();
 
     // Method to set the velocity of the fruit
     void setVelocity(Vector2D velocity);
 
     // Method to get the acceleration of the fruit
     Vector2D getAcceleration();
 
     // Method to set the acceleration of the fruit
     void setAcceleration(Vector2D acceleration);

     // Method to get string representation of the fruit
     String toString();

     // Method to get the image of the fruit
     BufferedImage getImage();
     
     // Update the fruit physics
    UFOStatus update();

    // Method to check if the fruit is hit
    boolean isHit(Vector2D pos);

    // Method to smash the fruit
    void smash();

    void slowmotion(float i);

    
}




     
