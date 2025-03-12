package no.uib.inf101.sem2.model;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.model.fruit.DefaultFruitFactory;
import no.uib.inf101.sem2.model.fruit.FruitFactory;
import no.uib.inf101.sem2.model.fruit.UFO;

import java.awt.Dimension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class NinjaModelTest {
    
    @Test
    public void testSpawnFlyingObject() {
        FruitFactory factory = new DefaultFruitFactory(new Dimension(400,400));
        NinjaModel model = new NinjaModel(factory);
        model.spawnFlyingObject();
        List<UFO> ufos = model.getUFOs();
        assertEquals(1, ufos.size());
    }
    
    @Test
    public void testGetSpawnTimerDelay() {
        FruitFactory factory = new DefaultFruitFactory(new Dimension(400,400));
        NinjaModel model = new NinjaModel(factory);
        assertEquals(500, model.getSpawnTimerDelay());
    }
    
    @Test
    public void testGetGameState() {
        FruitFactory factory = new DefaultFruitFactory(new Dimension(400,400));
        NinjaModel model = new NinjaModel(factory);
        assertEquals(GameState.PLAYING, model.getGameState());
    }
    
    @Test
    public void testClockTick() {
        FruitFactory factory = new DefaultFruitFactory(new Dimension(400,400));
        NinjaModel model = new NinjaModel(factory);
        model.clockTick();
        List<UFO> ufos = model.getUFOs();
        assertEquals(1, ufos.size());
    }
    
    
    
}
