package lijntjesverbinden;

import javax.swing.*;

public class Button extends JButton {

    protected int answerId;

    public Button(String id, int i) {
        super(id);
        this.answerId = i;
    }
}
