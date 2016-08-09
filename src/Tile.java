import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Max on 7/08/2016.
 */
class Tile {

    final HexGrid grid;
    final Hex hex;

    Tile(HexGrid grid, Hex hex) {
        this.grid = grid;
        this.hex = hex;
    }

    public Optional<Tile> neighbor(int direction) {
        return Optional.of(grid.getMap().get(this.hex.neighbor(direction)));
    }

    public Point2D getCoords() {
        return grid.getLayout().hexToPoint(this.hex);
    }

    ArrayList<Point2D> getCorners() {
        return grid.getLayout().corners(this.hex);
    }

    public int[] getXPoints() {
        return getCorners()
                .stream()
                .mapToInt(x -> (int)(x.getX()))
                .toArray();
    }

    public int[] getYPoints() {
        return getCorners()
                .stream()
                .mapToInt(x -> (int)(x.getY()))
                .toArray();
    }
}
