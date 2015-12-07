import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Board {
    public VDMSet board;
    public Number boardSize;
    public Number numPositions;

    public Board(final Number board_size, final Player playerX,
        final Player playerY) {
        cg_init_Board_1(board_size, playerX, playerY);
    }

    public Board(final Number board_size, final Player playerX,
        final Player playerY, final Boolean allBlack) {
        cg_init_Board_2(board_size, playerX, playerY, allBlack);
    }

    public Board() {
    }

    public void cg_init_Board_1(final Number board_size, final Player playerX,
        final Player playerY) {
        boardSize = board_size;
        board = SetUtil.set();
        numPositions = 0L;
        init(playerX, playerY);

        return;
    }

    public void cg_init_Board_2(final Number board_size, final Player playerX,
        final Player playerY, final Boolean allBlack) {
        boardSize = board_size;
        board = SetUtil.set();
        numPositions = 0L;

        if (allBlack) {
            initAllPiecesEqual(playerY);
        } else {
            init(playerX, playerY);
        }

        return;
    }

    public void init(final Player playerX, final Player playerY) {
        long toVar_2 = boardSize.longValue();
        long byVar_2 = 1L;

        for (Long i = 1L; (byVar_2 < 0) ? (i >= toVar_2) : (i <= toVar_2);
                i += byVar_2) {
            Object pieceType = Piece.UndefinedType;
            Position position = null;
            long toVar_1 = boardSize.longValue();
            long byVar_1 = 1L;

            for (Long j = 1L; (byVar_1 < 0) ? (j >= toVar_1) : (j <= toVar_1);
                    j += byVar_1) {
                pieceType = Piece.UndefinedType;

                if (Utils.equals(i, 1L)) {
                    pieceType = playerY.getPieceType();
                }

                if (Utils.equals(i, boardSize)) {
                    pieceType = playerX.getPieceType();
                }

                if (Utils.equals(j, 1L)) {
                    pieceType = playerX.getPieceType();
                }

                if (Utils.equals(j, boardSize)) {
                    pieceType = playerY.getPieceType();
                }

                position = new Position(new Coordinates(j, i),
                        ((Object) pieceType));
                board = SetUtil.union(Utils.copy(board), SetUtil.set(position));
            }
        }

        numPositions = board.size();
    }

    public void initAllPiecesEqual(final Player playerY) {
        long toVar_4 = boardSize.longValue();
        long byVar_4 = 1L;

        for (Long i = 1L; (byVar_4 < 0) ? (i >= toVar_4) : (i <= toVar_4);
                i += byVar_4) {
            Object pieceType = Piece.UndefinedType;
            Position position = null;
            long toVar_3 = boardSize.longValue();
            long byVar_3 = 1L;

            for (Long j = 1L; (byVar_3 < 0) ? (j >= toVar_3) : (j <= toVar_3);
                    j += byVar_3) {
                pieceType = Piece.UndefinedType;

                if (Utils.equals(i, 1L)) {
                    pieceType = playerY.getPieceType();
                }

                if (Utils.equals(i, boardSize)) {
                    pieceType = playerY.getPieceType();
                }

                if (Utils.equals(j, 1L)) {
                    pieceType = playerY.getPieceType();
                }

                if (Utils.equals(j, boardSize)) {
                    pieceType = playerY.getPieceType();
                }

                position = new Position(new Coordinates(j, i),
                        ((Object) pieceType));
                board = SetUtil.union(Utils.copy(board), SetUtil.set(position));
            }
        }

        numPositions = board.size();
    }

    public Number getBoardSize() {
        return boardSize;
    }

    public Number getNumPositions() {
        return numPositions;
    }

    public Number numPieces(final Object pieceType) {
        Number counter = 0L;

        for (Iterator iterator_1 = board.iterator(); iterator_1.hasNext();) {
            Position position = (Position) iterator_1.next();

            if (Utils.equals(position.getPieceType(), pieceType)) {
                counter = counter.longValue() + 1L;
            }
        }

        return counter;
    }

    public void removePosition(final Position pos) {
        board = SetUtil.diff(Utils.copy(board), SetUtil.set(pos));
        numPositions = board.size();
    }

    public void insertPosition(final Position pos) {
        board = SetUtil.union(Utils.copy(board), SetUtil.set(pos));
        numPositions = board.size();
    }

    public Object getPieceTypeAtPosition(final Coordinates coord) {
        return getPieceAt(coord).pieceType;
    }

    public Position getPieceAt(final Coordinates coord) {
        Position pieceType = null;

        for (Iterator iterator_2 = board.iterator(); iterator_2.hasNext();) {
            Position position = (Position) iterator_2.next();

            if (position.compareToPositionCoordinates(coord)) {
                pieceType = position;
            }
        }

        return pieceType;
    }

    public String toString() {
        return "Board{" + "board := " + Utils.toString(board) +
        ", boardSize := " + Utils.toString(boardSize) + ", numPositions := " +
        Utils.toString(numPositions) + "}";
    }
}
