package Objects;

public class Sheep extends FieldObject {

    private int sheepId;
    private boolean isInPen;
    private char moveDirection;
    private int sheepRow;
    private int sheepCol;

    public Sheep(int id){
        this.isInPen = false;
        this.sheepId = id;
        this.symbol = 'S';

    }

    public void prepareForGameStart(){

    }

    public boolean isInPen() {
        return isInPen;
    }

    public void setInPen(boolean inPen) {
        isInPen = inPen;
    }


    public void movingDirection(){

    }

    public void checkCloseBorder(){

    }

    public void nearDog(){

    }

    public void nearBush(){

    }

}
