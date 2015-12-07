package MingMang.quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class BLACKQuote {
    private static int hc = 0;
    private static BLACKQuote instance = null;

    public BLACKQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static BLACKQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new BLACKQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof BLACKQuote;
    }

    public String toString() {
        return "<BLACK>";
    }
}
