public class UnoccupiedCell extends GameContent {
    // constructer
    public UnoccupiedCell( int[] indexes ){
        setContentType(0);
        setContentIndexes(indexes);
    }

    // methods
    public void display() {
        System.out.println(". ");
    }
}
