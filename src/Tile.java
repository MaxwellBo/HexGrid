/**
 * Created by Max Bo on 7/08/2016.
 */

import javafx.geometry.Point2D;

import java.util.*;


class Tile {

    final HexGrid grid;
    final Hex hex;
    private Minion minion;

    Tile(HexGrid grid, Hex hex) {
        this.grid = grid;
        this.hex = hex;
        this.minion = null;
    }

    public Optional<Tile> neighbor(int direction) {
        return Optional.ofNullable(grid.getMap().get(this.hex.neighbor(direction)));
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

    public Optional<Minion> getMinion() {
        return Optional.ofNullable(minion);
    }

    public void setMinion(Minion x) {
        minion = x;
    }

    private ArrayList<Tile> emptyNeighbors() {
        ArrayList<Tile> collector = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Optional<Tile> n = neighbor(i);

            // Only yield empty tiles
            if (n.isPresent() && !n.get().getMinion().isPresent())
                collector.add(neighbor(i).get());
            }
        return collector;
    }

    // Don't even bother asking me to explain this
    public ArrayList<Tile> pathTo(Tile target) {
        LinkedList<Tile> frontier = new LinkedList<>();
        frontier.add(this);

        HashMap<Tile, Tile> cameFrom = new HashMap<>();
        cameFrom.put(this, null);

        while (!frontier.isEmpty()) {
            Tile current = frontier.removeFirst();

            for (Tile next : current.emptyNeighbors()) {
                if (!cameFrom.containsKey(next)) {
                    frontier.add(next);
                    cameFrom.put(next, current);
                }
            }
        }

        Tile current = target;
        ArrayList<Tile> path = new ArrayList<>();
        path.add(current);

        while (current != this) {
            current = cameFrom.get(current);

            if (current == null) {
                // Path invalid
                return new ArrayList<>();
            }

            path.add(current);
        }

        path.add(this);
        Collections.reverse(path);
        return path;
    }
}
