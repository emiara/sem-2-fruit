package no.uib.inf101.sem2.model;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.sem2.model.fruit.controller.ControllableNinjaModel;
import no.uib.inf101.sem2.utils.Vector2D;
import no.uib.inf101.sem2.view.ViewableNinjaModel;
import no.uib.inf101.sem2.model.fruit.Bomb;
import no.uib.inf101.sem2.model.fruit.Fruit;
import no.uib.inf101.sem2.model.fruit.FruitFactory;
import no.uib.inf101.sem2.model.fruit.UFO;
import no.uib.inf101.sem2.model.fruit.UFOStatus;

public class NinjaModel implements ControllableNinjaModel, ViewableNinjaModel{

    // Instance variables
    private FruitFactory factory; // A FruitFactory object used to create Fruit and Bomb objects
    private List<Fruit> fruits; // A List of Fruit objects on the screen
    private List<Bomb> bombs; // A List of Bomb objects on the screen
    private GameState gameState = GameState.PLAYING; // The current game state, default is PLAYING
    private int score = 0; // The player's score, starts at 0
    private static int frameRate = 60; // The frame rate of the game, default is 60
    private boolean debugStatus = false; // Whether debug mode is enabled, default is true
    private Vector2D hitPosition = new Vector2D(0,0); // The position of the player's last hit, default is (0,0)
    private int bombsHit = 0;
    /**
     * Creates a new NinjaModel object.
     * @param factory
     */
    public NinjaModel(FruitFactory factory){
        this.factory = factory; // Initialize the FruitFactory object
        fruits = new ArrayList<Fruit>(); // Initialize the List of Fruit objects
        bombs = new ArrayList<Bomb>(); // Initialize the List of Bomb objects
    }

    // Getters and setters // 
    
    /**
     * Returns thep last position of the player's hit.
     * @returns the last position of the player's hit.
     */
    public Vector2D getHitPosition() {
        return hitPosition;
    }

    /**
     * Gets the frame rate of the game.
     * @returns the frame rate of the game.
     */
    @Override
    public int getFrameRate() {
        return frameRate;
    }

    /**
     * Gets a list of all the Fruit objects on the screen.
     * @returns a list of all the Fruit objects on the screen.
     */
    public List<UFO> getUFOs(){
        List<UFO> ufos = new ArrayList<UFO>();
        ufos.addAll(fruits); // Add all Fruit objects to the List of UFO objects
        ufos.addAll(bombs); // Add all Bomb objects to the List of UFO objects
        return ufos;
    }
    
    /**
     * Spawns a new Fruit or Bomb object.
     * The object is added to the List of UFO objects.
     * If it's a Fruit, it's added to the List of Fruit objects.
     * If it's a Bomb, it's added to the List of Bomb objects.
     */
    public void spawnFlyingObject(){
        UFO fruit = factory.next(); // Get the next UFO object from the FruitFactory

        if(fruit instanceof Fruit){
            fruits.add((Fruit) fruit); // If it's a Fruit, add it to the List of Fruit objects
        } else if(fruit instanceof Bomb){
            bombs.add((Bomb) fruit); // If it's a Bomb, add it to the List of Bomb objects
        }
    }

    /**
     * Increases the player's score.
     * @param increase the amount to increase the player's score by.
     */
    public void increasePoints(int increase){
        this.score += increase; // Add the increase to the player's score
    }

    

    // Method to pause the game
    @Override
    public boolean pauseGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pauseGame'");
    }

    @Override
    /**
     * Get spawn timer delay in milliseconds.
     */
    public int getSpawnTimerDelay() {
        return 500; // Return the spawn timer delay, which is 500 milliseconds
    }

    // Method from the ViewableNinjaModel interface
    @Override
    public GameState getGameState() {
        return this.gameState; // Return the current game state
    }

    // Method to spawn a new UFO object on every clock tick
    @Override
    public void clockTick() {
        this.spawnFlyingObject();
    }
    
    // Method to update the frame rate and status of UFO objects
    public void frameUpdate(){
        for(UFO ufo : this.getUFOs()){
            UFOStatus ufoStatus = ufo.update(); // Update the UFO object's status
            if(ufoStatus == UFOStatus.SLICED){
                setFrameRate(20); // If the UFO is sliced, set the frame rate to 20
            }
        }
    }


    /**
     * Sets the frame rate of the game.
     * @param i the frame rate to set the game to.
     */
    private void setFrameRate(int i) {
        this.frameRate = i;
    }

    /**
     * Smashes the UFO object at the given position.
     * @return true if the UFO object was smashed, false otherwise.
     */
    @Override
    public boolean pokePosition(Vector2D pos) {
        boolean hit = false;

        if(this.getDebugStatus() == true){
            hitPosition = pos;
        }

        for(UFO ufo : this.getUFOs()){
            if(ufo.isHit(pos)){
                hit = true;
                if(ufo instanceof Fruit){
                    increasePoints(3); // Increase the player's score by 1
                    // Remove ufo from fruits list
                    fruits.remove(ufo);
                    
                } else if(ufo instanceof Bomb){
                    if(bombsHit >= 3) {
                        setGameState(GameState.END); // Set the game state to GAME_OVER
                    } else {
                        bombsHit++;
                    }
                }
                ufo.slowmotion(0.2f);
                ufo.smash();
            }
        }
        return hit;
    }

    private void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean getDebugStatus() {
        return this.debugStatus;
    }

    /**
     * Slows down all UFO objects.
     * @param i the amount to slow down the UFO objects by.
     */
    private void slowMotionAll(float i) {
        for(UFO ufo : this.getUFOs()){
            ufo.slowmotion(i);
        }
    }

    @Override
    public int getScore() {
        return this.score;
    }
}
