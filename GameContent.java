abstract public class GameContent {
    // variables
    private int contentType;
    private int[] contentIndexes;
    private int point;

    // methods
    public int getContentType() {
        return contentType;
    }
    public void setContentType(int contentType) {
        this.contentType = contentType;
    }
    public int[] getContentIndexes() {
        return contentIndexes;
    }
    public void setContentIndexes(int[] contentIndexes) {
        this.contentIndexes = contentIndexes;
    }
    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
    abstract public void display();
}
