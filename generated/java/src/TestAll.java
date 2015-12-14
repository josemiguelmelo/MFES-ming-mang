import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class TestAll extends MyTestCase {
    private Number boardSize = 8L;

    public TestAll() {
    }

    public static void main() {
        BoardTest board_test = new BoardTest();
        MingMangTest ming_mang_test = new MingMangTest();
        ming_mang_test.runTests();
        board_test.runTests();
    }

    public String toString() {
        return "TestAll{" + "boardSize := " + Utils.toString(boardSize) + "}";
    }
}
