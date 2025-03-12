package no.uib.inf101.sem2;

import no.uib.inf101.sem2.model.NinjaModel;
import no.uib.inf101.sem2.model.fruit.controller.NinjaController;
import no.uib.inf101.sem2.model.fruit.BombOnlyFactory;
import no.uib.inf101.sem2.model.fruit.DefaultFruitFactory;
import no.uib.inf101.sem2.model.fruit.FruitFactory;
import no.uib.inf101.sem2.view.NinjaView;

import javax.swing.JFrame;
import java.awt.Dimension;

/**
 * Main class that launches the Fruit Ninja game.
 */
public class Main {
  public static void main(String[] args) {
      // Set the dimensions of the game window
      Dimension dimensions = new Dimension(1280, 720);

      // Create a factory that only produces bombs
      FruitFactory factory = new DefaultFruitFactory(dimensions);

      // Create a model for the game using the factory
      NinjaModel model = new NinjaModel(factory);

      // Create a view for the game using the model and window dimensions
      NinjaView view = new NinjaView(dimensions, model);

      // Create a controller for the game using the model, view, and window dimensions
      NinjaController controller = new NinjaController(model, view, dimensions);

      // Create a new window for the game and set the content to the view
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Keyboard Warrior");
      frame.setContentPane(view);

      // Set the size of the window to fit the content and make it visible
      frame.pack();
      frame.setVisible(true);
  }
}
