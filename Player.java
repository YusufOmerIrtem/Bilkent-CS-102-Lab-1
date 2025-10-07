import java.util.ArrayList;

public class Player extends GameContent {
    // constructer
    public Player( String name, int[] indexes, int playerNumber ) {
        this.name = name;
        setContentType(1);
        setContentIndexes(indexes);
        this.playerNumber = playerNumber;
        setPoint(5);
        pickedObjects = new ArrayList<GameContent>();
    }
    
    // variables
    private int playerNumber;
    private ArrayList<GameContent> pickedObjects;
    private String name;

    // methods
    public void display() {
        System.out.print(playerNumber + " ");
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
    public void addPickedObject(GameContent pickedObject) {
        pickedObjects.add(pickedObject);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return playerNumber + ") " + name;
    }
    
}
