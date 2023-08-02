// Written by Benjamin DeAcetis, deace003
public class Knight {
    private int row;
    private int col;
    private boolean isBlack;

    /**
     * Constructor.
     * @param row   The current row of the knight.
     * @param col   The current column of the knight.
     * @param isBlack   The color of the knight.
     */
    public Knight(int row, int col, boolean isBlack){
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
                //Moves up 2
                if(endRow == this.row - 2){
                    //left or right
                    if(endCol == this.col -1 || endCol == this.col + 1){
                        //Case 1: Move
                        if (board.getPiece(endRow, endCol) == null){
                            return true;
                        }
                        //Case 2: Capture
                        else if (board.getPiece(endRow, endCol) != null){
                            // diff color, can capture
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
                //Moves up 1
                if(endRow == this.row - 1){
                    //left or right
                    if(endCol == this.col -2 || endCol == this.col + 2){
                        //Case 1: Move
                        if (board.getPiece(endRow, endCol) == null){
                            return true;
                        }
                        //Case 2: Capture
                        else if (board.getPiece(endRow, endCol) != null){
                            // diff color, can capture
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
                //Moves down 1
                if(endRow == this.row + 1){
                    //left or right
                    if(endCol == this.col -2 || endCol == this.col + 2){
                        //Case 1: Move
                        if (board.getPiece(endRow, endCol) == null){
                            return true;
                        }
                        //Case 2: Capture
                        else if (board.getPiece(endRow, endCol) != null){
                            // diff color, can capture
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
                //Moves down 2
                if(endRow == this.row + 2){
                    //left or right
                    if(endCol == this.col -1 || endCol == this.col + 1){
                        //Case 1: Move
                        if (board.getPiece(endRow, endCol) == null){
                            return true;
                        }
                        //Case 2: Capture
                        else if (board.getPiece(endRow, endCol) != null){
                            //diff color, can capture
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
        }
        return false;
    }
}
