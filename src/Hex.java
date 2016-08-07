/**
 * Created by Max Bo on 7/08/2016.
 */

import java.util.ArrayList;

public class Hex {

    static private ArrayList<Hex> directions = new ArrayList<Hex>() {{
        add(new Hex(1, 0, -1));
        add(new Hex(1, -1, 0));
        add(new Hex(0, -1, 1));
        add(new Hex(-1, 0, 1));
        add(new Hex(-1, 1, 0));
        add(new Hex(0, 1, -1));
    }};

    static public ArrayList<Hex> diagonals = new ArrayList<Hex>() {{
        add(new Hex(2, -1, -1));
        add(new Hex(1, -2, 1));
        add(new Hex(-1, -1, 2));
        add(new Hex(-2, 1, 1));
        add(new Hex(-1, 2, -1));
        add(new Hex(1, 1, -2));
    }};

    public final int q;
    public final int r;
    public final int s;


    public Hex(int q, int r, int s) {
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
    public Hex add(Hex other) {
        return new Hex(q + other.q, r + other.r, s + other.s);
    }

    public Hex subtract(Hex other) {
        return new Hex(q - other.q, r - other.r, s - other.s);
    }

    public Hex scale(int k) {
        return new Hex(q * k, r * k, s * k);
    }

    public Hex neighbor(int direction) {
        Hex vector = directions.get(direction);

        return add(vector);
    }

    public Hex diagonalNeighbor(int direction) {
        Hex vector = diagonals.get(direction);

        return add(vector);
    }

    public int length() {
        return (int)((Math.abs(q) + Math.abs(r) + Math.abs(s)) / 2);
    }

    public int distance(Hex other) {
        return subtract(other).length();
    }

    public Offset toQoffset(int offset) {
        int col = q;
        int row = r + (int)((q + offset * (q & 1)) / 2);
        return new Offset(col, row);
    }

    public Offset toRoffset(int offset) {
        int col = q + (int)((r + offset * (r & 1)) / 2);
        int row = r;
        return new Offset(col, row);
    }
}