import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Max on 7/08/2016.
 */
public class Tile {

    final HexGrid grid;
    final Hex hex;

    public Tile(HexGrid grid, Hex hex) {
        this.grid = grid;
        this.hex = hex;
    }

    public Tile neighbor(int direction) {
        return grid.getMap().get(this.hex.neighbor(direction));
    }

    public Point2D getCoords() {
        return grid.getLayout().hexToPoint(this.hex);
    }

    public ArrayList<Point2D> getCorners() {
        return grid.getLayout().corners(this.hex);
    }

}
