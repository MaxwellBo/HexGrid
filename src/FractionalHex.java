/**
 * Created by Max on 7/08/2016.
 */

class FractionalHex {
    final double q;
    final double r;
    final double s;

    FractionalHex(double q, double r, double s) {
        this.q = q;
        this.r = r;
        this.s = s;
    }

    Hex round() {
        int q = (int)(Math.round(this.q));
        int r = (int)(Math.round(this.r));
        int s = (int)(Math.round(this.s));
        double q_diff = Math.abs(q - this.q);
        double r_diff = Math.abs(r - this.r);
        double s_diff = Math.abs(s - this.s);

        if (q_diff > r_diff && q_diff > s_diff) {
            q = -r - s;
        }
        else {
            if (r_diff > s_diff) {
                r = -q - s;
            }
            else {
                s = -q - r;
            }
        }
        return new Hex(q, r, s);
    }
}
