// Written by Benjamin DeAcetis, deace003
public class King {
    private int row;
    private int col;
    private boolean isBlack;

    /**
     * Constructor.
     * @param row   The current row of the king.
     * @param col   The current column of the king.
     * @param isBlack   The color of the king.
     */
    public King(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        //checks that move is remotely legal.
        if(board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            //Check that is valid move.
            if (board.verifyAdjacent(this.row, this.col, endRow, endCol)){
                //Case 1: Move
                if (board.getPiece(endRow, endCol) == null) {
                    return true;
                }
                //Case 2: Capture Cases (2)
                else{
                    //Capturing Case
                    if (board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
                        return true;
                    }
                    //End piece is same color as current player.
                    else {
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
