public class UnpassableBlock extends GameContent {
    // constructer
    public UnpassableBlock( int[] indexes ) {
        setContentType(2);
        setContentIndexes(indexes);
    }

    // methods
    public void display() {
        System.out.println("X ");
    }
}
