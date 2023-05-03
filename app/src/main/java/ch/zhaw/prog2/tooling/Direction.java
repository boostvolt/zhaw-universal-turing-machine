package ch.zhaw.prog2.tooling;

import java.util.EnumSet;
import java.util.Optional;

public enum Direction {

    LEFT("0"),
    RIGHT("00");

    private final String binaryCode;

    Direction(final String binaryCode) {
        this.binaryCode = binaryCode;
    }

    public static Optional<Direction> getDirectionForBinaryCode(final String currentBinaryCode) {
        return EnumSet.allOf(Direction.class).stream()
            .filter(direction -> direction.binaryCode.equals(currentBinaryCode))
            .findFirst();
    }

}
