/**
 * Created by Max Bo on 7/08/2016.
 */

import javafx.geometry.Point2D;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HexGrid is used to construct and build the grid, and provide a single entry
 * for fetching a tile from the board. Once the tile has been retrieved, the
 * Tile instance provides a single coherent interface to manipulate
 * the rest of the board.
 */
public class HexGrid implements Iterable<Tile> {

    // Hardcoded appearance values
    private final int MAP_HEIGHT = 5;
    private final int MAP_WIDTH = 6;
    final int OFFSET = Offset.EVEN;
    final char OFFSET_TYPE = 'R';

    private final Layout layout;
    private final Map<Hex, Tile> map; // I mean, it's a literal map

    public HexGrid() {
        // Pointy <-> r-offset arrangement
        Orientation orientation = Layout.POINTY;
        Point2D size = new Point2D(72, 48);
        Point2D origin = new Point2D(256, 256);

        layout = new Layout(orientation, size, origin);
        map = new HashMap<>();

        for (int r = 0; r < MAP_HEIGHT; r++) {
            int r_offset = r / 2;

            for (int q = -r_offset - 1; q < MAP_WIDTH - r_offset; q++) {

                // What tag does the position have
                Hex location = new Hex(q, r, -q - r);

                if (location.toRoffset(OFFSET).col >= 0) {
                    map.put(location, new Tile(this, location));
                }
                // Give the tile a copy of this class, and its location,
                // so that it can reason about its position on the screen
            }
        }
    }

    @Override
    public Iterator<Tile> iterator() {
        return map.values().iterator();
    }

    public Tile pointToTile(int x, int y) {
        return map.get(layout.pointToHex(new Point2D(x, y)).round());
    }

    Map<Hex, Tile> getMap() {
        return Collections.unmodifiableMap(map);
    }

    Layout getLayout() {
        return layout;
    }
}

