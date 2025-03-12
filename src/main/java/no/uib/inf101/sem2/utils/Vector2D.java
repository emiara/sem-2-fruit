package no.uib.inf101.sem2.utils;

/**
 * A position in the game.
 * @param x The x coordinate
 * @param y The y coordinate
 * @return A new position
 * @see no.uib.inf101.sem2.model.NinjaModel
 */

public class Vector2D {
    private final float x;
    private final float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x coordinate
     * @return int x
     */
    public float getX() {
        return x;
    }

    /**
     * Get the y coordinate
     * @return int y
     */
    public float getY() {
        return y;
    }

    /**
     * Add another position to this one
     * @param Vector2D other
     * @return A new position
     */
    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    /**
     * Subtract another position from this one to get distance
     * 
     * @param other The other position
     * @return The distance
     */
    public int distanceTo(Vector2D other) {
        return (int) Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    @Override
    public String toString() {
        return "Vector2D [x=" + x + ", y=" + y + "]";
    }

    public Vector2D minus(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }

    public Vector2D abs() {
        return new Vector2D(Math.abs(x), Math.abs(y));
    }

    public Vector2D times(float d) {
        return new Vector2D((float) (x * d), (float) (y * d));
    }



    
}
