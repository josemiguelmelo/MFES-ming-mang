import org.overture.codegen.runtime.*;

import java.io.*;
import java.util.*;


@SuppressWarnings("all")
public class MingMang {
    public Board board;
    public Player playerX;
    public Player playerY;
    public Player currentPlayer;


    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)
    {
    	MingMang m = new MingMang(8);
    	m.play();
    }
    
    
private Coordinates readFromCoordinates()
{
    System.out.print("From X Coord: ");
    int fromY = 0 , fromX = 0;

    try{
        fromX = Integer.parseInt(br.readLine());
    }catch(Exception nfe){
        System.err.println("Invalid Format!");
    }
    System.out.print("From Y Coord: ");
    try{
        fromY = Integer.parseInt(br.readLine());
    }catch(Exception nfe){
        System.err.println("Invalid Format!");
    }

    return new Coordinates(fromX, fromY);
}

private Coordinates readToCoordinates()
{
    System.out.print("To X Coord: ");
    int toX = 0, toY = 0;
    try{
        toX = Integer.parseInt(br.readLine());
    }catch(Exception nfe){
        System.err.println("Invalid Format!");
    }

    System.out.print("To Y Coord: ");
    try{
        toY = Integer.parseInt(br.readLine());
    }catch(Exception nfe){
        System.err.println("Invalid Format!");
    }

    return new Coordinates(toX, toY);
}

public void play() {
	System.out.println("PLAY");
    while(!isGameFinished())
    {
        printBoard();
        Coordinates fromCoordinates = readFromCoordinates();
        Coordinates toCoordinates = readToCoordinates();
        move(fromCoordinates, toCoordinates);
    }
}

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

    public void printBoard() {
        IO.print("\n");

        long toVar_6 = board.boardSize.longValue();

        for (Long counter1 = 1L; counter1 <= toVar_6; counter1++) {
            long toVar_5 = board.boardSize.longValue();

            for (Long counter2 = 1L; counter2 <= toVar_5; counter2++) {
                IO.print(((Object) board.getPieceTypeAtPosition(
                        new Coordinates(counter2, counter1))));
            }

            IO.print("\n");
        }

        IO.print("\nPlayer ");
        IO.print(((Object) currentPlayer.pieceType));
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

    public Boolean hasPossibleMoves(final Position p) {
        Position upPos = null;
        Position downPos = null;
        Position leftPos = null;
        Position rightPos = null;
        Coordinates upC = p.coord;
        Coordinates downC = p.coord;
        Coordinates leftC = p.coord;
        Coordinates rightC = p.coord;
        upC.y = upC.y.longValue() - 1L;
        downC.y = upC.y.longValue() + 1L;
        leftC.x = upC.x.longValue() - 1L;
        rightC.x = upC.x.longValue() + 1L;
        upPos = board.getPieceAt(upC);
        downPos = board.getPieceAt(downC);
        leftPos = board.getPieceAt(leftC);
        rightPos = board.getPieceAt(rightC);

        Boolean orResult_4 = false;

        if (!(Utils.equals(upPos.pieceType, Piece.UndefinedType))) {
            orResult_4 = true;
        } else {
            Boolean orResult_5 = false;

            if (!(Utils.equals(downPos.pieceType, Piece.UndefinedType))) {
                orResult_5 = true;
            } else {
                Boolean orResult_6 = false;

                if (!(Utils.equals(leftPos.pieceType, Piece.UndefinedType))) {
                    orResult_6 = true;
                } else {
                    orResult_6 = !(Utils.equals(rightPos.pieceType,
                            Piece.UndefinedType));
                }

                orResult_5 = orResult_6;
            }

            orResult_4 = orResult_5;
        }

        if (orResult_4) {
            return true;
        }

        return false;
    }

    public Boolean isGameFinished() {
        Boolean orResult_7 = false;

        if (Utils.equals(playerX.totalPieces, 0L)) {
            orResult_7 = true;
        } else {
            orResult_7 = Utils.equals(playerY.totalPieces, 0L);
        }

        if (orResult_7) {
            return true;
        }

        long toVar_8 = board.boardSize.longValue();

        for (Long counter1 = 1L; counter1 <= toVar_8; counter1++) {
            long toVar_7 = board.boardSize.longValue();

            for (Long counter2 = 1L; counter2 <= toVar_7; counter2++) {
                if (Utils.equals(board.getPieceTypeAtPosition(
                                new Coordinates(counter2, counter1)),
                            nextPlayer().pieceType)) {
                    if (hasPossibleMoves(board.getPieceAt(
                                    new Coordinates(counter2, counter1)))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void capture(final Position toEat, final Object playerWillEat) {
        board.removePosition(toEat);
        toEat.setPieceType(playerWillEat);
        board.insertPosition(toEat);

        if (Utils.equals(currentPlayer, playerX)) {
            playerX.totalPieces = playerX.totalPieces.longValue() + 1L;
            playerY.totalPieces = playerY.totalPieces.longValue() - 1L;
        } else {
            playerX.totalPieces = playerX.totalPieces.longValue() - 1L;
            playerY.totalPieces = playerY.totalPieces.longValue() + 1L;
        }

        return;
    }

    public void move(final Coordinates fromC, final Coordinates toC) {
        Player player = null;
        movePiece(board.getPieceAt(fromC), board.getPieceAt(toC));
        player = changeCurrentPlayer();
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
