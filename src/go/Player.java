
package go;

/**
 *
 * @author Alex
 */
public class Player {

    private int deadStones = 0;
    private int turn = 0;
    private int playerType;

    public Player(int playerType) {
        this.playerType = playerType;
    }

    public int getDeadStones() {
        return deadStones;
    }

    public void setDeadStones(int sc) {
        deadStones = sc;
    }

    public void increaseDeadStones() {
        ++deadStones;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int t) {
        turn = t;
    }

    public void nextTurn() {
        ++turn;
    }
    
    public int getPlayerType() {
        return playerType;
    }

}
