//File: GUIDemo.java
//Name: Haleigh Jayde Doetschman
//Date: 2/07/2019
//Class: CMSC 335 Spring 2019
//Purpose: Defines the GUI and contains main method, tests user reaction time

package guidemo;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class GUIDemo extends JFrame {

    //declare variables
    JPanel guiPanel = new JPanel();
    JButton reactButton = new JButton("React!");
    JButton resetButton = new JButton("Reset");
    JTextArea output = new JTextArea("Push Reset to Start. Click React When Color Changes.");
    int randomInt = ThreadLocalRandom.current().nextInt(10, 100);
    Timer timer;
    Timestamp delay;

    public GUIDemo() {

        //layout within frame
        GroupLayout layout = (new GroupLayout(guiPanel));
        guiPanel.setLayout(layout);
        guiPanel.add(reactButton);
        guiPanel.add(resetButton);

        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGap(20, 20, 20)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(resetButton)
                        .addContainerGap(100, 100)
                        .addComponent(reactButton))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(output))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(resetButton)
                        .addComponent(reactButton)
                )
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(output)
                )
                .addGap(20)
        );

        //refine panel elements
        JFrame panelFrame = new JFrame("Reaction Time");
        panelFrame.add(guiPanel);
        panelFrame.setSize(350, 350);
        panelFrame.setVisible(true);
        output.setEditable(false);
        panelFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //action listeners defining button actions
        resetButton.addActionListener((java.awt.event.ActionEvent f) -> {
            output.setBackground(Color.white);
            delay = new Timestamp(System.currentTimeMillis());
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                @Override
                public void run() {
                    output.setBackground(Color.blue);
                    
                }
            },
                    randomInt * 100
            );
            reactButton.addActionListener((java.awt.event.ActionEvent g) -> {
                long milliSecs = System.currentTimeMillis() - delay.getTime() - (randomInt * 100);
                output.setText("Reaction time: " + milliSecs + " ms");
            });

        });
    }

    public static void main(String[] args) {
        GUIDemo demo = new GUIDemo();
    }

}
