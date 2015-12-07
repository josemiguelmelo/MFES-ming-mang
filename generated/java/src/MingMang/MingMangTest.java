package MingMang;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class MingMangTest {
    private Number boardSize = 8L;
    private MingMang mingmangGame = new MingMang(boardSize);

    public MingMangTest() {
    }

    private void assertTrue(final Boolean cond) {
        return;
    }

    private void testMingMangBoardCreation() {
        assertTrue(Utils.equals(mingmangGame.board.getBoardSize(), boardSize));
        assertTrue(Utils.equals(mingmangGame.board.getNumPositions(),
                boardSize.longValue() * boardSize.longValue()));
        assertTrue(Utils.equals(mingmangGame.board.numPieces(Piece.WhiteType),
                (boardSize.longValue() - 1L) * 2L));
        assertTrue(Utils.equals(mingmangGame.board.numPieces(Piece.BlackType),
                (boardSize.longValue() - 1L) * 2L));
        assertTrue(Utils.equals(mingmangGame.board.numPieces(
                    Piece.UndefinedType),
                (boardSize.longValue() * boardSize.longValue()) -
                (4L * (boardSize.longValue() - 1L))));
    }

    private void testMingMangPlayersCreation() {
        Player playerX = mingmangGame.playerX;
        Player playerY = mingmangGame.playerY;
        Board board = mingmangGame.board;
        assertTrue(Utils.equals(playerX.getTotalPieces(),
                (boardSize.longValue() - 1L) * 2L));
        assertTrue(Utils.equals(playerY.getTotalPieces(),
                (boardSize.longValue() - 1L) * 2L));
        assertTrue(!(Utils.equals(playerX.getPieceType(), playerY.getPieceType())));
        assertTrue(!(Utils.equals(playerX.getPieceType(), Piece.UndefinedType)));
        assertTrue(!(Utils.equals(playerY.getPieceType(), Piece.UndefinedType)));
        assertTrue(Utils.equals(board.numPieces(
                    ((Object) playerX.getPieceType())), playerX.getTotalPieces()));
        assertTrue(Utils.equals(board.numPieces(
                    ((Object) playerY.getPieceType())), playerY.getTotalPieces()));

        Boolean orResult_7 = false;

        if (Utils.equals(mingmangGame.currentPlayer, playerY)) {
            orResult_7 = true;
        } else {
            orResult_7 = Utils.equals(mingmangGame.currentPlayer, playerX);
        }

        assertTrue(orResult_7);
    }

    private void testMingMangChangeCurrentPlayer() {
        Player playerX = mingmangGame.playerX;
        Player playerY = mingmangGame.playerY;
        Player currentPlayer = mingmangGame.currentPlayer;
        Player initialPlayer = currentPlayer;
        Boolean orResult_8 = false;

        if (Utils.equals(currentPlayer, playerY)) {
            orResult_8 = true;
        } else {
            orResult_8 = Utils.equals(currentPlayer, playerX);
        }

        assertTrue(orResult_8);

        currentPlayer = mingmangGame.changeCurrentPlayer();

        Boolean andResult_25 = false;

        Boolean orResult_9 = false;

        if (Utils.equals(currentPlayer, playerY)) {
            orResult_9 = true;
        } else {
            orResult_9 = Utils.equals(currentPlayer, playerX);
        }

        if (orResult_9) {
            if (!(Utils.equals(currentPlayer, initialPlayer))) {
                andResult_25 = true;
            }
        }

        assertTrue(andResult_25);
    }

    private void testMingMangRemovePosition() {
        Coordinates c = new Coordinates(8L, 7L);
        mingmangGame.board.removePosition(mingmangGame.board.getPieceAt(c));
        assertTrue(Utils.equals(mingmangGame.board.board.size(), 63L));

        for (Iterator iterator_4 = mingmangGame.board.board.iterator();
                iterator_4.hasNext();) {
            Position pos = (Position) iterator_4.next();
            Coordinates c2 = pos.coord;
            assertTrue(Utils.equals(c.compare(c2), false));
        }
    }

    private void testMingMangMove() {
        Coordinates fromC = new Coordinates(1L, 6L);
        Coordinates toC = new Coordinates(2L, 6L);
        Object pieceFrom = null;
        Object pieceTo = null;
        Player currentPlayer = null;
        Player initialPlayer = null;
        mingmangGame = new MingMang(boardSize);
        currentPlayer = mingmangGame.currentPlayer;
        initialPlayer = mingmangGame.currentPlayer;
        mingmangGame.movePiece(mingmangGame.board.getPieceAt(fromC),
            mingmangGame.board.getPieceAt(toC));
        currentPlayer = mingmangGame.changeCurrentPlayer();
        pieceFrom = mingmangGame.board.getPieceAt(fromC).pieceType;
        pieceTo = mingmangGame.board.getPieceAt(toC).pieceType;
        assertTrue(Utils.equals(pieceTo, initialPlayer.pieceType));
        assertTrue(!(Utils.equals(mingmangGame.currentPlayer, initialPlayer)));
        assertTrue(Utils.equals(pieceFrom, Piece.UndefinedType));
    }

    private void testMingMangCapturePiece() {
        Coordinates fromC = new Coordinates(8L, 7L);
        Coordinates toC = new Coordinates(2L, 7L);
        Object pieceFrom = null;
        Object pieceTo = null;
        Player currentPlayer = mingmangGame.currentPlayer;
        Player initialPlayer = currentPlayer;
        mingmangGame.movePiece(mingmangGame.board.getPieceAt(fromC),
            mingmangGame.board.getPieceAt(toC));
        currentPlayer = mingmangGame.changeCurrentPlayer();
        pieceFrom = mingmangGame.board.getPieceAt(fromC).pieceType;
        pieceTo = mingmangGame.board.getPieceAt(toC).pieceType;
        assertTrue(!(Utils.equals(currentPlayer, initialPlayer)));
        assertTrue(Utils.equals(pieceTo, currentPlayer.pieceType));
        assertTrue(Utils.equals(pieceFrom, Piece.UndefinedType));
    }

    public static void main() {
        MingMangTest test = new MingMangTest();
        test.testMingMangBoardCreation();
        test.testMingMangPlayersCreation();
        test.testMingMangChangeCurrentPlayer();
        test.testMingMangRemovePosition();
        test.testMingMangMove();
        test.testMingMangCapturePiece();
    }

    public String toString() {
        return "MingMangTest{" + "boardSize := " + Utils.toString(boardSize) +
        ", mingmangGame := " + Utils.toString(mingmangGame) + "}";
    }
}
