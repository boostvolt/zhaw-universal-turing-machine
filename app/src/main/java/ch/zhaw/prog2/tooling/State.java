package ch.zhaw.prog2.tooling;

import java.util.Objects;

public class State {

    private final String name;
    private final boolean accepted;
    private final boolean start;

    public State(final String binaryCode) {
        this("q" + binaryCode.length(), binaryCode.length() == 2, binaryCode.length() == 1);
    }

    private State(String name, boolean accepted, boolean start) {
        this.name = name;
        this.accepted = accepted;
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public boolean isStart() {
        return start;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return accepted == state.accepted && start == state.start && Objects.equals(name,
            state.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, accepted, start);
    }

    @Override
    public String toString() {
        return "State{" +
            "name='" + name + '\'' +
            ", accepted=" + accepted +
            ", start=" + start +
            '}';
    }

}
