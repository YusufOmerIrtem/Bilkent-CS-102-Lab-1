import java.util.Random;

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
        shuffledIndexes = new int[players.length];
        for( int i = 0; i < shuffledIndexes.length; i++ ) {
            shuffledIndexes[i] = i;
        }
        for( int i = 0; i < 100; i++ ) {
            int num1 = random.nextInt(shuffledIndexes.length);
            int num2 = random.nextInt(shuffledIndexes.length);
            int temp = shuffledIndexes[num1];
            shuffledIndexes[num1] = shuffledIndexes[num2];
            shuffledIndexes[num2] = temp;
        }
    }

    // variables
    private Player[] players;
    private Map board;
    private int[] scores;
    private Random random = new Random();
    private int[] shuffledIndexes;
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
                if( board.getGameMap()[player.getContentIndexes()[0]][player.getContentIndexes()[1]-i].getContentType() == 1 || board.getGameMap()[player.getContentIndexes()[0]][player.getContentIndexes()[1]-i].getContentType() == 2 ) return false;
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
                if( board.getGameMap()[player.getContentIndexes()[0]][player.getContentIndexes()[1]+i].getContentType() == 1 || board.getGameMap()[player.getContentIndexes()[0]][player.getContentIndexes()[1]+i].getContentType() == 2 ) return false;
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
        if( board.getGameMap()[newIndexes[0]][newIndexes[1]].getPoint() == 1 ) {
            System.out.println(player.toString() + " eated 1 pointed object");
        }
        if( board.getGameMap()[newIndexes[0]][newIndexes[1]].getPoint() == 2 ) {
            System.out.println(player.toString() + " eated 2 pointed object");
        }
        if( board.getGameMap()[newIndexes[0]][newIndexes[1]].getPoint() == 5 ) {
            System.out.println(player.toString() + " eated " + board.getGameMap()[newIndexes[0]][newIndexes[1]].toString() );
        }
        player.addPickedObject(board.getGameMap()[newIndexes[0]][newIndexes[1]]);
        board.getGameMap()[newIndexes[0]][newIndexes[1]] = board.getGameMap()[oldIndexes[0]][oldIndexes[1]];
        player.setContentIndexes(newIndexes);
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
    public int[] getShuffledIndexes() {
        return shuffledIndexes;
    }

    // check whether the game is finished or not
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
    // print the scores
    public void displayScores() {
        for( int i = 0; i < scores.length; i++ ) {
            System.out.println(players[i] + ": " + scores[i] );
        }
    }
    // print the winner
    public void displayWinners() {
        int winnerScore = 0;
        for( int i = 0; i < players.length; i++ ) {
            if( players[i].getPoint() > winnerScore ) {
                winnerScore = players[i].getPoint();
            }
        }
        int winnerNum = 0;
        for( int i: scores ) {
            if( i == winnerScore ) {
                winnerNum++;
            }
        }
        if( winnerNum == 1 ) {
            Player p = players[0];
            for( int i = 0; i < players.length; i++ ) {
                if( players[i].getPoint() == winnerScore ) {
                    p = players[i];
                }
            }
            System.out.println("Winner is " + p.toString());
        }
        else {
            System.out.println("There is tie between:");
            for( Player p: players ) {
                if( p.getPoint() == winnerScore ) {
                    System.out.println( p.toString() );
                }
            }
        }
    }
}
