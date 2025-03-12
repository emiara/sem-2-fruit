package no.uib.inf101.sem2.model.fruit.controller;

import no.uib.inf101.sem2.utils.Vector2D;
import no.uib.inf101.sem2.view.NinjaView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import javax.swing.Timer;

public class NinjaController implements KeyListener {
    private ControllableNinjaModel model;
    private KeyToPositionConverter keyToPositionConverter;
    private NinjaView view;
    private Timer spawnTimer;
    private Timer frameRateTimer;
    private boolean keyPressedDown;


    public NinjaController(ControllableNinjaModel model, NinjaView view, Dimension dimensions) {
        this.keyPressedDown = false;
        this.model = model;

        this.view = view;
        this.view.addKeyListener(this);

        this.spawnTimer = new Timer(this.model.getSpawnTimerDelay(), this::clockTick);
        this.spawnTimer.start();
        
        double keyWidthRatio = 170.0 / 2718.0;
        double keyHeightRatio = 155.0 / 930.0;

        double keyMarginWidthRatio = 16.0 / 2718.0;
        double keyMarginHeightRatio = 14.0 / 930.0;
        
        double secondRowOffsetRatio = 90.0 / 2718.0;
        double thirdRowOffsetRatio = 0.0;
        double fourthRowOffsetRatio = 0.0;

        double tabWidthRatio = 270.0 / 2718.0;
        double capsWidthRatio = 320.0 / 2718.0;
        double shiftWidthRatio = 225.0 / 2718.0;



        this.keyToPositionConverter = new KeyToPositionConverter(this.view, keyWidthRatio, keyHeightRatio, keyMarginWidthRatio, keyMarginHeightRatio,
                                                                 secondRowOffsetRatio, thirdRowOffsetRatio, fourthRowOffsetRatio,
                                                                 tabWidthRatio, capsWidthRatio, shiftWidthRatio);



        this.frameRateTimer = new Timer(1000/this.model.getFrameRate() , this::update);
        this.frameRateTimer.start();

    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(keyPressedDown){
            return;
        }

        Vector2D posHit = this.keyToPositionConverter.getPositionOfKey(e);

        if(posHit != null){
            this.model.pokePosition(posHit);
        }

    }


    private void clockTick(ActionEvent e){
        this.model.clockTick();


    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressedDown = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    public void update(ActionEvent e) {
        this.model.frameUpdate();
        this.view.repaint();
    }
}
