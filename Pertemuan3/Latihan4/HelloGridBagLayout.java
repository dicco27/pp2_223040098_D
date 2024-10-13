package Pertemuan3.Latihan4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloGridBagLayout extends JFrame {
    public HelloGridBagLayout() {
        // Set default close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a label for the header
        JLabel headerLabel = new JLabel("Layout in action: GridBagLayout", JLabel.CENTER);

        // Create a control panel with FlowLayout
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Create a panel for the buttons and set its background
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setPreferredSize(new Dimension(300, 300));

        // Set GridBagLayout for the panel
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        
        // Create GridBagConstraints for the buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add Button 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new Button("Button 1"), gbc);

        // Add Button 2
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new Button("Button 2"), gbc);

        // Add Button 3
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 20; // increase internal padding on the Y axis
        panel.add(new Button("Button 3"), gbc);

        // Add Button 4
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new Button("Button 4"), gbc);

        // Add Button 5, which spans two columns
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new Button("Button 5"), gbc);

        // Add panel to controlPanel
        controlPanel.add(panel);

        // Set layout for the frame and add components
        this.setLayout(new GridLayout(2, 1));
        this.add(headerLabel);
        this.add(controlPanel);

        // Set frame size
        this.setSize(400, 400);
    }

    // Main method to run the application
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloGridBagLayout h = new HelloGridBagLayout();
                h.setVisible(true);
            }
        });
    }
}

