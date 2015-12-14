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

        Boolean orResult_11 = false;

        if (Utils.equals(mingmangGame.currentPlayer, playerY)) {
            orResult_11 = true;
        } else {
            orResult_11 = Utils.equals(mingmangGame.currentPlayer, playerX);
        }

        assertTrue(orResult_11);
    }

    private void testMingMangChangeCurrentPlayer() {
        Player playerX = mingmangGame.playerX;
        Player playerY = mingmangGame.playerY;
        Player currentPlayer = mingmangGame.currentPlayer;
        Player initialPlayer = currentPlayer;
        Boolean orResult_12 = false;

        if (Utils.equals(currentPlayer, playerY)) {
            orResult_12 = true;
        } else {
            orResult_12 = Utils.equals(currentPlayer, playerX);
        }

        assertTrue(orResult_12);

        currentPlayer = mingmangGame.changeCurrentPlayer();

        Boolean andResult_28 = false;

        Boolean orResult_13 = false;

        if (Utils.equals(currentPlayer, playerY)) {
            orResult_13 = true;
        } else {
            orResult_13 = Utils.equals(currentPlayer, playerX);
        }

        if (orResult_13) {
            if (!(Utils.equals(currentPlayer, initialPlayer))) {
                andResult_28 = true;
            }
        }

        assertTrue(andResult_28);
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

        if (mingmangGame.movePiece(mingmangGame.board.getPieceAt(fromC),
                    mingmangGame.board.getPieceAt(toC))) {
            currentPlayer = mingmangGame.changeCurrentPlayer();
            pieceFrom = mingmangGame.board.getPieceAt(fromC).pieceType;
            pieceTo = mingmangGame.board.getPieceAt(toC).pieceType;
            assertTrue(Utils.equals(pieceTo, initialPlayer.pieceType));
            assertTrue(!(Utils.equals(mingmangGame.currentPlayer, initialPlayer)));
            assertTrue(Utils.equals(pieceFrom, Piece.UndefinedType));
        }
    }

    private void testMingMangCapturePiece() {
        Coordinates fromC = new Coordinates(8L, 7L);
        Coordinates toC = new Coordinates(2L, 7L);
        Object pieceFrom = null;
        Object pieceTo = null;
        Player currentPlayer = mingmangGame.currentPlayer;
        Player initialPlayer = currentPlayer;

        if (mingmangGame.movePiece(mingmangGame.board.getPieceAt(fromC),
                    mingmangGame.board.getPieceAt(toC))) {
            currentPlayer = mingmangGame.changeCurrentPlayer();
            pieceFrom = mingmangGame.board.getPieceAt(fromC).pieceType;
            pieceTo = mingmangGame.board.getPieceAt(toC).pieceType;
            assertTrue(!(Utils.equals(currentPlayer, initialPlayer)));
            assertTrue(Utils.equals(pieceTo, currentPlayer.pieceType));
            assertTrue(Utils.equals(pieceFrom, Piece.UndefinedType));
        }
    }

    private void testReturnToPreviousState() {
        MingMang newGame = new MingMang(8L);
        Number boardHistorySize = 0L;
        Player player = null;
        newGame.move(new Coordinates(1L, 3L), new Coordinates(3L, 3L));
        newGame.move(new Coordinates(8L, 7L), new Coordinates(5L, 7L));
        newGame.move(new Coordinates(3L, 3L), new Coordinates(1L, 3L));
        boardHistorySize = newGame.boards_history.size();
        player = newGame.currentPlayer;
        newGame.move(new Coordinates(5L, 7L), new Coordinates(8L, 7L));
        assertTrue(Utils.equals(boardHistorySize, newGame.boards_history.size()));
        assertTrue(Utils.equals(player, newGame.currentPlayer));
    }

    private void testMingMangEndGame() {
        Board boardEnd = new Board(boardSize, mingmangGame.playerX,
                mingmangGame.playerX);
        mingmangGame.board = boardEnd;
        mingmangGame.playerX.totalPieces = mingmangGame.board.numPieces(((Object) mingmangGame.playerX.pieceType));
        mingmangGame.playerY.totalPieces = mingmangGame.board.numPieces(((Object) mingmangGame.playerY.pieceType));
        assertTrue(Utils.equals(mingmangGame.isGameFinished(), true));
    }

    public static void main() {
        MingMangTest test = new MingMangTest();
        test.testMingMangBoardCreation();
        test.testMingMangPlayersCreation();
        test.testMingMangChangeCurrentPlayer();
        test.testMingMangMove();
        test.testMingMangCapturePiece();
        test.testMingMangEndGame();
        test.testReturnToPreviousState();
    }

    public String toString() {
        return "MingMangTest{" + "boardSize := " + Utils.toString(boardSize) +
        ", mingmangGame := " + Utils.toString(mingmangGame) + "}";
    }
}
