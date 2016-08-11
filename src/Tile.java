/**
 * Created by Max Bo on 7/08/2016.
 */

import javafx.geometry.Point2D;

import java.util.*;


class Tile {

    private final HexGrid grid;
    private final Hex hex;
    private Minion minion;

    Tile(HexGrid grid, Hex hex) {
        this.grid = grid;
        this.hex = hex;
        this.minion = null;
    }

    public Optional<Tile> getNeighbor(int direction) {
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

    private ArrayList<Tile> getEmptyNeighbors() {
        ArrayList<Tile> collector = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Optional<Tile> n = getNeighbor(i);

            // Only yield empty tiles
            if (n.isPresent() && !n.get().getMinion().isPresent())
                collector.add(getNeighbor(i).get());
            }
        return collector;
    }

    // Don't even bother asking me to explain this
    public Optional<ArrayList<Tile>> pathTo(Tile target) {
        LinkedList<Tile> frontier = new LinkedList<>();
        frontier.add(this);

        HashMap<Tile, Tile> cameFrom = new HashMap<>();
        cameFrom.put(this, null);

        while (!frontier.isEmpty()) {
            Tile current = frontier.removeFirst();

            for (Tile next : current.getEmptyNeighbors()) {
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
                return Optional.empty();
            }

            path.add(current);
        }

        path.add(this);
        Collections.reverse(path);
        return Optional.of(path);
    }

    public Set<Tile> reachableTiles(int steps) {
        Set<Tile> visited = new HashSet<>();
        visited.add(this);

        ArrayList<ArrayList<Tile>> fringes = new ArrayList<>();
        ArrayList<Tile> start = new ArrayList<>();
        start.add(this);
        fringes.add(start);

        for (int k = 1; k <= steps; k++) {
            ArrayList<Tile> collector = new ArrayList<>();
            fringes.add(collector);

            for (Tile tile : fringes.get(k - 1)) {
                for (Tile neighbor : tile.getEmptyNeighbors()) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        fringes.get(k).add(neighbor);
                    }
                }
            }
        }

        return visited;
    }

    public Offset toOffset() {
        if (grid.OFFSET_TYPE == 'R') {
            return hex.toRoffset(grid.OFFSET);
        }
        else {
            return hex.toQoffset(grid.OFFSET);
        }
    }

    @Override
    public String toString() {
        return "Tile{" +
                "grid=" + grid +
                ", hex=" + hex +
                ", minion=" + minion +
                '}';
    }
}
