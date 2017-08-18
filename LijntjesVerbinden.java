package lijntjesverbinden;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LijntjesVerbinden {

    private Button[] buttonList;
    private final int buttonWidth, buttonHeight;
    private final int wordsPerRow;
    private final JFrame frame = new JFrame();
    private final LinePanel linePanel = new LinePanel();
    private final TextCanvas textCanvas;
    private int x1, x2, y1, y2, a1, a2;

    public LijntjesVerbinden() {
        //initialize variables
        wordsPerRow = 5;
        buttonWidth = 100;
        buttonHeight = 60;
        buttonList = new Button[wordsPerRow * 2];
        insertWords();

        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
        a1 = -1;
        a2 = -1;

        frame.setDefaultCloseOperation(3);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);

        linePanel.setBounds(0, 0, 600, 600);
        frame.add(linePanel);

        Container cp = frame.getContentPane();
        textCanvas = new TextCanvas();
        textCanvas.setBounds(0, 0, 600, 600);
        textCanvas.addString("verbind de bij elkaar horende woorden", 200, 10);
        cp.add(textCanvas);

        buttonList = RandomizeButtonList(buttonList);
        addWordsOnScreen();

        for (int i = 0; i < buttonList.length; i++) {
            buttonClick(i);
        }

    }

    public Button[] RandomizeButtonList(Button[] array) {
        Button[] array1 = new Button[array.length / 2];
        Button[] array2 = new Button[array.length / 2];

        for (int i = 0; i < array.length / 2; i++) {
            array1[i] = array[i];
        }

        for (int i = array.length; i < array.length; i++) {
            array2[i] = array[i];
        }

        Collections.shuffle(Arrays.asList(array1));
        Collections.shuffle(Arrays.asList(array2));

        for (int i = 0; i < array.length / 2; i++) {
            array[i] = array1[i];
        }

        for (int i = array.length; i < array.length; i++) {
            array[i] = array2[i];
        }

        return array;
    }

    private void buttonClick(final int i) {
        buttonList[i].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (i < wordsPerRow) {
                    x1 = buttonList[i].getX() + buttonWidth / 2;
                    y1 = buttonList[i].getY() + buttonHeight / 2;
                    a1 = buttonList[i].answerId;

                } else {
                    x2 = buttonList[i].getX() + buttonWidth / 2;
                    y2 = buttonList[i].getY() + buttonHeight / 2;
                    a2 = buttonList[i].answerId;
                }
                drawLine();

            }

        });
    }

    private void insertWords() {
        if (wordsPerRow >= 1) {
            buttonList[0] = new Button("een", 0);
            buttonList[0 + wordsPerRow] = new Button("one", 0);

        }
        if (wordsPerRow >= 2) {
            buttonList[1] = new Button("twee", 1);
            buttonList[1 + wordsPerRow] = new Button("two", 1);
        }
        if (wordsPerRow >= 3) {
            buttonList[2] = new Button("drie", 2);
            buttonList[2 + wordsPerRow] = new Button("three", 2);
        }
        if (wordsPerRow >= 4) {
            buttonList[3] = new Button("vier", 3);
            buttonList[3 + wordsPerRow] = new Button("four", 3);
        }
        if (wordsPerRow >= 5) {
            buttonList[4] = new Button("vijf", 4);
            buttonList[4 + wordsPerRow] = new Button("five", 4);
        }
        if (wordsPerRow >= 6) {
            buttonList[5] = new Button("zes", 5);
            buttonList[5 + wordsPerRow] = new Button("six", 5);
        }
    }

    private void addWordsOnScreen() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);

        float posX, posY;
        posX = (float) (frame.getContentPane().getWidth() * 0.2);
        posY = buttonHeight / 2;

        for (int i = 0; i < buttonList.length; i++) {
            buttonList[i].setBounds((int) posX, (int) posY, buttonWidth, buttonHeight);
            buttonPanel.add(buttonList[i]);

            posY += frame.getContentPane().getHeight() / (wordsPerRow);
            if (i >= wordsPerRow - 1 && i < wordsPerRow) {
                posX = (float) (frame.getContentPane().getWidth() * 0.8 - buttonWidth);
                posY = buttonHeight / 2;
            }
        }
        buttonPanel.setBounds(0, 0, 600, 600);
        frame.getContentPane().add(buttonPanel);
    }

    private void drawLine() {
        if (a1 != -1 && a2 != -1) {
            if (a1 == a2) {
                linePanel.addLine(x1, y1, x2, y2, Color.green);

                for (int i = 0; i < buttonList.length; i++) {
                    if (buttonList[i].answerId == a1) {
                        buttonList[i].setEnabled(false);
                    }
                }
                int j = 0;
                for (int i = 0; i < buttonList.length; i++) {
                    if (buttonList[i].isEnabled() == false) {
                        j++;
                    }
                }
                if (j == buttonList.length) {
                    textCanvas.addString("gewonnen", 250, 300);
                }
            } else {
                linePanel.addLine(x1, y1, x2, y2, Color.red);
            }
            a1 = -1;
            a2 = -1;
        }

    }

    public static void main(String[] args) {

        new LijntjesVerbinden();
    }
}
