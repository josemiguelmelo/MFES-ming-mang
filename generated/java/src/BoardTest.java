import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class BoardTest extends MyTestCase {
    private Number boardSize = 8L;

    public BoardTest() {
    }

    private void testCompare() {
        Board board1 = new Board(boardSize);
        Board board2 = new Board(boardSize);
        Board board3 = new Board(boardSize, new Player(8L, Piece.WhiteType),
                new Player(8L, Piece.WhiteType));
        super.assertTrue(board1.compare(board2));
        super.assertTrue(Utils.equals(board1.compare(board3), false));
    }

    private void testPieceAt() {
        Board board1 = new Board(boardSize);
        super.assertEqual(((Object) board1.getPieceTypeAtPosition(
                new Coordinates(1L, 1L))), Piece.WhiteType);
        super.assertEqual(((Object) board1.getPieceTypeAtPosition(
                new Coordinates(8L, 8L))), Piece.BlackType);
    }

    private void testPositionExists() {
        Board board1 = new Board(boardSize);
        super.assertEqual(board1.posInBoard(
                new Position(new Coordinates(1L, 1L), Piece.BlackType)), false);
        super.assertEqual(board1.posInBoard(board1.getPieceAt(
                    new Coordinates(1L, 1L))), true);
    }

    private void testNumPieces() {
        Board board1 = new Board(boardSize);
        super.assertEqual(board1.numPieces(Piece.WhiteType),
            (boardSize.longValue() - 1L) * 2L);
        super.assertEqual(board1.numPieces(Piece.BlackType),
            (boardSize.longValue() - 1L) * 2L);
    }

    public void runTests() {
        testCompare();
        testPieceAt();
        testPositionExists();
        testNumPieces();
    }

    public String toString() {
        return "BoardTest{" + "boardSize := " + Utils.toString(boardSize) +
        "}";
    }
}
