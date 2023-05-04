package ch.zhaw.prog2.tooling;

import static com.google.common.base.Strings.repeat;
import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.List;

public class TuringMachine {

    private final List<Transition> transitions;
    private final List<String> tape;
    private final boolean stepMode;
    private int headIndex;
    private State currentState;
    private int calculationCount;

    public TuringMachine(final String input, final boolean stepMode) {
        tape = new ArrayList<>();
        initializeTape(input);
        transitions = new ArrayList<>();
        headIndex = 0;
        this.stepMode = stepMode;
    }

    public void addTransition(final Transition transition) {
        transitions.add(transition);
    }

    public void startCalculation() {
        final Transition startTransition = transitions.stream()
            .filter(transition -> transition.getCurrentState().isStart()
                && transition.getReadSymbol().getValue().equals(getSymbolAtIndex(headIndex)))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No start state found"));
        currentState = startTransition.getCurrentState();
        printStepIfApplicable();
        handleTransition(startTransition);
        currentState = startTransition.getNextState();
        while (!currentState.isAccepted()) {
            printStepIfApplicable();
            for (Transition nextTransition : transitions) {
                if (nextTransition.getCurrentState().equals(currentState)
                    && nextTransition.getReadSymbol().getValue()
                    .equals(getSymbolAtIndex(headIndex))) {
                    handleTransition(nextTransition);
                    currentState = nextTransition.getNextState();
                    break;
                }
            }
        }
        printStepIfApplicable();
        System.out.println(repeat("-", 100));
        System.out.println("Result: " + getTapeResult());
    }

    private void printStepIfApplicable() {
        if (stepMode) {
            System.out.println(repeat("-", 100));
            System.out.println("Current state is " + currentState.getName());

            System.out.print("|");
            range(headIndex - 15, headIndex)
                .forEach(i -> {
                    System.out.print(getSymbolAtIndex(i));
                    System.out.print("|");
                });
            System.out.print("X");
            range(headIndex, headIndex + 16)
                .forEach(i -> {
                    System.out.print(getSymbolAtIndex(i));
                    System.out.print("|");
                });
            System.out.println();

            System.out.println("Number of calculation steps " + calculationCount);
        }
    }

    private void handleTransition(final Transition transition) {
        calculationCount++;
        setTapeSymbol(transition.getWriteSymbol().getValue());
        switch (transition.getDirection()) {
            case LEFT -> headIndex--;
            case RIGHT -> headIndex++;
            default -> throw new IllegalArgumentException("Invalid direction");
        }
    }

    private void initializeTape(final String input) {
        if (input != null) {
            range(0, input.length())
                .forEach(i -> tape.add(String.valueOf(input.charAt(i))));
        }
    }

    private String getSymbolAtIndex(final int index) {
        try {
            return tape.get(index);
        } catch (IndexOutOfBoundsException e) {
            return Symbol.BLANK.getValue();
        }
    }

    private void setTapeSymbol(final String newSymbol) {
        try {
            tape.set(headIndex, newSymbol);
        } catch (IndexOutOfBoundsException e) {
            tape.add(headIndex < 0 ? 0 : tape.size(), newSymbol);
        }
    }

    private long getTapeResult() {
        return tape.stream()
            .filter(tapeSymbol -> tapeSymbol.equals(Symbol.ZERO.getValue()))
            .count();
    }

}
