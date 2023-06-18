package util;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);
    //private static final Robot r = new Robot();

    public static int scanInt()
    {
        // At least once and until valid input is received:
        do
        {
            // Wait for new input and check if it is an integer.
            if(scanner.hasNextInt())
            {
                // Obtain the integer value and return it after
                // consuming the rest of the input line (if any).
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            }

            // The input was not an integer, print an error and repeat.
            System.out.print("Please enter an integer: ");

            // Consume the rest of the line.
            scanner.nextLine();
        }
        while(true);
    }

    public static char scanChar()
    {
        //Toolkit.init();
        //Robot r = new Robot();
        //try {
        //    Robot r = new Robot();
        //} catch (AWTException e) {
        //    throw new RuntimeException(e);
        //}

        Robot r;
        try {
            r = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        //try {
         //Thread.sleep(2000);
        //}  catch (InterruptedException e){
       // TODO Auto-generated catch block
        //e.printStackTrace();
        //}
        // At least once and until valid input is received:

        do
        {
            // Wait for a new line of text to be entered.
            if(scanner.hasNextLine())
            {
                // Obtain the user input.
                //String input = scanner.nextLine();
                String input = scanner.nextLine();
                //r.keyPress(KeyEvent.VK_N);
                //r.keyPress(KeyEvent.VK_ENTER);
                //r.keyRelease(KeyEvent.VK_ENTER);
                //r.keyRelease(KeyEvent.VK_N);
                //r.waitForIdle();






                //Char input = Toolkit.readCharacter();

                // If a single char was entered, return it.
                if(input.length() == 1) {
                    System.out.println("INPUT ~~~~~~" + input);
                    return input.charAt(0);
                }
            }


            /*
            try {
                Thread.sleep(2000);
                }  catch (InterruptedException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
                }
                // At least once and until valid input is received:
            */
            // Invalid input length, print an error and repeat.
            System.out.print("Please enter a single character: ");
        }
        while(true);
    }


    //public void keyTyped(KeyEvent key){
        //char input = key.getKeyChar();
        //return input;

    //}

    /*
    public char keyEnter(KeyEvent key){
        int keyC = key.getKeyChar();
        boolean keyInputed = false;
        char input = ' ';
        do {
            if (keyC == KeyEvent.VK_D) {
                input = 'D';
            } else if (keyC == KeyEvent.VK_A) {
                input = 'A';
            } else if (keyC == KeyEvent.VK_W) {
                input = 'W';
            } else if (keyC == KeyEvent.VK_S) {
                input = 'S';
            } else {
                System.out.print("Please enter a single character: ");
            }
        }while(!keyInputed);

        return input;

    }

     */



}
