package util;

public class FieldSettings {

    public final int nrRows;
    public final int nrCols;
    public final int sheepNo;
    public final int dogNo;
    public final int treeNo;
    public final int penRows;
    public final int penCols;
    public final int isFieldRandom;


    // The minimum sequence length required to win.

    // Loads the default settings if no arguments are provided.
    public FieldSettings()
    {
        this(8, 8, 5, 1, 0, 2, 3 ,1);
    }

    // Creates an instance with the given settings.
    public FieldSettings(int nrRows, int nrCols, int sheepNo, int dogNo, int treeNo, int penRows, int penCols, int isRandom)
    {
        this.nrRows = nrRows;
        this.nrCols = nrCols;
        this.sheepNo = sheepNo;
        this.dogNo = dogNo;
        this.treeNo = treeNo;
        this.penRows = penRows;
        this.penCols = penCols;
        this.isFieldRandom = isRandom;
    }
}
