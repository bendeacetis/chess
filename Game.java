// Written by Benjamin DeAcetis, deace003
import java.util.Scanner;

public class Game {
    public static void main (String[] args){
        //Scanner used to recieve users input
        Scanner scnr = new Scanner(System.in);
        Board myBoard = new Board();

        //Load the board with starting position
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);

        //Boolean variable tracks who's turn it is, starts as false because want white to go first
        boolean isWhite = false;

        //Game continues until a king is captured (gameOver== true)
        while((myBoard.isGameOver()!=true)){
            System.out.println("Board:");
            System.out.println(myBoard);

            //String to take in user inputs
            String move;

            //Case 1 :Black's Turn
            if(isWhite == true){
                //Set turn to black
                isWhite = false;

                System.out.println("It is currently black's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");
                move = scnr.nextLine();
                //creates an array of the inputs (from string to integers)
                String[] inputsTwo = move.split(" ");
                myBoard.movePiece(Integer.parseInt(inputsTwo[0]),Integer.parseInt(inputsTwo[1]),Integer.parseInt(inputsTwo[2]),Integer.parseInt(inputsTwo[3]));
            }
            //Case 2: White's Turn
            else if(isWhite == false){
                //Set turn to white
                isWhite = true;

                System.out.println("It is currently white's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");
                move = scnr.nextLine();
                //creates an array of the inputs (from string to integers)
                String[] inputs = move.split(" ");
                //moves the piece with the inputs
                myBoard.movePiece(Integer.parseInt(inputs[0]),Integer.parseInt(inputs[1]),Integer.parseInt(inputs[2]),Integer.parseInt(inputs[3]));
            }
        }
        scnr.close();

        //Print the final board (game has ended)
        System.out.println("Board:");
        System.out.println(myBoard);

        if(isWhite==true){
            System.out.println("White has won the game!");
        }
        else{
            System.out.println("Black has won the game!");
        }
    }
}
