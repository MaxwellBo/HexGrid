import java.util.HashMap;

/**
 * Created by Max on 7/08/2016.
 */
public class HexGridModel {

    private int MAP_HEIGHT = 5;
    private int MAP_WIDTH = 6;

    private HashMap<Hex, Tile> board;

    public HexGridModel() {

        board = new HashMap<>();

        for (int r = 0; r < MAP_HEIGHT; r++) {
            int r_offset = r / 2;

            for (int q = -r_offset; q < MAP_WIDTH - r_offset; q++) {
                Hex staged = new Hex(q, r, -q-r);
                board.put(staged, new Tile(staged));
            }
        }
    }

    public void debug() {
        board.keySet().stream().forEach(x -> System.out.println(x.toString()));
    }
}
