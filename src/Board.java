import java.util.Arrays;

public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:(done)
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[7][7];
    }

    // Accessor Methods
    //TODO:(done)
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:(done)
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods
    //TODO:(done)
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move
    // is legal, and to execute the move if it is. Your Game class should not
    // directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece pieceToMove = getPiece(startRow, startCol);
        if (!pieceToMove.isMoveLegal(pieceToMove, endRow, endCol)) {
            return false;
        }
        board[endRow][endCol] = pieceToMove;
        board[startRow][startCol] = null;
        return true;
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    //Save for later
    public boolean isGameOver() {
        boolean whiteKingFound = false;
        boolean blackKingFound = false;
        return false;
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[col].length; col++) {
                board[row][col] = null;
            }
        }
    }

    // Movement helper functions
    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow < 0 || startRow >= board.length || startCol < 0 || startCol >= board[startRow].length) {
            return false;
        }
        if (endRow < 0 || endRow >= board.length || endCol < 0 || endCol >= board[endRow].length) {
            return false;
        }
        // Ensure 'start' contains a Piece
        if (board[startRow][startCol] == null) {
            return false;
        }
        // Check the color of the 'start' Piece
        if (board[startRow][startCol].getIsBlack() != isBlack) {
            return false;
        }
        // Ensure 'end' is either empty or contains a Piece of the opposite color
        if (board[endRow][endCol] != null && board[endRow][endCol].getIsBlack() == isBlack) {
            return false;
        }
        return true;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = endRow - startRow;
        int colDiff = endCol - startCol;
        if (rowDiff >= -1 && rowDiff <= 1 && colDiff >= -1 && colDiff <= 1) {
            if (rowDiff != 0 || colDiff != 0) {
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow) {
            return false;
        }
        if (startCol > endCol) {
            int temp = startCol;
            startCol = endCol;
            endCol = temp;
        }
        for (int i = startCol + 1; i < endCol; i++) {
            if (board[startRow][i] != null) {
                return false;
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) {
            return false;
        }

        if (startRow > endRow) {
            int temp = startRow;
            startRow = endRow;
            endRow = temp;
        }
        for (int i = startRow + 1; i < endRow; i++) {
            if (board[startCol][i] != null) {
                return false;
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = endRow - startRow;
        int colDiff = endCol - startCol;
        int rowDirection;
        int colDirection;

        if (rowDiff == 0 || colDiff == 0) {
            return false;
        }
        if (rowDiff > 0 && colDiff > 0 && rowDiff != colDiff) {
            return false;
        }
        if (rowDiff < 0 && colDiff < 0 && rowDiff != colDiff) {
            return false;
        }
        if (rowDiff > 0 && colDiff < 0 && rowDiff != -colDiff) {
            return false;
        }
        if (rowDiff < 0 && colDiff > 0 && colDiff != -rowDiff) {
            return false;
        }
        if (rowDiff > 0) {
            rowDirection = 1; // Down
        } else {
            rowDirection = -1; // Up
        }
        if (colDiff > 0) {
            colDirection = 1; // Right
        } else {
            colDirection = -1; // Left
        }
        // Start one step away from start position
        startRow += rowDirection;
        startCol += colDirection;
        // Check intermediate cells for empty spaces
        while (startRow != endRow && startCol != endCol) {
            if (board[startRow][startCol] != null) {
                return false;
            }
            startRow += rowDirection;
            startCol += colDirection;
        }
        return true;
    }
}
