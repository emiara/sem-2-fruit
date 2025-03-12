package no.uib.inf101.sem2.view.menu;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class StartMenu implements IMenu {
    
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 400, 200);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Fruit Ninja", 150, 50);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Press Enter to start", 150, 100);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Changes gamestate to playing if enter is pressed
     */
    @Override
    public void handleInput() {
        
    }

    @Override
    public void dis() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dis'");
    }
    
}
