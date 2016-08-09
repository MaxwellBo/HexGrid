import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
                Offset foundOffset = found.hex.toRoffset(Offset.EVEN);
                System.out.println(Integer.toString(foundOffset.row)
                        + " "
                        + Integer.toString(foundOffset.col));
            }
        });
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);

        for (Tile tile : grid) {
            int x = (int) tile.getCoords().getX();
            int y = (int) tile.getCoords().getY();
            g.fillRect(x, y, 6, 6);

            Offset offset = tile.hex.toRoffset(Offset.EVEN);
            g.drawString(Integer.toString(offset.row)
                    + " "
                    + Integer.toString(offset.col), x, y);

            g.drawPolygon(tile.getXPoints(), tile.getYPoints(), 6);
        }
    }
}
