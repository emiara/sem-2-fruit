package no.uib.inf101.sem2.model.fruit;


import no.uib.inf101.sem2.utils.Vector2D;
import no.uib.inf101.sem2.view.Inf101Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;


public class Bomb implements UFO {
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;
    private BufferedImage img;
    private List<BufferedImage> explosionFrames = new ArrayList<BufferedImage>();
    private UFOStatus status;
    private Dimension dimensions;

    /**
     * Create a new bomb at the given position with the given velocity and acceleration.
     * @param position of the bomb
     * @param velocity of the bomb
     * @param acceleration of the bomb
     * @param dimensions of the game area
     */
    public Bomb(Vector2D position, Vector2D velocity, Vector2D acceleration, Dimension dimensions) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.dimensions = dimensions;



        int DIVIDER = 12;

        this.img = Inf101Graphics.loadImageFromResources("/images/ufos/bomb.png");

        this.explosionFrames.add(getBufferedImageFromResources("/images/explosions/1.png", ((int)(dimensions.getWidth() / DIVIDER)), ((int)(dimensions.getHeight() / DIVIDER))));
        this.explosionFrames.add(getBufferedImageFromResources("/images/explosions/2.png", ((int)(dimensions.getWidth() / DIVIDER)), ((int)(dimensions.getHeight() / DIVIDER))));

        this.status = UFOStatus.NORMAL;

        this.dimensions = dimensions;
    }

    BufferedImage getBufferedImageFromResources(String path, int width, int height) {
        try {
            BufferedImage bufferedImage = Inf101Graphics.loadImageFromResources(path);
            if (bufferedImage == null) {
                System.out.println("Image is null for path: " + path);
                return null;
            }
            Image scaledImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(scaledImage, 0, 0, null);
            g.dispose();
            return resizedImage;
        } catch (Exception e) {
            System.out.println("Could not load image from path: " + path);
            return null;
        }
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2D position) {
        this.position = position;
        
    }

    @Override
    public Vector2D getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    @Override
    public Vector2D getAcceleration() {
        return acceleration;
    }

    @Override
    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public String toString() {
        String positionString = position.toString();
        String velocityString = velocity.toString();
        String accelerationString = acceleration.toString();

        return "Bomb [position=" + positionString +
               ", velocity=" + velocityString +
               ", acceleration=" + accelerationString + "]";
    }

    /**
     * @return the image of the bomb
     */
    @Override
    public BufferedImage getImage() {
        return this.img;
    }

    @Override
    public UFOStatus update() {
        this.setVelocity(velocity.add(acceleration));

        this.setPosition(position.add(velocity));
        return getStatus();
    }

    @Override
    public boolean isHit(Vector2D pos) {
        int radius = 90;

        if(this.getPosition().distanceTo(pos) < radius) {
            smash();
            return true;
        };

        return false;

    }

    @Override
    public void smash() {
        this.setStatus(UFOStatus.SLICED);
        this.setImage(explosionFrames.get(0));
        this.update();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setImage(explosionFrames.get(1));
                update();
            }
        }, 350);

    }

    private void setImage(BufferedImage bufferedImage) {
        this.img = bufferedImage;

    }

    private void setStatus(UFOStatus status) {
        this.status = status;
    }
    
    private UFOStatus getStatus() {
        return this.status;
    }

    @Override
    public void slowmotion(float i) {
        this.setAcceleration(acceleration.times(-i));
    }
}
