package no.uib.inf101.sem2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.model.fruit.BombOnlyFactory;
import no.uib.inf101.sem2.model.fruit.DefaultFruitFactory;
import no.uib.inf101.sem2.model.fruit.Fruit;
import no.uib.inf101.sem2.model.fruit.FruitFactory;
import no.uib.inf101.sem2.model.fruit.UFO;
import java.awt.Dimension;
public class DefaultFruitFactoryTest {

  @Test
  public void sanityTest() {
    Dimension dimensions = new Dimension(400,200);
    FruitFactory factory = new BombOnlyFactory(dimensions);
    
    UFO fruit = factory.next();

    assertTrue(!(fruit instanceof Fruit));
    


    }

}
