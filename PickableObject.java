import java.util.Random;

public class PickableObject extends GameContent {
    // constructer
    public PickableObject( int[] indexes ) {
        setContentType(3);
        setContentIndexes(indexes);
        setPoint(randomPoint.nextInt(2)+1);
    }

    // variables
    private static Random randomPoint = new Random();

    // methods
    public void display() {
        System.out.print("* ");
    }
}
