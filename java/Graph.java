/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mm;

/**
 *
 * @author trisha deshmukh
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.sql.*;

public class Graph {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Amount Distribution by Category");
            frame.setSize(800, 600);
           frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    frame.dispose();  // Dispose the frame but keep the application running
                }
            });
            
            
            // Fetch data and create chart
            PieDataset dataset = getDatasetFromDatabase();
            JFreeChart chart = ChartFactory.createPieChart(
                "Amount Distribution by Category",  // Chart title
                dataset,                            // Data
                true,                               // Include legend
                true,
                false
            );

            // Display the chart in a JPanel
            ChartPanel chartPanel = new ChartPanel(chart);
            frame.add(chartPanel);
            frame.setVisible(true);
        });
    }

    private static PieDataset getDatasetFromDatabase() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        String url = "jdbc:mysql://localhost:3306/mm1";
        String user = "root";
        String password = "trisha2005";
        String query = "SELECT category, SUM(amount) AS total_amount FROM spendings GROUP BY category";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String category = resultSet.getString("category");
                int totalAmount = resultSet.getInt("total_amount");
                dataset.setValue(category, totalAmount);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }
    
}
