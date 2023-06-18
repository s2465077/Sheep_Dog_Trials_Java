package game;

import util.FieldSettings;

/**
 * Controller class is used for holding all he game logic, which control the game session to start,
 * proceed and end. It also controls the data stores in SheepEnvironment Class, including initiate
 * the movement of sheep and the dog, and update the TextView to display the view to the player.
 * It implements the game setting stored at FieldSettings Class and the user input received from InputUtil Class,
 * which are then executed and stored at the “SheepEnvironment” Class.
 *
 */
public class Controller {

    private final SheepEnvironment sheepEnvironment;
    private final TextView view;
    private FieldSettings settings;
    private int moveSoFar;
    private char viewMode;


    public Controller()
    {
        // Create an instance of the SheepEnvironment class and TextView class.
        this.sheepEnvironment = new SheepEnvironment();
        this.view = new TextView();

        // Load default game settings.
        this.settings = new FieldSettings();

    }

    // Starts a session, which can consist of multiple matches.
    public void startSession()
    {
        // Display the welcome message from the TextView class.
        view.showWelcomeMessage();

        do
        {
            // Display the display choice for the user to choose.
            switch(view.requestViewType())
            {
                // Starts the textView setup
                case '1': viewMode = 'T'; startNewGame(textSetup()); break;

                default: return;
            }

        } while(true);
    }

    // Starts a new game with the setting chose.
    private void startNewGame(char setUp)
    {
        // Input the setup for generating an instance of the FieldSetting Class.
        switch (setUp)
        {
            // Fixed setup
            case 'F': this.settings = new FieldSettings(8, 8, 5, 1, 0, 2, 3 ,1); break;
            // Generate random numbers for a random setup.
            case 'R': this.settings = randomFieldSettings(); break;
        }

        // Input the setup into the SheepEnvironment class for setting up the field data and fieldObject.
        sheepEnvironment.initNewGame(settings);

        // Start the game loop.
        startGameLoop();
    }

    // Allow the user to enter the fieldsetting Create a textView.
    private char textSetup()
    {
        char fieldSetup = ' ';
        // Get the setup chosen by the user.
        fieldSetup = changeFieldSettings();
        return fieldSetup;
    }

    // Allows the field settings to be amended by the user.
    private char changeFieldSettings()
    {
        // call the menu for changing setup from TextView.
        char fieldSetup = view.requestFieldSettings();
        return fieldSetup;
    }

    // Generate random numbers for the random field setup.
    private FieldSettings randomFieldSettings()
    {
        // Create random numbers for the grid, pen, sheep number.
        int gridRow = (int) (Math.random()*(40 - 5)) + 5;
        int gridCol = (int) (Math.random()*(40 - 5)) + 5;
        int penRow = (int) (Math.random()*((gridRow / 2) - 2)) + 2;
        int penCol = (int) (Math.random()*((gridCol / 2) - 2)) + 2;
        int sheepNo = (int) (Math.random()*((penRow * penCol) - 2)) + 2;

        // Input those values to create an instance of FieldSettings Class.
        FieldSettings randomField = new FieldSettings(gridRow, gridCol, sheepNo, 1, 3, penRow, penCol, 2);
        return randomField;

    }


    // Control the game loop, proceed the game until the user wins.
    private void startGameLoop()
    {

        // Display the initial state of the board on different display.
        switch (viewMode)
        {
            // Show textView.
            case 'T': view.showField(sheepEnvironment, moveSoFar);break;

        }
        byte gameStatus;

        // continue the game while the game status is recognized as running at SheepEnvironment class.
        while((gameStatus = sheepEnvironment.getGameStatus()) == sheepEnvironment.GAME_STATUS_RUNNING)
        {

            // Sheeps move one step on the field.
            sheepEnvironment.sheepMove();

            // Check game status after sheep move.
            sheepEnvironment.getGameStatus();

            // Ask user to enter a move for the dog.
            char chosenMove = askForMove();

            // The dog move one step on the field.
            sheepEnvironment.startMoving(chosenMove, sheepEnvironment.dog);

            // Increment the move by 1 after completed each move.
            moveSoFar += 1;

            // Show the updated environment of the field.
            view.showField(sheepEnvironment, moveSoFar);

        }
        // The game has ended. Show the winning message.
        view.showGameStatus(gameStatus);
    }

    // Ask the movement for dog from the user.
    private char askForMove()
    {
        char chosenMove;
        boolean moveRejected = false;
        boolean moveNotCharacter = false;

        do
        {
        // Ask the user to enter a movement for the dog.
        chosenMove = sheepEnvironment.dog.movingDirection();

        // Check the move is valid character.
        moveNotCharacter = !sheepEnvironment.isMoveCharacter(chosenMove);
        // Check if the move is valid.
        moveRejected = !sheepEnvironment.isMoveValid(chosenMove, sheepEnvironment.dog);

            // If rejected, show the error message.
            if(moveRejected || moveNotCharacter)
            {
                view.showMoveRejected(chosenMove);
            }
        } while(moveRejected || moveNotCharacter);

        return chosenMove;

    }


}
