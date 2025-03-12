package no.uib.inf101.sem2.model.fruit.controller;

import no.uib.inf101.sem2.utils.Vector2D;
import no.uib.inf101.sem2.view.NinjaView;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import java.util.List;
import java.util.stream.Collectors;

public class KeyToPositionConverter {
    private Dimension dimensions;
    private NinjaView view;
    private double keyWidthRatio;
    private double keyHeightRatio;
    private double keyMarginHeightRatio;
    private double keyMarginWidth;

    private double secondRowOffsetRatio;
    private double thirdRowOffsetRatio;
    private double fourthRowOffsetRatio;

    private double tabWidthRatio;
    private double capsWidthRatio;
    private double shiftWidthRatio;

    private List<Character> row1;
    private List<Character> row2;
    private List<Character> row3;
    private List<Character> row4;

    public KeyToPositionConverter(NinjaView view,
                                  double keyWidthRatio,        double keyHeightRatio,      double keyMarginWidthRatio, double keyMarginHeightRatio,
                                  double secondRowOffsetRatio, double thirdRowOffsetRatio, double fourthRowOffsetRatio,
                                  double tabWidthRatio,        double capsWidthRatio,      double shiftWidthRatio){

        this.view = view;
        
        this.keyWidthRatio = keyWidthRatio;
        this.keyHeightRatio = keyHeightRatio;
        this.keyMarginWidth = keyMarginWidthRatio;
        this.keyMarginHeightRatio = keyMarginHeightRatio;

        this.secondRowOffsetRatio = secondRowOffsetRatio;
        this.thirdRowOffsetRatio = thirdRowOffsetRatio;
        this.fourthRowOffsetRatio = fourthRowOffsetRatio;

        this.tabWidthRatio = tabWidthRatio;
        this.capsWidthRatio = capsWidthRatio;
        this.shiftWidthRatio = shiftWidthRatio;


        // Convert string of each keyboard row to a list of char
        row1 = "'1234567890+´".chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        row2 = "qwertyuiopå¨".chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        row3 = "asdfghjkløæ@".chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        row4 = "<zxcvbnm,.-".chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    }


    private double getkeyWidth(){
        return this.view.getWidth() * this.keyWidthRatio;
    }

    private double getkeyHeight(){
        return this.view.getHeight() * this.keyHeightRatio;
    }

    private double getkeyMarginWidth(){
        return this.view.getWidth() * this.keyMarginWidth;
    }
    
    private double getkeyMarginHeight(){
        return this.view.getHeight() * this.keyMarginHeightRatio;
    }

    private double getSecondRowOffset(){
        return this.view.getWidth() * this.secondRowOffsetRatio;
    }

    private double getThirdRowOffset(){
        return this.view.getWidth() * this.thirdRowOffsetRatio;
    }

    private double getFourthRowOffset(){
        return this.view.getWidth() * this.fourthRowOffsetRatio;
    }

    private double getTabWidth(){
        return this.view.getWidth() * this.tabWidthRatio;
    }

    private double getCapsWidth(){
        return this.view.getWidth() * this.capsWidthRatio;
    }

    private double getShiftWidth(){
        return this.view.getWidth() * this.shiftWidthRatio;
    }

    public Vector2D getPositionOfKey(KeyEvent e){
        char keyChar = e.getKeyChar(); // Get the character of the pressed key
    
        int row = -1; // Initialize row to -1 (invalid value)
        int col = -1; // Initialize col to -1 (invalid value)
    
        // Check if keyChar is present in row1, row2, or row3
        if (row1.contains(keyChar)) {
            row = 1;
            col = row1.indexOf(keyChar);
        } else if (row2.contains(keyChar)) {
            row = 2;
            col = row2.indexOf(keyChar);
        } else if (row3.contains(keyChar)) {
            row = 3;
            col = row3.indexOf(keyChar);
        } else if (row4.contains(keyChar)){
            row = 4;
            col = row4.indexOf(keyChar);
        }
    
        // Calculate x and y position based on row, col, dimensions, keyMargin, and keyWidth
        double x = -1;
        double y = -1;
        if (row != -1 && col != -1) {
            // Print out every value used to calculate the position
            System.out.println("row: " + row);
            System.out.println("col: " + col);
            System.out.println("keyWidth: " + getkeyWidth());
            System.out.println("keyHeight: " + getkeyHeight());
            System.out.println("keyMarginWidth: " + getkeyMarginWidth());
            System.out.println("keyMarginHeight: " + getkeyMarginHeight());
            System.out.println("secondRowOffset: " + getSecondRowOffset());
            System.out.println("thirdRowOffset: " + getThirdRowOffset());
            System.out.println("fourthRowOffset: " + getFourthRowOffset());
            System.out.println("viewWidth: " + this.view.getWidth());
            System.out.println("viewHeight: " + this.view.getHeight());

            x = getkeyMarginWidth() + col * (getkeyWidth() + getkeyMarginWidth());
            if (row == 1) {
                y = getkeyMarginHeight();
            } else if (row == 2) {
                y = getkeyMarginHeight() + (this.view.getHeight() / 5);
                x += getTabWidth();
            } else if (row == 3) {
                y = getkeyMarginHeight() + 2 * (this.view.getHeight() / 5);
                x += getCapsWidth();
            } else if (row == 4){
                y = getkeyMarginHeight() + 3 * (this.view.getHeight() / 5);
                x += getShiftWidth();
            }
            x += getkeyWidth() / 2;
            y += getkeyHeight() / 2;


            // Return the calculated position as a Vector2D object
            Vector2D res = new Vector2D((float) x,(float) y);

            return res;
        }
        return null;
    }
}
