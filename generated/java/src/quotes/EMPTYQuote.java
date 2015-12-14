package quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class EMPTYQuote {
    private static int hc = 0;
    private static EMPTYQuote instance = null;

    public EMPTYQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static EMPTYQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new EMPTYQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof EMPTYQuote;
    }

    public String toString() {
        return "<EMPTY>";
    }
}
