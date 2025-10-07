import java.util.Scanner;

public class ConsoleApp {
    // main method
    public static void main(String[] args) {
        System.out.println("Welcome to Console Driven Board Game");
        int selection;
        System.out.print("1) Two\n2) Three\n3) Four\nHow many players wil play: ");
        selection = in.nextInt();
        while( selection < 1 || selection > 3 ) {
            System.out.println("Invalid selection!");
            System.out.print("1) Two\n2) Three\n3) Four\nHow many players wil play: ");
            selection = in.nextInt();
            in.nextLine();
        }
        int playerNumber = selection+1;
        String[] playerNames = new String[playerNumber];
        for( int i = 1; i <= playerNumber; i++ ) {
            System.out.print("Player " + i + " name: ");
            playerNames[i-1] = in.next();
        }
        System.out.println("...Game is being constructed...");
        BoardGame game = new BoardGame(playerNames);
        System.out.println("Initial Board:");
        game.getBoard().display();
        int round = 1;
        int direction = 0;
        int numberOfCells = 0;
        while(!game.isGameFinished()) {
            System.out.println("Round " + round + " is starting...");
            for( int i = 0; i < playerNumber && !game.isGameFinished(); i++ ) {
                i = shuffled(game, i);
                System.out.println("1) West\n2) South\n3) East\n4) North");
                System.out.print("Player " + game.getPlayers()[i].getPlayerNumber() + " " + game.getPlayers()[i].getName() + "'s" + " direction: ");
                direction = in.nextInt();
                in.nextLine();
                while( direction < 1 || direction > 4 ){
                    System.out.println("Invalid selection!");
                    System.out.println("1) West\n2) South\n3) East\n4) North");
                    System.out.print("Player " + game.getPlayers()[i].getPlayerNumber() + " " + game.getPlayers()[i].getName() + "'s" + " direction: ");
                    direction = in.nextInt();
                    in.nextLine();
                }
                System.out.println("From 1 to 10");
                System.out.print("Player " + game.getPlayers()[i].getPlayerNumber() + " " + game.getPlayers()[i].getName() + "'s" + " number of cells: ");
                numberOfCells = in.nextInt();
                in.nextLine();
                while( numberOfCells < 1 || numberOfCells > 10 ){
                    System.out.println("Invalid selection!");
                    System.out.println("From 1 to 10");
                    System.out.print("Player " + game.getPlayers()[i].getPlayerNumber() + " " + game.getPlayers()[i].getName() + "'s" + " number of cells: ");
                    numberOfCells = in.nextInt();
                    in.nextLine();
                }
                while( !game.move(game.getPlayers()[i], direction, numberOfCells) ) {
                    System.out.println("Invalid selection!");
                    System.out.println("1) West\n2) South\n3) East\n4) North");
                    System.out.print("Player " + game.getPlayers()[i].getPlayerNumber() + " " + game.getPlayers()[i].getName() + "'s" + " direction: ");
                    direction = in.nextInt();
                    in.nextLine();
                    while( direction < 1 || direction > 4 ){
                        System.out.println("Invalid selection!");
                        System.out.println("1) West\n2) South\n3) East\n4) North");
                        System.out.print("Player " + game.getPlayers()[i].getPlayerNumber() + " " + game.getPlayers()[i].getName() + "'s" + " direction: ");
                        direction = in.nextInt();
                        in.nextLine();
                    }
                    System.out.println("From 1 to 10");
                    System.out.print("Player " + game.getPlayers()[i].getPlayerNumber() + " " + game.getPlayers()[i].getName() + "'s" + " number of cells: ");
                    numberOfCells = in.nextInt();
                    in.nextLine();
                    while( numberOfCells < 1 || numberOfCells > 10 ){
                        System.out.println("Invalid selection!");
                        System.out.println("From 1 to 10");
                        System.out.print("Player " + game.getPlayers()[i].getPlayerNumber() + " " + game.getPlayers()[i].getName() + "'s" + " number of cells: ");
                        numberOfCells = in.nextInt();
                        in.nextLine();
                    }
                }
                System.out.println("Updated board:");
                game.getBoard().display();
                System.out.println("Updated scores:");
                game.displayScores();
            }
        }
        System.out.println("Game is finished!");
        game.displayWinners();
    }

    // variables
    private static Scanner in = new Scanner(System.in);

    // methods
    private static int shuffled( BoardGame game, int i) {
        return game.getShuffledIndexes()[i];
    }
}
