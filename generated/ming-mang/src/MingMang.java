import org.overture.codegen.runtime.*;

import java.util.*;
import java.io.*;


@SuppressWarnings("all")
public class MingMang {
    public Board board;
    public Player playerX;
    public Player playerY;
    public Player currentPlayer;
    public VDMSeq boards_history;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public MingMang(final Number board_size) {
        cg_init_MingMang_1(board_size);
    }

    public MingMang() {
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

    public void cg_init_MingMang_1(final Number board_size) {
        Number playerPieces = (board_size.longValue() - 1L) * 2L;
        playerX = new Player(playerPieces, Piece.WhiteType);
        playerY = new Player(playerPieces, Piece.BlackType);
        currentPlayer = playerX;
        board = new Board(board_size, playerX, playerY);
        boards_history = SeqUtil.seq(board.clone());

        return;
    }

    public void printBoard() {
        IO.print("\n");

        long toVar_4 = board.boardSize.longValue();

        for (Long counter1 = 1L; counter1 <= toVar_4; counter1++) {
            long toVar_3 = board.boardSize.longValue();

            for (Long counter2 = 1L; counter2 <= toVar_3; counter2++) {
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
        Boolean andResult_10 = false;

        if (!(Utils.equals(rightPos.pieceType, Piece.UndefinedType))) {
            Boolean andResult_11 = false;

            if (!(Utils.equals(leftPos.pieceType, Piece.UndefinedType))) {
                Boolean andResult_12 = false;

                if (!(Utils.equals(rightPos.pieceType, pos.pieceType))) {
                    if (!(Utils.equals(leftPos.pieceType, pos.pieceType))) {
                        andResult_12 = true;
                    }
                }

                if (andResult_12) {
                    andResult_11 = true;
                }
            }

            if (andResult_11) {
                andResult_10 = true;
            }
        }

        return andResult_10;
    }

    public Boolean checkVerticalCapture(final Position pos) {
        Position rightPos = board.getPieceAt(new Coordinates(pos.coord.x,
                    pos.coord.y.longValue() + 1L));
        Position leftPos = board.getPieceAt(new Coordinates(pos.coord.x,
                    pos.coord.y.longValue() - 1L));
        Boolean andResult_14 = false;

        if (!(Utils.equals(rightPos.pieceType, Piece.UndefinedType))) {
            Boolean andResult_15 = false;

            if (!(Utils.equals(leftPos.pieceType, Piece.UndefinedType))) {
                Boolean andResult_16 = false;

                if (!(Utils.equals(rightPos.pieceType, pos.pieceType))) {
                    if (!(Utils.equals(leftPos.pieceType, pos.pieceType))) {
                        andResult_16 = true;
                    }
                }

                if (andResult_16) {
                    andResult_15 = true;
                }
            }

            if (andResult_15) {
                andResult_14 = true;
            }
        }

        return andResult_14;
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

        if (upC.y.longValue() < 1L) {
            upC = p.coord;
        }

        if (downC.y.longValue() > board.boardSize.longValue()) {
            downC = p.coord;
        }

        if (leftC.x.longValue() < 1L) {
            rightC = p.coord;
        }

        if (rightC.x.longValue() > board.boardSize.longValue()) {
            rightC = p.coord;
        }

        upPos = board.getPieceAt(upC);
        downPos = board.getPieceAt(downC);
        leftPos = board.getPieceAt(leftC);
        rightPos = board.getPieceAt(rightC);

        Boolean orResult_4 = false;

        if (Utils.equals(upPos.pieceType, Piece.UndefinedType)) {
            orResult_4 = true;
        } else {
            Boolean orResult_5 = false;

            if (Utils.equals(downPos.pieceType, Piece.UndefinedType)) {
                orResult_5 = true;
            } else {
                Boolean orResult_6 = false;

                if (Utils.equals(leftPos.pieceType, Piece.UndefinedType)) {
                    orResult_6 = true;
                } else {
                    orResult_6 = Utils.equals(rightPos.pieceType,
                            Piece.UndefinedType);
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

        long toVar_6 = board.boardSize.longValue();

        for (Long counter1 = 1L; counter1 <= toVar_6; counter1++) {
            long toVar_5 = board.boardSize.longValue();

            for (Long counter2 = 1L; counter2 <= toVar_5; counter2++) {
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
        toEat.setPieceType(playerWillEat);

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
        if (movePiece(board.getPieceAt(fromC), board.getPieceAt(toC))) {
            currentPlayer = changeCurrentPlayer();
        }
    }

    public Boolean movePiece(final Position fromPosition,
        final Position toPosition) {
        Object pieceTypeAux = fromPosition.pieceType;
        fromPosition.setPieceType(toPosition.pieceType);
        toPosition.setPieceType(pieceTypeAux);

        for (Iterator iterator_6 = board.board.iterator();
                iterator_6.hasNext();) {
            Position pos = (Position) iterator_6.next();
            Boolean andResult_24 = false;

            if (pos.coord.x.longValue() > 1L) {
                Boolean andResult_25 = false;

                if (pos.coord.x.longValue() < board.boardSize.longValue()) {
                    Boolean andResult_26 = false;

                    if (pos.coord.y.longValue() > 1L) {
                        Boolean andResult_27 = false;

                        if (pos.coord.y.longValue() < board.boardSize.longValue()) {
                            if (!(Utils.equals(pos.pieceType,
                                        Piece.UndefinedType))) {
                                andResult_27 = true;
                            }
                        }

                        if (andResult_27) {
                            andResult_26 = true;
                        }
                    }

                    if (andResult_26) {
                        andResult_25 = true;
                    }
                }

                if (andResult_25) {
                    andResult_24 = true;
                }
            }

            if (andResult_24) {
                if (canCapture(pos)) {
                    capture(pos,
                        ((Object) getOtherPlayerPieceType(
                            ((Object) pos.pieceType))));
                }
            }
        }

        for (Iterator iterator_7 = boards_history.iterator();
                iterator_7.hasNext();) {
            Board b = (Board) iterator_7.next();

            if (b.compare(board)) {
                pieceTypeAux = fromPosition.pieceType;
                fromPosition.setPieceType(toPosition.pieceType);
                toPosition.setPieceType(pieceTypeAux);

                return false;
            }
        }

        boards_history = SeqUtil.conc(Utils.copy(boards_history),
                SeqUtil.seq(board.clone()));

        return true;
    }

    public String toString() {
        return "MingMang{" + "board := " + Utils.toString(board) +
        ", playerX := " + Utils.toString(playerX) + ", playerY := " +
        Utils.toString(playerY) + ", currentPlayer := " +
        Utils.toString(currentPlayer) + ", boards_history := " +
        Utils.toString(boards_history) + "}";
    }
}
