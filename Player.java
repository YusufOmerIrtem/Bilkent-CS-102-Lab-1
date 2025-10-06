public class Player extends GameContent {
    // constructer
    public Player( int[] indexes, int playerNumber ) {
        setContentType(1);
        setContentIndexes(indexes);
        this.playerNumber = playerNumber;
    }
    
    // variables
    int playerNumber;

    // methods
    public void display() {
        System.out.println(playerNumber + " ");
    }
}
