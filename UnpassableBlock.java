public class UnpassableBlock extends GameContent {
    // constructer
    public UnpassableBlock( int[] indexes ) {
        setContentType(2);
        setContentIndexes(indexes);
        setPoint(0);
    }

    // methods
    public void display() {
        System.out.print("X ");
    }
}
