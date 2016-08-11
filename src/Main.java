import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Optional;

public class Main extends JPanel {

    public static void main(String[] args) {
        DisplayFrame frame = new DisplayFrame();
        frame.setVisible(true);
    }
}
@SuppressWarnings("serial")
class DisplayFrame extends JFrame {

    private DisplayPanel panel = new DisplayPanel();

    public DisplayFrame() {
        setTitle("Hex Grid");
        setBounds(50, 50, 700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.add(panel);
    }
}

@SuppressWarnings("serial")
class DisplayPanel extends JPanel {

    HexGrid grid;

    public DisplayPanel() {
        this.grid = new HexGrid();

        setBackground(Color.white);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Tile found = grid.pointToTile(e.getX(), e.getY());

                switch (e.getButton()) {
                    case MouseEvent.BUTTON1:
                        found.setMinion(new Minion(1, 1));
                        repaint();
                        break;
                    case MouseEvent.BUTTON2:
                    case MouseEvent.BUTTON3:
                        Offset foundOffset = found.hex.toRoffset(Offset.EVEN);
                        System.out.println(Integer.toString(foundOffset.row)
                            + " "
                            + Integer.toString(foundOffset.col));
                        System.out.println(found.getMinion().isPresent());
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Tile tile : grid) {

            int x = (int) tile.getCoords().getX();
            int y = (int) tile.getCoords().getY();

            g.setColor(Color.RED);
            if (tile.getMinion().isPresent()) {
                g.fillRect(x - 10, y - 10, 20, 20);
            }

            g.setColor(Color.BLACK);
            Offset offset = tile.hex.toRoffset(Offset.EVEN);
            g.drawString(Integer.toString(offset.row)
                    + " "
                    + Integer.toString(offset.col), x, y);

            g.drawPolygon(tile.getXPoints(), tile.getYPoints(), 6);
        }

        Tile start = grid.pointToTile(256, 256);
        Tile finish = grid.pointToTile(800, 550);

        ArrayList<Tile> path = start.pathTo(finish).orElse(new ArrayList<>());

        int[] xs = path
                    .stream()
                    .mapToInt(x -> (int)(x.getCoords().getX()))
                    .toArray();

        int[] ys = path
                .stream()
                .mapToInt(x -> (int)(x.getCoords().getY()))
                .toArray();
        g.drawPolyline(xs, ys, path.size());

    }
}
