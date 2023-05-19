import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/* [MatrixDisplayWithMouse.java]
 * A small program showing how to use the MatrixDisplayWithMouse class
 *  NOTE - A lot of things to fix here!
 * @author Mangat
 */


class MatrixDisplayWithMouse extends JFrame {

    int maxX,maxY, GridToScreenRatio;

    MapDatabase map;

    Tile[][] matrix;
    MatrixDisplayWithMouse(String title, MapDatabase map) {
        super(title);

        this.map = map;
        this.matrix = map.getMap();
        maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
        maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
        GridToScreenRatio = maxY / (matrix.length + 1);  //ratio to fit in screen as square map

        System.out.println(GridToScreenRatio);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        this.add(new MatrixPanel());

        this.setVisible(true);
    }

    public void refresh() {
        this.repaint();
    }

    //Inner Class
    class MatrixPanel extends JPanel {

        MatrixPanel() {

            addMouseListener(new MatrixPanelMouseListener());
        }

        public void paintComponent(Graphics g) {
            super.repaint();

            setDoubleBuffered(true);
            g.setColor(Color.BLACK);
            g.drawOval(50, 50, 50, 50);

            g.drawString("Humans", 1400, 300);


            for(int i = 0; i < matrix.length; i ++)  {
                for(int j = 0; j < matrix[i].length; j ++)  {
                    Tile spot = matrix[i][j];

                    if (spot instanceof Ground)
                        g.setColor(Color.LIGHT_GRAY);
                    else if (spot instanceof Zombie)    //This block can be changed to match character-color pairs
                        g.setColor(Color.RED);
                    else if (spot instanceof Human)
                        if (spot.getAge() <= 4) {
                            g.setColor(Color.YELLOW);
                        } else if (((Human) spot).getGender().equals("male")) {
                            g.setColor(Color.BLUE);
                        } else {
                            g.setColor(Color.MAGENTA);
                        }

                    else if (spot instanceof Nuke)
                        g.setColor(((Nuke) spot).getColor());
                    else
                        g.setColor(Color.GREEN);

                    g.fillRect(j*GridToScreenRatio, i*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
                    g.setColor(Color.BLACK);
                    g.drawRect(j*GridToScreenRatio, i*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
                }
            }


        }
    }


    //Mouse Listener
    class MatrixPanelMouseListener implements MouseListener{
        //Mouse Listner Stuff
        public void mousePressed(MouseEvent e) {
            System.out.println("Mouse pressed; # of clicks: " + e.getClickCount());
            System.out.println("x: " + e.getPoint().x + ",y: " + e.getPoint().y);
            map.getNukeQueue().add(new ArrayList<Integer>());
            map.getNukeQueue().get(map.getNukeQueue().size() - 1).add((e.getPoint().x / GridToScreenRatio));
            map.getNukeQueue().get(map.getNukeQueue().size() - 1).add((e.getPoint().y / GridToScreenRatio));

        }

        public void mouseReleased(MouseEvent e) {
            System.out.println("Mouse released; # of clicks: " + e.getClickCount());
        }

        public void mouseEntered(MouseEvent e) {
            System.out.println("Mouse entered");
        }

        public void mouseExited(MouseEvent e) {
            System.out.println("Mouse exited");
        }

        public void mouseClicked(MouseEvent e) {
            System.out.println("Mouse clicked (# of clicks: "+ e.getClickCount() + ")");
        }

    }

    class MatrixPanelKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}