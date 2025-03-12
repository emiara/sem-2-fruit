package no.uib.inf101.sem2.view;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import no.uib.inf101.sem2.model.fruit.UFO;
import no.uib.inf101.sem2.utils.Vector2D;
import no.uib.inf101.sem2.view.menu.PausedMenu;
import no.uib.inf101.sem2.view.menu.StartMenu;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.AlphaComposite;
import java.awt.Color;

/**
 * A view for the Fruit Ninja game.
 */
public class NinjaView extends JPanel {
    private ViewableNinjaModel model;
    private BufferedImage backgroundImage;
    private BufferedImage keyboardOverlayImage;

    /** Construct a new FruitView */
    public NinjaView(Dimension dimensions, ViewableNinjaModel model) {
        this.setFocusable(true);
        this.setPreferredSize(dimensions);
        this.model = model;
        this.backgroundImage = Inf101Graphics.loadImageFromResources("/images/background.png");
        this.keyboardOverlayImage = Inf101Graphics.loadImageFromResources("/images/keyboard.png");
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (model.getGameState()) {
        case START:
            start(g);
            break;
        case PLAYING:
            playing(g);
            break;
        case PAUSED:
            paused(g);
            break;
        case END:
            end(g);
            break;
        }
    }


    private void end(Graphics g) {
        // Write Game over
        drawBackground(g);
        g.setColor(Color.WHITE);
        // change opacity of text to 50%
    
        // Center "GAME OVER" horizontally and vertically
        FontMetrics fm = g.getFontMetrics();
        int x = (this.getWidth() - fm.stringWidth("GAME OVER")) / 2;
        int y = (this.getHeight() - fm.getHeight()) / 2;
        g.drawString("GAME OVER", x, y);
    
        // Center "Points: " + model.getScore() horizontally and place it below "GAME OVER"
        String pointsString = "Points: " + model.getScore();
        int pointsX = (this.getWidth() - fm.stringWidth(pointsString)) / 2;
        int pointsY = y + fm.getHeight();
        g.drawString(pointsString, pointsX, pointsY);
    }
    


    private void paused(Graphics g) {
        PausedMenu menu = new PausedMenu();
    }


    private void playing(Graphics g) {
        drawBackground(g);
        drawUFOs(g);
        drawKeyboard(g, 0.2f);
        if(model.getDebugStatus()){
            drawDot(g, model.getHitPosition());
        }
        drawScore(g);
    }
    
    /**
     * Draws the score on the screen
     * @param g The graphics object to draw on
     */
    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        // change opacity of text to 50%

        g.drawString("Points: " + model.getScore(), 10, 20);

    }


    private void drawDot(Graphics g, Vector2D hitPosition) {
        g.fillOval((int) hitPosition.getX(), (int) hitPosition.getY(), 10, 10);
    }

    private void drawBackground(Graphics g){
        BufferedImage img = this.getBackgroundImage();
        ImageIcon icon = new ImageIcon(img);
        Image image = icon.getImage();
        g.drawImage(image, 0, 0,this.getWidth(), this.getHeight() + 20, null);
    }

    private void drawKeyboard(Graphics g, float opacity) {
        BufferedImage img = this.keyboardOverlayImage;
        ImageIcon icon = new ImageIcon(img);
        Image image = icon.getImage();
        
        // Create an AlphaComposite with the specified opacity
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(composite);
        
        // Draw the image with the specified opacity
        g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight() + 20, null);
        
        // Reset the composite to fully opaque
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
    
    private void drawUFOs(Graphics g) {
        for (UFO ufo : model.getUFOs()) {
            Vector2D pos = ufo.getPosition();

            int x = (int) pos.getX();
            int y = (int) pos.getY();

            BufferedImage img = ufo.getImage();

            if(img == null){
                System.err.println(img);
            }



            ImageIcon icon = new ImageIcon(img);

            Image image = icon.getImage();

            g.drawImage(image, x, y, null);
        }
    }

    private void start(Graphics g) {
        StartMenu menu = new StartMenu();
        menu.draw(g);
    }

    private BufferedImage getBackgroundImage() {
        return this.backgroundImage;
    }

    private void update(ActionEvent e) {
        this.repaint();
    }
}
