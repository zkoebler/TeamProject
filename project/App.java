/*
Zephrom Koebler ID:1216780526
*/

package com.codebind;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//import java.io.*;

public class App
{
    //---------------------------------
    private JPanel ThePanel;
    private JButton loadDataButton;
    private JButton saveDataButton;
    private JButton addDataButton;
    private JButton aboutButton;
    private JPanel parentPanel;
    private JPanel loadDataPane;
    private JPanel addDataPane;
    private JPanel aboutPane;
    private JButton visualizeDataButton;
    private JPanel visualizeDataPane;
    private JTextField dateTextField;
    private JTextField idTextField;
    private JTextField lastNameTextField;
    private JTextField firstNameTextField;
    private JTextField vaccineTypeTextField;
    private JButton submitButton;
    private JScrollPane loadDataScrollPane;
    private JTextField locationTextField;
    //------------------------------------
    JTable dataTable = new JTable();    //Jtable for data
    public App()
    {
        //adds the data table to the scroll pan and intializes the CSV list and data classes
        loadDataScrollPane.add(dataTable);
        CSVList list = new CSVList();
        CSVData data = new CSVData(list);

        loadDataButton.addActionListener(new ActionListener() {     //executes code if button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataPane.setVisible(true);
                addDataPane.setVisible(false);
                aboutPane.setVisible(false);
                visualizeDataPane.setVisible(false);

                //allows user to chose file to load into the table
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Only CSV files","csv");
                chooser.setFileFilter(filter);
                int ret = chooser.showOpenDialog(null);

                //if file is valid
                if(ret == JFileChooser.APPROVE_OPTION)
                {
                    String fileName = chooser.getSelectedFile().getAbsolutePath().toString();
                    data.loadData(fileName);
                    DefaultTableModel model = new DefaultTableModel();
                    loadData(model);    //loads data into model

                    //displays the data table on the screen
                    loadDataScrollPane.setViewportView(dataTable);
                    dataTable.setModel(model);
                    dataTable.revalidate();
                    dataTable.repaint();
                }
            }
            public void loadData(DefaultTableModel theModel)
            {
                //iterates through the list and loads data into the model
                for(int i = 0; i < list.size(); i++)
                {
                    String row = list.get(i);
                    String[] rowVals = row.split(",");
                    if(i == 0){
                        theModel.setColumnIdentifiers(rowVals);
                        continue;
                    }
                    theModel.addRow(rowVals);
                }
            }
        });

        saveDataButton.addActionListener(new ActionListener() {     //executes code if button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Only CSV files","csv");
                chooser.setFileFilter(filter);

                int ret = chooser.showSaveDialog(null);
                if(ret == JFileChooser.APPROVE_OPTION)
                {
                    data.saveData(chooser.getSelectedFile());
                }
            }
        });

        addDataButton.addActionListener(new ActionListener() {     //executes code if button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataPane.setVisible(false);
                addDataPane.setVisible(true);
                aboutPane.setVisible(false);
                visualizeDataPane.setVisible(false);

            }
        });

        aboutButton.addActionListener(new ActionListener() {     //executes code if button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataPane.setVisible(false);
                addDataPane.setVisible(false);
                aboutPane.setVisible(true);
                visualizeDataPane.setVisible(false);
            }
        });

        visualizeDataButton.addActionListener(new ActionListener() {     //executes code if button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataPane.setVisible(false);
                addDataPane.setVisible(false);
                aboutPane.setVisible(false);
                visualizeDataPane.setVisible(true);
            }
        });

        submitButton.addActionListener(new ActionListener() {     //executes code if button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                //gathers fields from text boxes
                String date = dateTextField.getText().toString();
                String id = idTextField.getText().toString();
                String lastName = lastNameTextField.getText().toString();
                String firstName = firstNameTextField.getText().toString();
                String vaccineType = vaccineTypeTextField.getText().toString();
                String locaction = locationTextField.getText().toString();

                //checks if all textboxes are full
                boolean docIsFull = !date.equals("") && !id.equals("") && !lastName.equals("") && !firstName.equals("")
                        && !vaccineType.equals("") && !locaction.equals("");
                if(docIsFull)
                {
                    data.addData(id,lastName,firstName,vaccineType,locaction,date); //adds the data to the list

                    //removes text
                    dateTextField.setText("");
                    idTextField.setText("");
                    lastNameTextField.setText("");
                    firstNameTextField.setText("");
                    vaccineTypeTextField.setText("");
                    locationTextField.setText("");

                    JOptionPane.showMessageDialog(addDataPane,"Success!");
                }
                else
                {
                   JOptionPane.showMessageDialog(addDataPane,"You must fill all fields in order to add data.");
                }
            }
        });
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("App");                       //sets new frame as a JFrame called "App"
        frame.setContentPane(new App().ThePanel);                   //sets the content of the pane to the panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //Closes the Jframe if the user presses the X in the corner
        frame.pack();                                               //packs the frame
        frame.setMinimumSize(new Dimension(870, 500));  //sets the default minimum dimensions of the window
        frame.setVisible(true);                                     //makes the frame visible
    }


}