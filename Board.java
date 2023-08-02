// Written by Benjamin DeAcetis, deace003
public class Board {
    // Instance variables
    private Piece[][] board;

    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];
    }

    // Accessor Methods

    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
        //Updates piece position parameters and checks that not null (null pointer exception error)
        if(piece != null){
            piece.setPosition(row,col);
        }
    }

    // Game functionality methods

    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        //checks if there exists a piece to move, and move is legal
        if ((this.getPiece(startRow,startCol) != null)&&(getPiece(startRow,startCol).isMoveLegal(this, endRow,endCol))){
            //sets piece of final destination as moving piece.
            this.setPiece(endRow,endCol,getPiece(startRow,startCol));
            //sets original spot to empty (null)
            this.setPiece(startRow,startCol,null);
            return true;

        }
        return false;
    }





    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured. Index out of bounds error

    //TODO: consider using get character instead of making a new piece object.
    public boolean isGameOver() {
        Piece bKing = new Piece('\u265A', 0, 0, true);
        Piece wKing = new Piece('\u2654', 0, 0, false);

        //count represents number of kings on the board
        int count = 0;

        //iterate through the entire board.
        for (int i = 0; i<=7; i++){
            for (int j = 0; j<= 7; j++){
                //Checks if the piece is a king
                if((this.getPiece(i,j)!=null)&&((this.getPiece(i,j).equals(bKing))||(this.getPiece(i,j).equals(wKing)))){
                    //increment count if king is found.
                    count++;
                }
            }
        }
        //check if there aren't two kings, are return true (game over).
        if(count != 2){
            return true;
        }
        return false;
    }

    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        String output = "  0 1 2 3 4 5 6 7 "+ "\n";
        int row = 0;
        for(int i = 0; i<= 7; i++){
            output += row;
            for(int j= 0; j<= 7; j++){
                if(this.getPiece(i,j)!=null){
                    output += "|"+ this.getPiece(i,j);
                }
                else{
                    output += "|"+ '\u2001';
                }
            }
            row++;
            output += "|"+"\n";
        }
        return output;
    }

    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for(int i = 0; i<= 7; i++){
            for(int j = 0; j<=7; j++){
                this.setPiece(i,j,null);
            }
        }
    }

    // Movement helper functions

    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.

    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if((startRow <= 7)&&(startCol<=7)&&(endRow<=7)&&(endCol<=7)){
            if(this.getPiece(startRow,startCol)!= null){
                //check that right color for persons turn.
                if(isBlack == this.getPiece(startRow,startCol).getIsBlack()){
                    //check the second piece for empty, or the capture case.
                    if((this.getPiece(endRow,endCol)==null)||(this.getPiece(endRow,endCol).getIsBlack()!=isBlack)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        //Check row is equal or one away.
        if((endRow == startRow - 1)|| (endRow==startRow)||(endRow ==startRow+1)){
            //Check if col is equal or one away.
            if((endCol==startCol - 1)||(endCol==startCol)||(endCol==startCol+1)){
                return true;
            }
        }
        return false;
    }

    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if((startRow==endRow)){
            //Case 1:moving to left
            if(endCol<startCol){
                for(int i = startCol-1; i<endCol;i--){
                    if(this.getPiece(startRow,i)!=null){
                        return false;
                    }
                }
            }
            //Case 2: moving to right
            if(endCol>startCol){
                for(int i = startCol+1; i<endCol;i++){
                    if(this.getPiece(startRow,i)!=null){
                        return false;
                    }
                }
            }
            //return true if no pieces in way.
            return true;
        }
        return false;
    }

    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        //check col is the same
        if((startCol==endCol)){
            //Case 1: moving up
            if(endRow<startRow){
                for(int i = startRow-1; i>endRow;i--){
                    if(this.getPiece(i,startCol)!=null){
                        return false;
                    }
                }
            }
            //Case 2: moving down
            if(endRow>startRow){
                for(int i = startRow+1; i<endRow;i++){
                    if(this.getPiece(i,startCol)!=null){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        //checks if diagonal
        if((Math.abs(endRow-startRow))==(Math.abs(endCol-startCol))){
            //now check for null elements
            //all check if a element is null, and then returns false if so.
            //Case 1: Up, right
            if((endRow<startRow)&&(endCol>startCol)){
                int j = startCol+1;
                for(int i = startRow-1;i> endRow; i--){
                    if(this.getPiece(i,j)!=null){
                        return false;
                    }
                    j++;
                }
            }
            //Case 2: Up, left
            if((endRow<startRow)&&(endCol<startCol)){
                int j = startCol-1;
                for(int i = startRow-1;i> endRow; i--){
                    if(this.getPiece(i,j)!=null){
                        return false;
                    }
                    j--;
                }
            }
            //Case 3: Down, right
            if((endRow>startCol)&&(endCol>startCol)){
                int j = startCol+1;
                for(int i = startRow+1;i< endRow; i++){
                    if(this.getPiece(i,j)!=null){
                        return false;
                    }
                    j++;
                }
            }
            //Case 4: Down, left
            if((endRow>startCol)&&(endCol<startCol)){
                int j = startCol-1;
                for(int i = startRow+1;i< endRow; i++){
                    if(this.getPiece(i,j)!=null){
                        return false;
                    }
                    j--;
                }
            }
            //if didn't return false (no null elements) returns true.
            return true;
        }
        return false;
    }
}
