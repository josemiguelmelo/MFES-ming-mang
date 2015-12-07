package MingMang;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Piece {
    public static final MingMang.quotes.UNDEFINEDQuote UndefinedType = MingMang.quotes.UNDEFINEDQuote.getInstance();
    public static final MingMang.quotes.BLACKQuote BlackType = MingMang.quotes.BLACKQuote.getInstance();
    public static final MingMang.quotes.WHITEQuote WhiteType = MingMang.quotes.WHITEQuote.getInstance();
    public Position position;
    public Player player;
    public Object type;

    public Piece(final Player pl, final Position pos) {
        cg_init_Piece_1(pl, pos);
    }

    public Piece() {
    }

    public void cg_init_Piece_1(final Player pl, final Position pos) {
        player = pl;
        position = pos;
        type = player.pieceType;

        return;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(final Position pos) {
        position = pos;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(final Player pla) {
        player = pla;
    }

    public String toString() {
        return "Piece{" + "UndefinedType = " + Utils.toString(UndefinedType) +
        ", BlackType = " + Utils.toString(BlackType) + ", WhiteType = " +
        Utils.toString(WhiteType) + ", position := " +
        Utils.toString(position) + ", player := " + Utils.toString(player) +
        ", type := " + Utils.toString(type) + "}";
    }
}
