package Objects;

import util.InputUtil;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Dog extends FieldObject{

    private char moveDirection;
    private int dogId;
    private char direction;

    public Dog(int dogId){
        this.dogId = dogId;
        this.symbol = 'D';


    }

    //put the dog in a random location on the field.
    public void prepareForGameStart(){


    }

    public char movingDirection() {
        char input = InputUtil.scanChar();
        return input;
    }


    public void checkCloseBorder(){

    }

    /*
    @Override
    public void keyTyped(KeyEvent e) {

        boolean notInput = true;
        do {
            char input = e.getKeyChar();

            switch (input) {
                case 'A':
                    this.direction = 'A';
                    notInput = false;
                    break;
                case 'W':
                    this.direction = 'W';
                    notInput = false;
                    break;
                case 'D':
                    this.direction = 'D';
                    notInput = false;
                    break;
                case 'S':
                    this.direction = 'S';
                    notInput = false;
                    break;
                default:
                    System.out.println("Please enter a valid input.");
            }
        }while(notInput);

    }



    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

     */


}
