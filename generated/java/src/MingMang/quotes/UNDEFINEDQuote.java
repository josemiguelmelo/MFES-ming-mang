package MingMang.quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class UNDEFINEDQuote {
    private static int hc = 0;
    private static UNDEFINEDQuote instance = null;

    public UNDEFINEDQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static UNDEFINEDQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new UNDEFINEDQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof UNDEFINEDQuote;
    }

    public String toString() {
        return "<UNDEFINED>";
    }
}
