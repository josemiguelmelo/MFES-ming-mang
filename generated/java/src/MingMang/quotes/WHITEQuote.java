package MingMang.quotes;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class WHITEQuote {
    private static int hc = 0;
    private static WHITEQuote instance = null;

    public WHITEQuote() {
        if (Utils.equals(hc, 0)) {
            hc = super.hashCode();
        }
    }

    public static WHITEQuote getInstance() {
        if (Utils.equals(instance, null)) {
            instance = new WHITEQuote();
        }

        return instance;
    }

    public int hashCode() {
        return hc;
    }

    public boolean equals(final Object obj) {
        return obj instanceof WHITEQuote;
    }

    public String toString() {
        return "<WHITE>";
    }
}
