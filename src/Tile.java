import java.util.HashMap;

/**
 * Created by Max on 7/08/2016.
 */
public class Tile {

    private final HashMap<Hex, Tile> board;
    public final Hex location;
    // TODO: This is just a mock object that we can cram into our tile
    private String entity;

    public Tile(HashMap<Hex, Tile> board, Hex location) {
        this.board = board;
        this.location = location;
        this.entity = "";
    }

    @Override
    public String toString() {
        return "Tile{" +
                "board=" + board +
                ", location=" + location +
                ", entity='" + entity + '\'' +
                '}';
    }

    public Tile neighbor(int direction) {
        return board.get(location.neighbor(direction));
    }

}
