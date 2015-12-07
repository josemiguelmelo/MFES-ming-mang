package MingMang;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Player {
    public Object pieceType;
    public Number totalPieces;

    public Player(final Number numPieces, final Object piece) {
        cg_init_Player_1(numPieces, ((Object) piece));
    }

    public Player() {
    }

    public void cg_init_Player_1(final Number numPieces, final Object piece) {
        totalPieces = numPieces;
        pieceType = piece;

        return;
    }

    public Number getTotalPieces() {
        return totalPieces;
    }

    public Object getPieceType() {
        return pieceType;
    }

    public void decNumPieces() {
        totalPieces = totalPieces.longValue() - 1L;
    }

    public void incNumPieces() {
        totalPieces = totalPieces.longValue() + 1L;
    }

    public String toString() {
        return "Player{" + "pieceType := " + Utils.toString(pieceType) +
        ", totalPieces := " + Utils.toString(totalPieces) + "}";
    }
}
