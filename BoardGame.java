public class BoardGame {
    // constructer
    public BoardGame( String[] playerNames ) {
        // initialise players
        players = new Player[playerNames.length];
        for( int i = 0; i < playerNames.length; i++ ) {
            if( i == 0 ) {
                int[] indexes = {0,0};
                players[i] = new Player(playerNames[i], indexes, i+1);
            }
            else if( i == 1 ) {
                int[] indexes = {0,10};
                players[i] = new Player(playerNames[i], indexes, i+1);
            }
            else if( i == 2 ) {
                int[] indexes = {10,0};
                players[i] = new Player(playerNames[i], indexes, i+1);
            }
            else if( i == 3 ) {
                int[] indexes = {10,10};
                players[i] = new Player(playerNames[i], indexes, i+1);
            }
        }
        
        // create instance variables
        board = new Map(players);
        scores = new int[playerNames.length];
    }

    // variables
    private Player[] players;
    private Map board;
    private int[] scores;
    final private static int NORTH = 4;
    final private static int EAST = 3;
    final private static int SOUTH = 2;
    final private static int WEST = 1;

    // methods
    public boolean move(Player player, int direction, int numberOfCells ) {
        int[] newIndexes = {0,0};
        int[] oldIndexes = {player.getContentIndexes()[0],player.getContentIndexes()[1]};
        // check whether the move can be played or not
        if( direction == WEST ) {
            if( player.getContentIndexes()[1]-numberOfCells < 0 ) return false;
            for( int i = 1; i < numberOfCells; i++ ) {
                if( board.getGameMap()[player.getContentIndexes()[0]][player.getContentIndexes()[1]+i].getContentType() == 1 || board.getGameMap()[player.getContentIndexes()[0]][player.getContentIndexes()[1]+i].getContentType() == 2 ) return false;
            }
            newIndexes[0] = player.getContentIndexes()[0];
            newIndexes[1] = player.getContentIndexes()[1]-numberOfCells;
        }
        else if( direction == SOUTH ) {
            if( player.getContentIndexes()[0]+numberOfCells > 10 ) return false;
            for( int i = 1; i < numberOfCells; i++ ) {
                if( board.getGameMap()[player.getContentIndexes()[0]+i][player.getContentIndexes()[1]].getContentType() == 1 || board.getGameMap()[player.getContentIndexes()[0]+i][player.getContentIndexes()[1]].getContentType() == 2 ) return false;
            }
            newIndexes[0] = player.getContentIndexes()[0]+numberOfCells;
            newIndexes[1] = player.getContentIndexes()[1];
        }
        else if( direction == EAST ) {
            if( player.getContentIndexes()[1]+numberOfCells > 10 ) return false;
            for( int i = 1; i < numberOfCells; i++ ){
                if( board.getGameMap()[player.getContentIndexes()[0]][player.getContentIndexes()[1]-i].getContentType() == 1 || board.getGameMap()[player.getContentIndexes()[0]][player.getContentIndexes()[1]-i].getContentType() == 2 ) return false;
            }
            newIndexes[0] = player.getContentIndexes()[0];
            newIndexes[1] = player.getContentIndexes()[1]+numberOfCells;
        }
        else if( direction == NORTH ){
            if( player.getContentIndexes()[0]-numberOfCells < 0 ) return false;
            for( int i = 1; i < numberOfCells; i++ ){
                if( board.getGameMap()[player.getContentIndexes()[0]-i][player.getContentIndexes()[1]].getContentType() == 1 || board.getGameMap()[player.getContentIndexes()[0]-i][player.getContentIndexes()[1]].getContentType() == 2 ) return false;
            }
            newIndexes[0] = player.getContentIndexes()[0]-numberOfCells;
            newIndexes[1] = player.getContentIndexes()[1];
        }
        if( board.getGameMap()[newIndexes[0]][newIndexes[1]].getContentType() == 2 ) return false;
        // make the move
        scores[player.getPlayerNumber()-1] += board.getGameMap()[newIndexes[0]][newIndexes[1]].getPoint();
        player.addPickedObject(board.getGameMap()[newIndexes[0]][newIndexes[1]]);
        board.getGameMap()[newIndexes[0]][newIndexes[1]] = board.getGameMap()[oldIndexes[0]][oldIndexes[1]];
        board.getGameMap()[oldIndexes[0]][oldIndexes[1]] = new UnoccupiedCell(oldIndexes);
        System.out.println("moved");
        return true;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Map getBoard() {
        return board;
    }

    public int[] getScores() {
        return scores;
    }
    
    public boolean isGameFinished() {
        int pNum = 0;
        int poNum = 0;
        for( int i = 0; i < 11; i++ ) {
            for( int j = 0; j < 11; j++ ) {
                if( board.getGameMap()[i][j].getContentType() == 1 ) pNum++;
                else if( board.getGameMap()[i][j].getContentType() == 3 ) poNum++;
            }
        }
        return pNum == 1 || poNum == 0;
    }
}
