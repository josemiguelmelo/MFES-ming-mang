import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Coordinates {
    public Number x;
    public Number y;

    public Coordinates(final Number xPos, final Number yPos) {
        cg_init_Coordinates_1(xPos, yPos);
    }

    public Coordinates() {
    }

    public void cg_init_Coordinates_1(final Number xPos, final Number yPos) {
        x = xPos;
        y = yPos;

        return;
    }

    public Number getX() {
        return x;
    }

    public Number getY() {
        return y;
    }

    public void setX(final Number xPos) {
        x = xPos;
    }

    public void setY(final Number yPos) {
        y = yPos;
    }

    public Boolean compare(final Coordinates coord) {
        Boolean andResult_3 = false;

        if (Utils.equals(x, coord.getX())) {
            if (Utils.equals(y, coord.getY())) {
                andResult_3 = true;
            }
        }

        return andResult_3;
    }

    public String toString() {
        return "Coordinates{" + "x := " + Utils.toString(x) + ", y := " +
        Utils.toString(y) + "}";
    }
}
