package com.codebind;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.CategoryToPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class visualizeDataInit extends JFrame
{
    //constructor
    public visualizeDataInit(CSVList list, boolean isBarGraph)
    {
        super();
        if(isBarGraph)  //initializes bar graph
        {
            CategoryDataset dataset = (CategoryDataset) createDataset(list,3);
            JFreeChart chart = ChartFactory.createBarChart(
                    "Vaccine Type Distribution",
                    "Vaccine Types",
                    "Number of Vaccinations",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,true,false
            );
            ChartPanel panel = new ChartPanel(chart);
            setContentPane(panel);
        }
        else    //initializes pie chart
        {
            PieDataset dataset = (PieDataset) createDataset(list,5);
            JFreeChart chart = ChartFactory.createPieChart(
                    "Locations where vaccines were administered",
                    dataset,
                    true,true,false
            );
            ChartPanel panel = new ChartPanel(chart);
            setContentPane(panel);
        }
    }
    public Dataset createDataset(CSVList list, int column)
    {
        Hashtable<String,Integer> vaccineTypeCount = new Hashtable<String,Integer>();   //hash table keeping track of table elements
        ArrayList<String> vaccineType = new ArrayList<String>();    //arraylist of all the fields going into the chart
        for(int i = 1; i < list.size();i++)
        {
            String type = list.get(i).split(",")[column];   //column that needs to be tracked
            if(vaccineTypeCount.containsKey(type))
            {
                int val = vaccineTypeCount.get(type);
                val++;
                vaccineTypeCount.replace(type,val);
            }
            else
            {
                vaccineTypeCount.put(type,1);
                vaccineType.add(type);
            }
        }
        if(column == 3) //creates category dataset for bar graph
        {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (String type : vaccineType) {
                int val = vaccineTypeCount.get(type);
                dataset.addValue(val, type, "");
            }
            return dataset;
        }
        else    //creates pie dataset for pie chart
        {
            DefaultPieDataset dataset = new DefaultPieDataset();
            for (String type : vaccineType) {
                int val = vaccineTypeCount.get(type);
                dataset.setValue(type,val);
            }
            return dataset;
        }
    }
}
