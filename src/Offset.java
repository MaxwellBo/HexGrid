/**
 * Created by Max Bo on 7/08/2016.
 */

class Offset {

    static final int EVEN = 1;
    static final int ODD = -1;

    public final int col;
    public final int row;

    Offset(int col, int row) {
        this.col = col;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offset offset = (Offset) o;

        if (col != offset.col) return false;
        return row == offset.row;

    }

    @Override
    public int hashCode() {
        int result = col;
        result = 31 * result + row;
        return result;
    }

    @Override
    public String toString() {
        return "Offset{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }

    Hex qoffsetToHex(int offset) {
        int q = col;
        int r = row - (int)((col + offset * (col & 1)) / 2);
        int s = -q - r;
        return new Hex(q, r, s);
    }

    Hex roffsetToHex(int offset) {
        int q = col - (int)((row + offset * (row & 1)) / 2);
        int r = row;
        int s = -q - r;
        return new Hex(q, r, s);
    }
}

