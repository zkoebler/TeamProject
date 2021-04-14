/*
Zephrom Koebler ID:1216780526
*/

package com.codebind;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App
{
    private JPanel ThePanel;
    private JButton loadDataButton;
    private JButton saveDataButton;
    private JButton addDataButton;
    private JButton aboutButton;
    private JPanel parentPanel;
    private JPanel loadDataPane;
    private JPanel saveDataPane;
    private JPanel addDataPane;
    private JPanel aboutPane;


    public App()
    {
        loadDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataPane.setVisible(true);
                saveDataPane.setVisible(false);
                addDataPane.setVisible(false);
                aboutPane.setVisible(false);
            }
        });
        saveDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataPane.setVisible(false);
                saveDataPane.setVisible(true);
                addDataPane.setVisible(false);
                aboutPane.setVisible(false);
            }
        });

        addDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataPane.setVisible(false);
                saveDataPane.setVisible(false);
                addDataPane.setVisible(true);
                aboutPane.setVisible(false);
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataPane.setVisible(false);
                saveDataPane.setVisible(false);
                addDataPane.setVisible(false);
                aboutPane.setVisible(true);
            }
        });
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("App");                   //sets new frame as a JFrame called "App"
        frame.setContentPane(new App().ThePanel);               //sets the content of the pane to the panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Closes the Jframe if the user presses the X in the corner
        frame.pack();                                           //packs the frame
        frame.setMinimumSize(new Dimension(870, 500));//sets the default minimum dimensions of the window
        frame.setVisible(true);                                 //makes the frame visible
    }
}