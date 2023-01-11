package game;

public class MoveHeuristic extends Move {
    int heuristic;

    public MoveHeuristic(int row, int col, int heuristic) {
        super(row, col);
        this.heuristic = heuristic;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

}
