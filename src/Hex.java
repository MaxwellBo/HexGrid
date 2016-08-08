/**
 * Created by Max Bo on 7/08/2016.
 */

import java.util.ArrayList;

class Hex {

    static final private ArrayList<Hex> directions = new ArrayList<Hex>() {{
        add(new Hex(1, 0, -1));
        add(new Hex(1, -1, 0));
        add(new Hex(0, -1, 1));
        add(new Hex(-1, 0, 1));
        add(new Hex(-1, 1, 0));
        add(new Hex(0, 1, -1));
    }};

    static final private ArrayList<Hex> diagonals = new ArrayList<Hex>() {{
        add(new Hex(2, -1, -1));
        add(new Hex(1, -2, 1));
        add(new Hex(-1, -1, 2));
        add(new Hex(-2, 1, 1));
        add(new Hex(-1, 2, -1));
        add(new Hex(1, 1, -2));
    }};

    final int q;
    final int r;
    final int s;


    Hex(int q, int r, int s) {
        this.q = q;
        this.r = r;
        this.s = s;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Hex hex = (Hex) other;

        return (q == hex.q && r == hex.r && s == hex.s);
    }

    @Override
    public int hashCode() {
        int result = q;
        result = 23 * result + r;
        result = 31 * result + s;
        return result;
    }

    @Override
    public String toString() {
        return "Hex{" +
                "q=" + q +
                ", r=" + r +
                ", s=" + s +
                '}';
    }
    private Hex add(Hex other) {
        return new Hex(q + other.q, r + other.r, s + other.s);
    }

    private Hex subtract(Hex other) {
        return new Hex(q - other.q, r - other.r, s - other.s);
    }

    private Hex scale(int k) {
        return new Hex(q * k, r * k, s * k);
    }

    Hex neighbor(int direction) {
        Hex vector = directions.get(direction);

        return add(vector);
    }

    Hex diagonalNeighbor(int direction) {
        Hex vector = diagonals.get(direction);

        return add(vector);
    }

    int length() {
        return (int)((Math.abs(q) + Math.abs(r) + Math.abs(s)) / 2);
    }

    int distance(Hex other) {
        return subtract(other).length();
    }

    Offset toQoffset(int offset) {
        int col = q;
        int row = r + (int)((q + offset * (q & 1)) / 2);
        return new Offset(col, row);
    }

    Offset toRoffset(int offset) {
        int col = q + (int)((r + offset * (r & 1)) / 2);
        int row = r;
        return new Offset(col, row);
    }
}