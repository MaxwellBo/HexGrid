import javafx.geometry.Point2D;

/**
 * Created by Max Bo on 7/08/2016.
 */

class Layout {
    static Orientation POINTY = new Orientation(
            Math.sqrt(3.0)
            , Math.sqrt(3.0) / 2.0
            , 0.0, 3.0 / 2.0
            , Math.sqrt(3.0) / 3.0
            , -1.0 / 3.0, 0.0
            , 2.0 / 3.0
            , 0.5);

    static Orientation FLAT = new Orientation(
            3.0 / 2.0
            , 0.0
            , Math.sqrt(3.0) / 2.0
            , Math.sqrt(3.0)
            , 2.0 / 3.0
            , 0.0, -1.0 / 3.0
            , Math.sqrt(3.0) / 3.0
            , 0.0);

    private final Orientation orientation;
    private final Point2D size;
    private final Point2D origin;

    Layout(Orientation orientation, Point2D size, Point2D origin) {
        this.orientation = orientation;
        this.size = size;
        this.origin = origin;
    }

    Point2D hexToPoint(Hex h) {
        Orientation o = orientation;

        double x = (o.f0 * h.q + o.f1 * h.r) * size.getX();
        double y = (o.f2 * h.q + o.f3 * h.r) * size.getY();

        return new Point2D(x + origin.getX(), y + origin.getY());
    }

    FractionalHex pointToHex(Point2D p) {
        Orientation o = orientation;

        Point2D pt = new Point2D((p.getX() - origin.getX()) / size.getX()
                , (p.getY() - origin.getY()) / size.getY());

        double q = o.b0 * pt.getX() + o.b1 * pt.getY();
        double r = o.b2 * pt.getX() + o.b3 * pt.getY();

        return new FractionalHex(q, r, -q - r);
    }

}
