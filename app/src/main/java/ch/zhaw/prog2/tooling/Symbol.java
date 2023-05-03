package ch.zhaw.prog2.tooling;

import java.util.EnumSet;
import java.util.Optional;

public enum Symbol {

    ZERO("0", "0"),
    ONE("00", "1"),
    BLANK("000", "_");

    private final String binaryCode;
    private final String value;

    Symbol(final String binaryCode, final String value) {
        this.binaryCode = binaryCode;
        this.value = value;
    }

    public static Optional<Symbol> getSymbolForBinaryCode(final String currentBinaryCode) {
        return EnumSet.allOf(Symbol.class).stream()
            .filter(symbol -> symbol.binaryCode.equals(currentBinaryCode))
            .findFirst();
    }

    public String getValue() {
        return value;
    }

}
