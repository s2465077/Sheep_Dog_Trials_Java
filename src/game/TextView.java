package game;

import util.InputUtil;

/**
 * This class is used to interact with the user.
 */

public class TextView {

    public void showWelcomeMessage()
    {
        System.out.println("----------------WELCOME TO SHEEPDOG TRIALS!----------------");
    }

    public void showMoveRejected(char move)
    {
        System.out.println("The move (" + move + ") was rejected, please enter the move again.");
    }

    public void showGameStatus(byte gameStatus)
    {
        System.out.print("\nGame status: ");

        switch(gameStatus)
        {
            case SheepEnvironment.GAME_STATUS_RUNNING: System.out.println("The game is in progress."); break;
            case SheepEnvironment.GAME_STATUS_WIN: System.out.println("------------------Victory!!!------------------\n"); break;
            default : System.out.println("Error: Invalid/unknown game status"); break;
        }
    }

    public void showField(SheepEnvironment envir, int moveSoFar)
    {
        System.out.println("\nMove so far : " + moveSoFar);

        int nrRows = envir.getFieldSettings().nrRows;
        int nrCols = envir.getFieldSettings().nrCols;

        System.out.println("The field has " + nrRows + " rows and " + nrCols + " columns.");

        for (int r = 0; r < nrRows; r++){
            for (int c = 0; c < nrCols; c++) {
                if (envir.getObjectInField(r, c) == 0) {
                    System.out.print('-');
                } else {
                    System.out.print(envir.getObjectInField(r, c));
                }
            }
            System.out.println(' ');
        }
    }

    public char requestViewType()
    {
        char choice;
        // Show menu options and request user input.
        System.out.println("Please select your preferred view type by entering the corresponding number :");
        System.out.print("(1) Only text view");
        System.out.print("                ");
        //System.out.print("(2) Only GUI");
        //System.out.print("                ");
        //System.out.println("(3) Text and GUI view");

        // Return user input.
        System.out.print("Your choice : ");
        choice = InputUtil.scanChar();

        return choice;
    }

    public char requestFieldSettings() {
        System.out.println("\nTo set up a field");
        System.out.println("F (Fixed setup)");
        System.out.println("R (Random setup)");

        System.out.println("\nYou can use the following commands to move the dog.");
        System.out.println("W (UP)");
        System.out.println("A (LEFT)");
        System.out.println("S (DOWN)");
        System.out.println("D (RIGHT)");

        System.out.println("\nTo exit type E");
        System.out.println("Confirm each command by pressing enter");

        System.out.print("Command : ");
        char choice = InputUtil.scanChar();
        System.out.print(choice);

        return choice;

    }

}
