public class PickableObject extends GameContent {
    // constructer
    public PickableObject( int[] indexes ) {
        setContentType(3);
        setContentIndexes(indexes);
    }

    //methods
    public void display() {
        System.out.println("* ");
    }
}
