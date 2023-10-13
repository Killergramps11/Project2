public class Knight {
    private int row;
    private int col;
    private boolean isBlack;


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

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifyVertical(this.row, this.col, endRow, endCol) || board.verifyHorizontal(this.row, this.col, endRow, endCol)) {
            int rowDiff = endRow - this.row;
            int colDiff = endCol - this.col;

            // A knight moves in an L-shape: two squares in one direction and one square in a perpendicular direction.
            if (((rowDiff == 1 || rowDiff == -1) && (colDiff == 2 || colDiff == -2)) ||
                    ((rowDiff == 2 || rowDiff == -2) && (colDiff == 1 || colDiff == -1))) {
                return true;
            }
        }
        return false;
    }
}

