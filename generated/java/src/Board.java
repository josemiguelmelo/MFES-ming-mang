import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Board {
    public static final Number MinBoardSize = 1L;
    public VDMSet board;
    public Number boardSize;
    public Number numPositions;

    public Board(final Number board_size) {
        cg_init_Board_1(board_size);
    }

    public Board(final Number board_size, final Player playerX,
        final Player playerY) {
        cg_init_Board_2(board_size, playerX, playerY);
    }

    public Board() {
    }

    public void cg_init_Board_1(final Number board_size) {
        Player playerX = new Player((board_size.longValue() - 1L) * 2L,
                Piece.WhiteType);
        Player playerY = new Player((board_size.longValue() - 1L) * 2L,
                Piece.BlackType);
        boardSize = board_size;
        board = SetUtil.set();
        numPositions = 0L;
        init(playerX, playerY);

        return;
    }

    public void cg_init_Board_2(final Number board_size, final Player playerX,
        final Player playerY) {
        boardSize = board_size;
        board = SetUtil.set();
        numPositions = 0L;
        init(playerX, playerY);

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

    private Boolean posInBoard(final Position pos) {
        for (Iterator iterator_3 = board.iterator(); iterator_3.hasNext();) {
            Position position = (Position) iterator_3.next();
            Boolean andResult_4 = false;

            if (Utils.equals(pos.coord.x, position.coord.x)) {
                Boolean andResult_5 = false;

                if (Utils.equals(pos.coord.y, position.coord.y)) {
                    if (Utils.equals(pos.pieceType, position.pieceType)) {
                        andResult_5 = true;
                    }
                }

                if (andResult_5) {
                    andResult_4 = true;
                }
            }

            if (andResult_4) {
                return true;
            }
        }

        return false;
    }

    public Boolean compare(final Board b) {
        for (Iterator iterator_4 = b.board.iterator(); iterator_4.hasNext();) {
            Position pos = (Position) iterator_4.next();

            if (Utils.equals(posInBoard(pos), false)) {
                return false;
            }
        }

        return true;
    }

    public Board clone() {
        Board b = new Board(boardSize);
        VDMSet board_new = SetUtil.set();

        for (Iterator iterator_5 = board.iterator(); iterator_5.hasNext();) {
            Position p = (Position) iterator_5.next();
            board_new = SetUtil.union(Utils.copy(board_new),
                    SetUtil.set(
                        new Position(new Coordinates(p.coord.x, p.coord.y),
                            ((Object) p.pieceType))));
        }

        b.board = Utils.copy(board_new);

        return b;
    }

    public String toString() {
        return "Board{" + "MinBoardSize = " + Utils.toString(MinBoardSize) +
        ", board := " + Utils.toString(board) + ", boardSize := " +
        Utils.toString(boardSize) + ", numPositions := " +
        Utils.toString(numPositions) + "}";
    }
}
