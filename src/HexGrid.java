import javafx.geometry.Point2D;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Max on 7/08/2016.
 */

/**
 * HexGrid is used to construct and build
 */
public class HexGrid implements Iterable<Tile> {

    // Hardcoded appearance values
    private final int MAP_HEIGHT = 5;
    private final int MAP_WIDTH = 6;

    private final Layout layout;
    private final Map<Hex, Tile> board;

    public HexGrid() {
        // Pointy <-> r-offset arrangement
        Orientation orientation = Layout.POINTY;
        Point2D size = new Point2D(16, 16);
        Point2D origin = new Point2D(32, 32);

        layout = new Layout(orientation, size, origin);
        board = new HashMap<>();
        Map<Hex, Tile> frozenBoard = Collections.unmodifiableMap(board);

        for (int r = 0; r < MAP_HEIGHT; r++) {
            int r_offset = r / 2;

            for (int q = -r_offset; q < MAP_WIDTH - r_offset; q++) {

                // What tag does the position have
                Hex hex = new Hex(q, r, -q - r);
                // Where do we want it to go on our screen
                Point2D screenspace = layout.hexToPoint(hex);

                board.put(hex, new Tile(frozenBoard, hex, screenspace));
            }
        }
    }

    @Override
    public Iterator<Tile>iterator() {
        return board.values().iterator();
    }

    public void view() {
        for (Hex hex : board.keySet()) {
            System.out.println(hex.toRoffset(Offset.EVEN));
        }
    }

    public Tile pixel_to_tile(int x, int y) {
        return board.get(layout.pointToHex(new Point2D(x, y)).round());
    }
}
