public class Bishop {
    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;
    public Bishop(int row, int col, boolean isBlack) {
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
    public boolean isMoveLegal(Board board, int endRow, int endCol) {

        if (board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
            if (this.isBlack) {
                return (endRow == this.row + (endRow - row)) && (endCol == this.col + (endRow - col));
            } else {
                return (endRow == this.row - (endRow - row)) && (endCol == this.col - (endCol - col));
            }
        } else if (board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
            // I)f there's a piece on the destination square, and it's of the opposite color
            if (board.getPiece(endRow, endCol) != null &&
                    board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
                // Valid capture move
                return true;
            }
        }
        return false;
    }
}
