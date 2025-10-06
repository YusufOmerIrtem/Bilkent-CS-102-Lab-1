abstract public class GameContent {
    // variables
    private int contentType;
    private int[] contentIndexes;

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
    abstract public void display();
}
