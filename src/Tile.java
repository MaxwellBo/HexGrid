import javafx.geometry.Point2D;

import java.util.Map;

/**
 * Created by Max on 7/08/2016.
 */
public class Tile {

    private final Map<Hex, Tile> board;
    public final Hex hex;
    public final Point2D coords;

    public Tile(Map<Hex, Tile> board, Hex hex, Point2D coords) {
        this.board = board;
        this.hex = hex;
        this.coords = coords;
    }


    public Tile neighbor(int direction) {
        return board.get(hex.neighbor(direction));
    }

}
