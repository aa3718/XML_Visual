package allPkg;

import javax.swing.*;
import java.awt.*;

// Testing the JScrollPanel functionality

public class testYeD {
    public JPanel panelBase;
    public JPanel panelOut;

    public void drawYED() {
        //GraphComponent graphComponent = new GraphComponent();

        JFrame frame = new JFrame("Hello, yFiles for Java (Swing)");
        frame.setSize(700, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panelBase = new JPanel();
        panelOut = new JPanel();
        panelOut.setLayout(new GridLayout(2,3,5,5));

        JScrollPane scroll = new JScrollPane(panelOut);
        panelBase.setAutoscrolls(true);

        //scroll.setPreferredSize(new Dimension(800,300));
        scroll.setBounds(0,0,600,600);

        panelBase.add(scroll);
        panelBase.setBounds(0, 0, 600, 600);
        panelBase.setLayout(null);

        frame.add(panelBase);
        frame.setVisible(true);

        for(int i =0; i < 40; i++) {
            JPanel buttonONe = new JPanel();
            buttonONe.setPreferredSize(new Dimension(200,200));
            buttonONe.setBackground(Color.pink);
            JLabel label = new JLabel("YEPP"+i);
            buttonONe.add(label);
            panelOut.add(buttonONe);
        }

        panelOut.repaint();
        panelOut.revalidate();

    }

    public static void main(String[] argvs) {
        testYeD tes = new testYeD();
        tes.drawYED();
    }

}
