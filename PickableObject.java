import java.util.Random;

public class PickableObject extends GameContent {
    // constructer
    public PickableObject( int[] indexes ) {
        setContentType(3);
        setContentIndexes(indexes);
        setPoint();
    }

    // variables
    private int point;
    Random randomPoint = new Random();

    // methods
    public void display() {
        System.out.println("* ");
    }

    public int getPoint() {
        return point;
    }

    private void setPoint() {
        point = randomPoint.nextInt(2)+1;
    }
    
}
