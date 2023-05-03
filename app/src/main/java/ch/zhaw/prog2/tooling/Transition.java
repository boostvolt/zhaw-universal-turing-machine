package ch.zhaw.prog2.tooling;

public class Transition {

    private final State currentState;
    private final Symbol readSymbol;
    private final State nextState;
    private final Symbol writeSymbol;
    private final Direction direction;

    public Transition(State currentState, Symbol readSymbol, State nextState, Symbol writeSymbol,
        Direction direction) {
        this.currentState = currentState;
        this.readSymbol = readSymbol;
        this.nextState = nextState;
        this.writeSymbol = writeSymbol;
        this.direction = direction;
    }

    public State getCurrentState() {
        return currentState;
    }

    public Symbol getReadSymbol() {
        return readSymbol;
    }

    public State getNextState() {
        return nextState;
    }

    public Symbol getWriteSymbol() {
        return writeSymbol;
    }

    public Direction getDirection() {
        return direction;
    }

}
