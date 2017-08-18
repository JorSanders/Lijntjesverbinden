package lijntjesverbinden;

import java.awt.Graphics;
import java.util.*;
import javax.swing.JComponent;

class TextCanvas extends JComponent {

    private int x, y;
    ArrayList<String> strings = new ArrayList<>();
    List<Integer> posX = new ArrayList<>();
    List<Integer> posY = new ArrayList<>();

    public void addString(String string, int x, int y) {
        strings.add(string);
        posX.add(x);
        posY.add(y);
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < strings.size(); i++) {
            g.drawString(strings.get(i), posX.get(i), posY.get(i));
        }
    }
}
