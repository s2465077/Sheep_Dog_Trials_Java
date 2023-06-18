package Objects;

import util.FieldSettings;

public class Pen extends FieldObject {

    private byte[][] area;
    private int penFirstRow;
    private int penFirstCol;
    private int penRow;
    private int penCol;

    private int noSheepInPen;
    public Pen(int row, int col){
        this.noSheepInPen = 0;
        this.penRow = row;
        this.penCol = col;
        this.area = new byte[row][col];
        this.penFirstRow = 0;
        this.penFirstCol = 0;
        this.symbol = 'P';
    }

    // Create a random row number to fit in the entire pen.
    public int getRandomPenRow(FieldSettings settings){
        this.penFirstRow = (int) (Math.random()*(settings.nrRows - settings.penRows));
        return penFirstRow;
    }

    public int getRandomPenCol(FieldSettings settings){
        this.penFirstCol = (int) (Math.random()*(settings.nrCols - settings.penCols));
        System.out.println(penFirstRow + "      FirstRow      " + penFirstCol);
        return penFirstCol;
    }

    public void addSheep(){

        noSheepInPen += 1;

    }

    public int getNoSheepInPen(){
        return noSheepInPen;
    }

    public void setPenFirstRow(int penFirstRow) {
        this.penFirstRow = penFirstRow;
    }

    public void setPenFirstCol(int penFirstCol) {
        this.penFirstCol = penFirstCol;
    }

    public int getPenFirstRow() {
        return penFirstRow;
    }

    public int getPenFirstCol() {
        return penFirstCol;
    }





}
