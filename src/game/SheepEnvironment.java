package game;

import util.FieldSettings;
import Objects.*;
import java.util.*;

/**
 * SheepEnvironment class is used for holding the game status and store the data, including
 * the position of sheep and the dog, as well as other objects on the field.
 *
 */

public class SheepEnvironment {

    public static final byte GAME_STATUS_RUNNING = 0;
    public static final byte GAME_STATUS_WIN = 1;
    private char[][] field;
    private FieldSettings settings;
    private int Rows;
    private int Cols;
    private int penRows;
    private int penCols;
    public int sheepNo;
    public int dogNo;
    private int treeNo;
    private int movedRow;
    private int movedCol;
    private final String chars = "WASD";


    public Pen pen;
    public List<Sheep> sheeps;
    public List<Bush> trees;
    public List<Dog> dogs;
    public Dog dog;
    public int state;
    public int sheepsCollected;
    public Random rand;



    public SheepEnvironment(){

        this.sheepsCollected = 0;
        this.state = GAME_STATUS_RUNNING;

    }

    // Called when a new game is started on an empty board.
    public void initNewGame(FieldSettings fieldSettings) {
        this.settings = fieldSettings;
        this.Rows = fieldSettings.nrRows;
        this.Cols = fieldSettings.nrCols;
        this.field = new char[Rows][Cols];

        this.sheeps = new ArrayList<Sheep>();
        this.trees = new ArrayList<Bush>();
        this.dogs = new ArrayList<Dog>();

        this.sheepNo = fieldSettings.sheepNo;
        this.dogNo = fieldSettings.dogNo;
        this.treeNo = fieldSettings.treeNo;
        this.penRows = fieldSettings.penRows;
        this.penCols = fieldSettings.penCols;
        this.pen = new Pen(fieldSettings.penRows, fieldSettings.penCols);

        this.rand = new Random();

        // Put the pen at random or fixed area.
        if(fieldSettings.isFieldRandom == 2) {
            putPenAtRandomArea();
        }else if(fieldSettings.isFieldRandom == 1){
        putPenAtFixedArea();
        }

        // Put objects at random location or fixed location

        //Create the sheep and put them on the field
        Sheep sheep;
        for (int i = 1; i <=sheepNo; i++) {
            sheep = new Sheep(i);

            // Put the sheep at random or fixed location at the field.
            if(fieldSettings.isFieldRandom == 2) {
                setRandomObject(sheep);
            }else if(fieldSettings.isFieldRandom == 1){
                setFixedObject(sheep, i, 4);
            }
            sheeps.add(sheep);
        }

        //Create bush and put them on the field randomly
        Bush tree;
        for (int i = 0; i < treeNo; i++) {
            tree = new Bush(i);
            // Put the tree in random or fixed location on the field.
            if(fieldSettings.isFieldRandom == 2) {
                setRandomObject(tree);
            }else if(fieldSettings.isFieldRandom == 1){
                setFixedObject(tree, i, 5);
            }
            setRandomObject(tree);
            trees.add(tree);
        }

        dog = new Dog(1);
        // Put the dog at a random position at the field.
        setRandomObject(dog);
    }

    // Set the objects at fixed position on the field.
    public void setFixedObject(FieldObject object, int row, int col){
        int rows = row;
        do{
            object.prepareFixedStart(rows, col);
            if(field[rows][col] == 0) {
                field[rows][col] = object.symbol;
                break;
            } else if ((field[rows][col] != 0)){
            rows ++;}
        }while(!isSpotAvailable(rows, col));
    }


    // Set the objects at random position on the field.
    public void setRandomObject(FieldObject object){
        do{
            object.prepareRandomStart(settings);
            if(field[object.getFieldRow()][object.getFieldCol()] == 0) {
                field[object.getFieldRow()][object.getFieldCol()] = object.symbol;
                break;
            }
        }while(!isSpotAvailable(object.getFieldRow(), object.getFieldCol()));
    }

    // Put the pen at Top Left corner of the field.
    public void putPenAtFixedArea(){
        pen.setPenFirstCol(0);
        pen.setPenFirstRow(0);
        for (int r = 0; r < settings.penRows; r++) {
            for (int c = 0; c < settings.penCols; c++) {
                field[r][c] = pen.symbol;
            }
        }
    }

    // Put the pen at random area of the field.
    public void putPenAtRandomArea(){
        int i = pen.getRandomPenRow(settings);
        int j = pen.getRandomPenCol(settings);

        for(int row = i; row < (penRows + i); row++){
            System.out.println(row + "r pen    c C" + i);
            for (int col = j; col < (penCols + j); col++){
                    field[row][col] = pen.symbol;
            }
        }
    }

    public void startMoving(char move, FieldObject object) {

        int nrRows = this.Rows;
        int nrCols = this.Cols;

        moveToStep(move, object);
        int movingRow = this.movedRow;
        int movingCol = this.movedCol;

        int originalRow = object.getFieldRow();
        int originalCol = object.getFieldCol();

        if(isRCInBounds(movingRow, movingCol)) {
                if (field[movingRow][movingCol] == 0) {
                    field[movingRow][movingCol] = object.symbol;
                    // set back the original position on the field to empty.
                    field[originalRow][originalCol] = 0;
                    object.setFieldRow(movingRow);
                    object.setFieldCol(movingCol);

                }
        }
    }

    // Check if the sheep moved into the pen
    public void checkMoveToPen(char move, Sheep sheep) {

        int i = pen.getPenFirstRow();
        int j = pen.getPenFirstCol();

        moveToStep(move, sheep);
        int movingRow = this.movedRow;
        int movingCol = this.movedCol;

        int originalRow = sheep.getFieldRow();
        int originalCol = sheep.getFieldCol();

        if(isRCInBounds(movingRow, movingCol)) {
            if (field[movingRow][movingCol] == 'P' || field[movingRow][movingCol] == 'X') {
                // trigger the Pen function.
                pen.addSheep();

                // Label the sheep status that it is in the pen.
                sheep.setInPen(true);

                boolean sheepNotAdded = true;

                int row = i;
                while(sheepNotAdded && row < (penRows + i)){
                    for (int col = j; col < (penCols + j); col++) {
                        if (field[row][col] == 'P') {
                            field[row][col] = 'X';
                            sheepNotAdded = false;
                            break;
                        }
                    }
                    row ++;
                }
                // set back the original position on the field to empty.
                field[originalRow][originalCol] = 0;
            }
        }
    }


    // Create movement for sheeps.
    public void sheepMove(){
        for (Sheep sheep : sheeps){

            int activeRowS = sheep.getFieldRow();
            int activeColS = sheep.getFieldCol();
            char movement = ' ';

            boolean findSheep = false;
            boolean findDog = false;
            boolean findPen = false;

            if(!sheep.isInPen()) {

                // Adjust the sheep's detecting distance for different objects on the field.
                int findSheepRange = (int) Rows / 2;
                int findPenRange = 2;
                int findDogRange = (int) Rows / 2;
                int count = 1;

                // Check for positions of other sheeps and run towards other sheeps.
                for(Sheep sheepFind : sheeps){
                    while((sheepFind != sheep) && !sheepFind.isInPen() && count<=findSheepRange && !findSheep) {
                            if(checkAnyClosedOBject(count, activeRowS, activeColS, sheepFind)){
                                movement = findFlockingMove(sheep, sheepFind, count);
                                findSheep= true;
                                break;
                            } count++;
                    }
                }

                // Check for pen position and run away from the pen.
                for(int i = 1; i<=findPenRange; i++) {
                    if(checkAnyClosedOBject(i, activeRowS, activeColS, pen)) {
                        movement = findSuitableMove(sheep, pen, i);
                        findPen = true;
                    }
                }

                // Check for dog position and run away from the dog.
                for(int i = 1; i<=findDogRange; i++) {
                    if(checkAnyClosedOBject(i, activeRowS, activeColS, dog)) {
                        movement = findSuitableMove(sheep, dog, i);
                        findDog = true;
                    }
                }

                // Create a random movement if no other field object is found.
                if(!findSheep && !findDog && !findPen){
                    do {
                        movement = chars.charAt(rand.nextInt(chars.length()));
                    }while(!isSheepMoveValid(movement, sheep));
                }

                // Check if sheep run into the pen after movement is decided. Change the pen status if sheep is caught.
                checkMoveToPen(movement, sheep);

                startMoving(movement, sheep);
            }

        }

    }

    public char findFlockingMove(Sheep sheep, FieldObject passiveO, int findingRange){

        char movement = chars.charAt(rand.nextInt(chars.length()));

        switch (checkObjectPosition(sheep, passiveO, findingRange)){
            case 'D':
                movement = rotateForValidMove('D', 'S', 'W', 'A',sheep);break;

            case 'W':
                movement = rotateForValidMove('W', 'D', 'A', 'S',sheep);break;

            case 'A':
                movement = rotateForValidMove('A', 'W', 'S', 'D',sheep);break;

            case 'S':
                movement = rotateForValidMove('S', 'A', 'D', 'W',sheep);break;

            // Provide a movement for running from the dog at the diagonal direction.
            case 'Q':
                movement = rotateForValidMove('W', 'A', 'S', 'D',sheep);break;

            case 'Z':
                movement = rotateForValidMove('S', 'A', 'D', 'W',sheep);break;

            case 'E':
                movement = rotateForValidMove('W', 'D', 'S', 'A',sheep);break;

            case 'C':
                movement = rotateForValidMove('D', 'S', 'A', 'W',sheep);break;

            case 'X': movement = 'X'; break;
        }
        return movement;

    }

    // Create a movement that the sheep run away from the passive Object.
    public char findSuitableMove(Sheep sheep, FieldObject passiveO, int findRange){

        char movement = chars.charAt(rand.nextInt(chars.length()));
        switch (checkObjectPosition(sheep, passiveO, findRange)){
            case 'D':
                movement = rotateForValidMove('A', 'W', 'S', 'D',sheep);break;

            case 'W':
                movement = rotateForValidMove('S', 'A', 'D', 'W',sheep);break;

            case 'A':
                movement = rotateForValidMove('D', 'S', 'W', 'A',sheep);break;

            case 'S':
                movement = rotateForValidMove('W', 'D', 'A', 'S',sheep);break;

            // Provide a movement for running from the dog at the diagonal direction.
            case 'Q':
                movement = rotateForValidMove('D', 'S', 'A', 'W',sheep);break;

            case 'Z':
                movement = rotateForValidMove('W', 'D', 'S', 'A',sheep);break;

            case 'E':
                movement = rotateForValidMove('S', 'A', 'D', 'W',sheep);break;

            case 'C':
                movement = rotateForValidMove('W', 'A', 'S', 'D',sheep);break;

            case 'X': movement = 'X';break;
        }
        return movement;

    }




    // Sheeps select another moving direction, if the most suitable move is not valid
    public char rotateForValidMove(char move1, char move2, char move3, char move4, Sheep sheep){
        char move;
        move = move1;


        if (!isSheepMoveValid(move1, sheep)){
            move = move2;
            if (!isSheepMoveValid(move2, sheep)){
                move = move3;

                if (!isSheepMoveValid(move3, sheep)) {
                    move = move4;
                }
            }
        }
        return move;
    }

    public char checkObjectPosition(FieldObject activeO, FieldObject passiveO, int length){
        // Adjust the sensing distance for the field size.
        char objectPos = 'X';

        int activeRow = activeO.getFieldRow();
        int activeCol = activeO.getFieldCol();

        objectPos = checkForDirection(length, activeRow, activeCol, passiveO);

        return objectPos;
    }



    // Check the direction of other object from the particular object.
    public char checkForDirection(int i, int row, int col, FieldObject object){
        char direction = ' ';

        if(checkOneDirection(row,col + i, object)){
            direction = 'D';

        } else if (checkOneDirection(row+ i,col, object)){
            direction = 'S';

        } else if (checkOneDirection(row- i,col, object)){
            direction = 'W';

        } else if (checkOneDirection(row,col- i, object)) {
            direction = 'A';

        } else if (checkOneDirection(row + i,col + i, object)) {
            direction = 'C';

        } else if (checkOneDirection(row - i,col - i, object)) {
            direction = 'Q';

        } else if (checkOneDirection(row + i,col - i, object)) {
            direction = 'Z';

        } else if (checkOneDirection(row - i,col + i, object)) {
            direction = 'E';

        }
        return direction;

    }

    // Check if there is any other object at all 8 directions.
    public boolean checkAnyClosedOBject(int i, int row, int col, FieldObject object){
        if(checkOneDirection(row,col + i, object) || checkOneDirection(row+ i,col, object)
                //check for object aligning vertically
                || checkOneDirection(row- i,col, object) || checkOneDirection(row,col- i, object)
                //check for diagonal down right object
        ||checkOneDirection(row+ i,col+i, object) || checkOneDirection(row - i,col- i, object)
                //check for diagonal down left object
                ||checkOneDirection(row+ i,col-i, object) || checkOneDirection(row - i,col+ i, object)){
            return true;
        } else{
            return false;
        }
    }

    // Check if there is any other object at a particular direction.
    public boolean checkOneDirection(int row, int col, FieldObject object) {
        if (isRCInBounds(row, col)) {
            if (field[row][col] == object.symbol) {
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }
    }

    public void moveToStep(char move, FieldObject object){
        switch(move)
        {
            case 'W': this.movedRow = object.getFieldRow() - 1;this.movedCol = object.getFieldCol();
                break;
            case 'A': this.movedCol = object.getFieldCol() - 1;this.movedRow = object.getFieldRow();
                break;
            case 'S': this.movedRow = object.getFieldRow() + 1;this.movedCol = object.getFieldCol();break;
            case 'D': this.movedCol = object.getFieldCol() + 1;this.movedRow = object.getFieldRow(); break;
            default : System.out.println("Error: Invalid/unknown input"); break;
        }

    }



    // Check if the input from the user is the valid character for moving directions.
    public boolean isMoveCharacter(char move){
        if(move == 'W'  || move == 'A' || move == 'S' || move =='D')
            return true;
        return false;
    }

    // Returns whether or not the passed in move is valid at this time.
    public boolean isMoveValid(char move, FieldObject object) {

        moveToStep(move, object);

        if(isSpotAvailable(this.movedRow, this.movedCol))
            return true;
        return false;

    }

    public boolean isSheepMoveValid(char move, Sheep sheep){
        moveToStep(move, sheep);

        if(isRCInBounds(this.movedRow, this.movedCol) &&
                (field[this.movedRow][this.movedCol] == 0 ||
                        field[this.movedRow][this.movedCol] == 'P' || field[this.movedRow][this.movedCol] == 'X'))
            return true;
        return false;

    }

    // Test if the target spot at the field is available for the object to move.
    public boolean isSpotAvailable(int row, int col){
        if(isRowInBounds(row) && isColInBounds(col) && field[row][col] == 0)
            return true;
        return false;
    }

    public boolean isRCInBounds(int row, int col){
        if(isRowInBounds(row) && isColInBounds(col))
            return true;
        return false;
    }
    private boolean isRowInBounds(int row){
        if(row >= 0 && row < Rows)
            return true;
        return false;
    }

    private boolean isColInBounds(int col){
        if(col >= 0 && col < Cols)
            return true;
        return false;
    }

    public char getObjectInField(int row, int col){

        char piece;
        return field[row][col];

    }


    public FieldSettings getFieldSettings() {
        return settings;
    }


    // Check if all sheeps go inside the pen.
    public boolean checkWin() {

        if(pen.getNoSheepInPen() == settings.sheepNo)
            return true;
        return false;

    }

    public byte getGameStatus() {
        if(checkWin())
            return 1;
        return 0;
    }


}
