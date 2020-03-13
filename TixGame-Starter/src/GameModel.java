/**
 * Class to model the play of the game
 * <p>
 * You should implement the public interface methods below
 */
public class GameModel {

    private final int BLACK = 1;
    private final int WHITE = -1;
    private int size;
    private int gameModel[];
    private int id[];
    private int sizeList[];


    /**
     * Construct a game with given sizexsize
     *
     * @param sz the square size of the board
     */
    public GameModel(int sz) {
        size = sz;
        gameModel = new int[sz * sz];
        id = new int[sz * sz];
        sizeList = new int[sz * sz];

        for (int i = 0; i < sz * sz; i++) {
            gameModel[i] = 0;
            id[i] = i;
            sizeList[i] = 1;
        }
    }


    /**
     * Can a play be made at position row, col?
     *
     * @param row the row in question
     * @param col the col in question
     * @return true if row, col is empty, false o.w.
     */
    public boolean canPlay(int row, int col) {

        int idx = row * size + col;

        return (gameModel[idx] == 0);
    }

    /**
     * play a piece at the specified spot by the specified player
     *
     * @param row    the row where a piece is played
     * @param col    the col where a piece is played
     * @param player -1 for white and 1 for black
     * @return true if the game is over and false otherwise
     */
    public boolean makePlay(int row, int col, int player) {

        // fill gameModel array with a player's number
        int idx = row * size + col;
        gameModel[idx] = player;


        // find and validate all the possible unions
        //up
        if (row > 0)
            if (gameModel[idx - size] == player) { union(idx, idx - size); }

        //down
        if (row != size - 1)
            if (gameModel[idx + size] == player) { union(idx, idx + size); }

        //left
        if (col > 0)
            if (gameModel[idx - 1] == player) { union(idx, idx - 1); }

        //right
        if (col != size - 1)
            if (gameModel[idx + 1] == player) { union(idx, idx + 1); }

        //upper-right
        if (row > 0 && col != size - 1)
            if (gameModel[idx - size + 1] == player) { union(idx, idx - size + 1); }

        //lower-left
        if (row != size - 1 && col > 0)
            if (gameModel[idx + size - 1] == player) { union(idx, idx + size - 1); }


        //Test purpose
        //printBoard();

        // checks if the current state is a goal state
        if (player == WHITE) {
            for (int i = 0; i < size; i++) {
                if (gameModel[i] == WHITE) {
                    for (int j = 0; j < size; j++) {
                        int index = size * (size - 1) + j;

                        if (gameModel[index] == WHITE) {
                            if (find(i) == find(index))
                                return true;
                        }
                    }
                }
            }
        } else if (player == BLACK) {
            for (int i = 0; i < size; i++) {
                if (gameModel[size * i] == BLACK) {
                    for (int j = 1; j <= size; j++) {
                        int index = size * j - 1;

                        if (gameModel[index] == BLACK)
                            if (find(size * i) == find(index))
                                return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     *  Find an parent index connected to a given index node
     *
     * @param p     an index of the board
     * @return      the id of p
     */
    private int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }

        return p;
    }

    /**
     * Change the root of either p or q to point to the root of the other
     *
     * @param p     an index of the board
     * @param q     another index of the board
     */
    private void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if (i == j) return;

        if(sizeList[i] < sizeList[j]) {
            id[i] = j;
            sizeList[j] += sizeList[i];
        }
        else {
            id[j] = i;
            sizeList[i] += sizeList[j];
        }
    }

    /**
     * Print the board to console - perhaps you want this for debugging
     * Not required and can be deleted if you want
     */
    private void printBoard() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%3d", id[i * size + j]);
            }
            System.out.println();
        }
    }

}
