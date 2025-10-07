public class UnoccupiedCell extends GameContent {
    // constructer
    public UnoccupiedCell( int[] indexes ){
        setContentType(0);
        setContentIndexes(indexes);
        setPoint(0);
    }

    // methods
    public void display() {
        System.out.print(". ");
    }
}
