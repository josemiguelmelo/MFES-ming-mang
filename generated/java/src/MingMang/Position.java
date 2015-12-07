package MingMang;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Position {
    public Coordinates coord;
    public Object pieceType;

    public Position(final Coordinates coordinates, final Object piece) {
        cg_init_Position_1(coordinates, ((Object) piece));
    }

    public Position() {
    }

    public void cg_init_Position_1(final Coordinates coordinates,
        final Object piece) {
        coord = coordinates;
        pieceType = piece;

        return;
    }

    public Number getX() {
        return coord.x;
    }

    public Number getY() {
        return coord.y;
    }

    public void setX(final Number xPos) {
        coord.setX(xPos);
    }

    public void setY(final Number yPos) {
        coord.setY(yPos);
    }

    public void setPieceType(final Object piece) {
        pieceType = piece;
    }

    public Object getPieceType() {
        return pieceType;
    }

    public Boolean compareToPositionCoordinates(final Coordinates coordinates) {
        return coord.compare(coordinates);
    }

    public String toString() {
        return "Position{" + "coord := " + Utils.toString(coord) +
        ", pieceType := " + Utils.toString(pieceType) + "}";
    }
}
