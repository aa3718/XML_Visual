package allPkg;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.io.File;

// Testing Drag and Drop functionality for future implementation

public class Drag
        extends JComponent {

    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;
    static Graphics2D graphicSettings;
    static Shape drawLine;

    public Drag() {
        System.out.println("Drawing..");
        drawLine = new Line2D.Float(480, 480, 0, 0);
        setBorder(new LineBorder(Color.BLUE, 3));
        //setBackground(Color.WHITE);
        //add(new JLabel(new ImageIcon("/Users/Chapman/Desktop/icons/Interface/MainMenu/visual.png")));
        setBounds(0, 0, 100, 100);
        //setOpaque(false);

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                screenX = e.getXOnScreen();
                screenY = e.getYOnScreen();

                myX = getX();
                myY = getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getXOnScreen() - screenX;
                int deltaY = e.getYOnScreen() - screenY;

                setLocation(myX + deltaX, myY + deltaY);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });
    }


        @Override
        public void paint(Graphics g) {

            graphicSettings = (Graphics2D)g;
            graphicSettings.setColor(Color.RED);
            graphicSettings.fillRect(0, 0, getWidth(), getHeight());
            //graphicSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphicSettings.setPaint(Color.BLUE);
            graphicSettings.drawString("Permission",10,10);
            try {
                graphicSettings.drawImage(ImageIO.read(new File("resources/icons/icons/acceptTracking.png")),15,15,null);
            }catch (Exception e){
               e.printStackTrace();
            }
        }


    public static void main(String[] args) {
        JFrame f = new JFrame("Swing Hello World");

        JPanel panelBox = new JPanel();
        panelBox.setBounds(0, 0, 250, 200);
        panelBox.setBackground(Color.lightGray);
        panelBox.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.blue));
        f.setLayout(null);

        Drag mc = new Drag();
        f.add(mc);
        f.add(panelBox);

        f.setSize(500, 500);

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }

}

