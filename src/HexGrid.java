import javafx.geometry.Point2D;

import java.util.HashMap;

/**
 * Created by Max on 7/08/2016.
 */

/**
 * HexGrid is used to construct and build
 */
public class HexGrid {

    // Hardcoded appearance values
    private final int MAP_HEIGHT = 5;
    private final int MAP_WIDTH = 6;

    final Layout layout;
    final HashMap<Hex, Tile> board;

    public HexGrid() {
        // Pointy <-> r-offset arrangement
        Orientation orientation = Layout.POINTY;
        Point2D size = new Point2D(16, 16);
        Point2D origin = new Point2D(32, 32);

        layout = new Layout(orientation, size, origin);
        board = new HashMap<>();

        for (int r = 0; r < MAP_HEIGHT; r++) {
            int r_offset = r / 2;

            for (int q = -r_offset; q < MAP_WIDTH - r_offset; q++) {
                Hex staged = new Hex(q, r, -q - r);

                // Hand the tile a copy of the grid...
                // ... and its location on the grid
                board.put(staged, new Tile(board, staged));
            }
        }
    }

    public void view() {
        for (Hex hex : board.keySet()) {
            System.out.println(hex.toRoffset(Offset.EVEN));
        }
    }
}
