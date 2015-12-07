package MingMang;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class MingMang {
    public Board board;
    public Player playerX;
    public Player playerY;
    public Player currentPlayer;

    public MingMang(final Number board_size) {
        cg_init_MingMang_1(board_size);
    }

    public MingMang() {
    }

    public void cg_init_MingMang_1(final Number board_size) {
        Number playerPieces = (board_size.longValue() - 1L) * 2L;
        playerX = new Player(playerPieces, Piece.WhiteType);
        playerY = new Player(playerPieces, Piece.BlackType);
        currentPlayer = playerX;
        board = new Board(board_size, playerX, playerY);

        return;
    }

    public Player changeCurrentPlayer() {
        currentPlayer = nextPlayer();

        return currentPlayer;
    }

    public Player nextPlayer() {
        if (Utils.equals(currentPlayer, playerX)) {
            return playerY;
        } else {
            return playerX;
        }
    }

    public Object getOtherPlayerPieceType(final Object pieceType) {
        if (Utils.equals(pieceType, Piece.WhiteType)) {
            return Piece.BlackType;
        }

        return Piece.WhiteType;
    }

    public Position emptyPosition(final Coordinates coord) {
        return new Position(coord, Piece.UndefinedType);
    }

    public Boolean checkHorizontalCapture(final Position pos) {
        Position rightPos = board.getPieceAt(new Coordinates(pos.coord.x.longValue() +
                    1L, pos.coord.y));
        Position leftPos = board.getPieceAt(new Coordinates(pos.coord.x.longValue() -
                    1L, pos.coord.y));
        Boolean andResult_7 = false;

        if (!(Utils.equals(rightPos.pieceType, Piece.UndefinedType))) {
            Boolean andResult_8 = false;

            if (!(Utils.equals(leftPos.pieceType, Piece.UndefinedType))) {
                Boolean andResult_9 = false;

                if (!(Utils.equals(rightPos.pieceType, pos.pieceType))) {
                    if (!(Utils.equals(leftPos.pieceType, pos.pieceType))) {
                        andResult_9 = true;
                    }
                }

                if (andResult_9) {
                    andResult_8 = true;
                }
            }

            if (andResult_8) {
                andResult_7 = true;
            }
        }

        return andResult_7;
    }

    public Boolean checkVerticalCapture(final Position pos) {
        Position rightPos = board.getPieceAt(new Coordinates(pos.coord.x,
                    pos.coord.y.longValue() + 1L));
        Position leftPos = board.getPieceAt(new Coordinates(pos.coord.x,
                    pos.coord.y.longValue() - 1L));
        Boolean andResult_11 = false;

        if (!(Utils.equals(rightPos.pieceType, Piece.UndefinedType))) {
            Boolean andResult_12 = false;

            if (!(Utils.equals(leftPos.pieceType, Piece.UndefinedType))) {
                Boolean andResult_13 = false;

                if (!(Utils.equals(rightPos.pieceType, pos.pieceType))) {
                    if (!(Utils.equals(leftPos.pieceType, pos.pieceType))) {
                        andResult_13 = true;
                    }
                }

                if (andResult_13) {
                    andResult_12 = true;
                }
            }

            if (andResult_12) {
                andResult_11 = true;
            }
        }

        return andResult_11;
    }

    public Boolean canCapture(final Position position) {
        Boolean orResult_3 = false;

        if (checkHorizontalCapture(position)) {
            orResult_3 = true;
        } else {
            orResult_3 = checkVerticalCapture(position);
        }

        return orResult_3;
    }

    public void capture(final Position toEat, final Object playerWillEat) {
        board.removePosition(toEat);
        toEat.setPieceType(playerWillEat);
        board.insertPosition(toEat);

        return;
    }

    public void movePiece(final Position fromPosition, final Position toPosition) {
        board.removePosition(toPosition);
        board.removePosition(fromPosition);
        board.insertPosition(new Position(toPosition.coord,
                ((Object) currentPlayer.getPieceType())));
        board.insertPosition(emptyPosition(fromPosition.coord));

        for (Iterator iterator_3 = board.board.iterator();
                iterator_3.hasNext();) {
            Position pos = (Position) iterator_3.next();
            Boolean andResult_21 = false;

            if (pos.coord.x.longValue() > 1L) {
                Boolean andResult_22 = false;

                if (pos.coord.x.longValue() < 8L) {
                    Boolean andResult_23 = false;

                    if (pos.coord.y.longValue() > 1L) {
                        Boolean andResult_24 = false;

                        if (pos.coord.y.longValue() < 8L) {
                            if (!(Utils.equals(pos.pieceType,
                                        Piece.UndefinedType))) {
                                andResult_24 = true;
                            }
                        }

                        if (andResult_24) {
                            andResult_23 = true;
                        }
                    }

                    if (andResult_23) {
                        andResult_22 = true;
                    }
                }

                if (andResult_22) {
                    andResult_21 = true;
                }
            }

            if (andResult_21) {
                if (canCapture(pos)) {
                    capture(pos,
                        ((Object) getOtherPlayerPieceType(
                            ((Object) pos.pieceType))));
                }
            }
        }

        return;
    }

    public String toString() {
        return "MingMang{" + "board := " + Utils.toString(board) +
        ", playerX := " + Utils.toString(playerX) + ", playerY := " +
        Utils.toString(playerY) + ", currentPlayer := " +
        Utils.toString(currentPlayer) + "}";
    }
}
