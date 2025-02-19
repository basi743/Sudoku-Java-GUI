public class State {
    int row;
    int col;
    int prevValue;
    int newValue;

    public State(int row, int col, int prevValue, int newValue) {
        this.row = row;
        this.col = col;
        this.newValue = newValue;
        this.prevValue = prevValue;
    }
}
