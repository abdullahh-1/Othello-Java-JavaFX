package game;
public class Move {
    private int x;
    private int y;

    public Move(int row, int col) {
        this.x = row;
        this.y = col;
    }

    /**
     * This function returns the value of the x variable.
     *
     * @return The value of the x variable.
     */
    public int getX() {
        return x;
    }

    /**
     * This function returns the value of the y variable.
     *
     * @return The y value of the point.
     */
    public int getY() {
        return y;
    }

    public boolean equals(Object o){
        if(o instanceof Move){
            return ((Move) o).x == this.x && ((Move) o).y == this.y;
        }else{
            return false;
        }
    }
}


