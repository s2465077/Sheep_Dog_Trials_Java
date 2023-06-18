package Objects;

import game.SheepEnvironment;
import util.FieldSettings;

public abstract class FieldObject {

    private int fieldRow;
    private int fieldCol;
    private int randomRow;
    private int randomCol;
    public char symbol;

    public FieldObject(){

    }

    public void prepareRandomStart(FieldSettings settings){

        fieldRow = getRandomRow(settings);
        fieldCol = getRandomCol(settings);
        setFieldRow(fieldRow);
        setFieldCol(fieldCol);
        System.out.println(fieldRow + "field random      " + fieldCol);


    }

    public void prepareFixedStart(int row, int col){
        setFieldRow(row);
        setFieldCol(col);
        System.out.println(fieldRow + "FieldstartRow      " + fieldCol);
    }

    public int getRandomRow(FieldSettings settings){
        //int randomRow = (int) (Math.random()*((settings.nrRows) - settings.penRows)) + settings.penRows;
        int randomRow = (int) (Math.random()*(settings.nrRows));
        this.fieldRow = randomRow;
        return randomRow;
    }

    public int getRandomCol(FieldSettings settings){
        //int randomCol = (int) (Math.random()*((settings.nrCols) - settings.penCols)) + settings.penCols;
        int randomCol = (int) (Math.random()*(settings.nrCols));
        this.fieldCol = randomCol;
        return randomCol;
    }

    public int getFieldRow() {
        return fieldRow;
    }

    public void setFieldRow(int fieldRow) {
        this.fieldRow = fieldRow;
    }

    public int getFieldCol() {
        return fieldCol;
    }

    public void setFieldCol(int fieldCol) {
        this.fieldCol = fieldCol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

}
