import java.util.Random;

public class Map {
    // constructer
    public Map( Player[] players ) {
        // initialise map base
        gameMap = new GameContent[11][11];
        for( int i = 0; i < 11; i++ ) {
            for( int j = 0; j < 11; j++ ) {
                int[] ucIndexes = {i,j};
                gameMap[i][j] = new UnoccupiedCell(ucIndexes);
            }
        }

        // initialise players
        if( players.length == 2 ) {
            gameMap[0][0] = players[0];
            gameMap[0][10] = players[1];
        }
        else if(players.length == 3 ) {
            gameMap[0][0] = players[0];
            gameMap[0][10] = players[1];
            gameMap[10][0] = players[2];
        }
        else {
            gameMap[0][0] = players[0];
            gameMap[0][10] = players[1];
            gameMap[10][0] = players[2];
            gameMap[10][10] = players[3];
        }

        // initialise unpassable blocks
        int ubIndex = 0;
        int[] ubIndexes = new int[2];
            // the ones between players
        if( players.length == 2 ) {
            ubIndex = contentNumber.nextInt(7) + 2;
            ubIndexes[0] = 0;
            ubIndexes[1] = ubIndex;
            gameMap[0][ubIndex] = new UnpassableBlock(ubIndexes);
        }
        else if(players.length == 3 ) {
            ubIndex = contentNumber.nextInt(7) + 2;
            ubIndexes[0] = 0;
            ubIndexes[1] = ubIndex;
            gameMap[0][ubIndex] = new UnpassableBlock(ubIndexes);
            ubIndex = contentNumber.nextInt(7) + 2;
            ubIndexes[0] = ubIndex;
            ubIndexes[1] = 0;
            gameMap[ubIndex][0] = new UnpassableBlock(ubIndexes);
        }
        else {
            ubIndex = contentNumber.nextInt(7) + 2;
            ubIndexes[0] = 0;
            ubIndexes[1] = ubIndex;
            gameMap[0][ubIndex] = new UnpassableBlock(ubIndexes);
            ubIndex = contentNumber.nextInt(7) + 2;
            ubIndexes[0] = ubIndex;
            ubIndexes[1] = 0;
            gameMap[ubIndex][0] = new UnpassableBlock(ubIndexes);
            ubIndex = contentNumber.nextInt(7) + 2;
            ubIndexes[0] = 10;
            ubIndexes[1] = ubIndex;
            gameMap[10][ubIndex] = new UnpassableBlock(ubIndexes);
            ubIndex = contentNumber.nextInt(7) + 2;
            ubIndexes[0] = ubIndex;
            ubIndexes[1] = 10;
            gameMap[ubIndex][10] = new UnpassableBlock(ubIndexes);
        }
            // the ones placed randomly
        for( int i = 0; i < U_B_NUMBER; i++ ) {
            int[] indexes = new int[2];
            do {
                indexes[0] = contentNumber.nextInt(11);
                indexes[1] = contentNumber.nextInt(11);
            } while( !ubPlacer(indexes) );
        }

        // initialise pickable objects
        for( int i = 0; i < P_O_NUMBER; i++ ) {
            int[] indexes = new int[2];
            do {
                indexes[0] = contentNumber.nextInt(11);
                indexes[1] = contentNumber.nextInt(11);
            } while( !poPlacer(indexes) );
        }
    }

    // variables
    private GameContent[][] gameMap;
    private Random contentNumber = new Random();
    final int U_B_NUMBER = 5;
    final int P_O_NUMBER = 10;

    // methods
    private boolean ubPlacer( int[] indexes ) {
        if( gameMap[indexes[0]][indexes[1]].getContentType() == 1 || gameMap[indexes[0]][indexes[1]].getContentType() == 2 ) return false;
        if( indexes[0]-1 < 0 || gameMap[indexes[0]-1][indexes[1]].getContentType() == 1 || gameMap[indexes[0]-1][indexes[1]].getContentType() == 2 ) return false;
        if( indexes[0]+1 > 10 || gameMap[indexes[0]+1][indexes[1]].getContentType() == 1 || gameMap[indexes[0]+1][indexes[1]].getContentType() == 2 ) return false;
        if( indexes[1]-1 < 0 || gameMap[indexes[0]][indexes[1]-1].getContentType() == 1 || gameMap[indexes[0]][indexes[1]-1].getContentType() == 2 ) return false;
        if( indexes[1]+1 > 10 || gameMap[indexes[0]][indexes[1]+1].getContentType() == 1 || gameMap[indexes[0]][indexes[1]+1].getContentType() == 2 ) return false;
        gameMap[indexes[0]][indexes[1]] = new UnpassableBlock(indexes);
        return true;
    }
    private boolean poPlacer( int[] indexes ) {
        if( gameMap[indexes[0]][indexes[1]].getContentType() == 1 || gameMap[indexes[0]][indexes[1]].getContentType() == 2 || gameMap[indexes[0]][indexes[1]].getContentType() == 3 ) return false;
        if( indexes[0]-1 < 0 || gameMap[indexes[0]-1][indexes[1]].getContentType() == 1 ) return false;
        if( indexes[0]+1 > 10 || gameMap[indexes[0]+1][indexes[1]].getContentType() == 1 ) return false;
        if( indexes[1]-1 < 0 || gameMap[indexes[0]][indexes[1]-1].getContentType() == 1 ) return false;
        if( indexes[1]+1 > 10 || gameMap[indexes[0]][indexes[1]+1].getContentType() == 1 ) return false;
        if( gameMap[indexes[0]-1][indexes[1]].getContentType() != 0 ) return false;
        gameMap[indexes[0]][indexes[1]] = new PickableObject(indexes);
        return true;
    }
    public void display() {
        for( int i = 0; i < 11; i++ ) {
            for( int j = 0; j < 11; j++ ) {
                gameMap[i][j].display();
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public GameContent[][] getGameMap() {
        return gameMap;
    }
    public void setGameMap(GameContent[][] gameMap) {
        this.gameMap = gameMap;
    }
    
}
