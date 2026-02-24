
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import Bloodbank.DBConnection;


public class BloodBankApp extends JFrame {

    JTextField nameField, ageField, groupField, phoneField, cityField, unitsField;
    DefaultTableModel model;
    JTable table;

    public BloodBankApp() {

        setTitle("Blood Bank Management System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 228, 225));
        setLayout(null);

        JLabel title = new JLabel("BLOOD BANK MANAGEMENT ");
        title.setBounds(300, 10, 400, 30);
        title.setFont(new Font("Times new roman", Font.BOLD, 22));
        title.setForeground(new Color(153,0,0));
        add(title);

        // Labels and TextFields

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 60, 100, 25);
        nameLabel.setFont(new Font("Times new roman", Font.BOLD, 18));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 60, 150, 25);
        add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 100, 100, 25);
        ageLabel.setFont(new Font("Times new roman", Font.BOLD, 18));
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(150, 100, 150, 25);
        add(ageField);

        JLabel groupLabel = new JLabel("Blood Group:");
        groupLabel.setBounds(50, 140, 100, 25);
        groupLabel.setFont(new Font("Times new roman", Font.BOLD, 18));
        add(groupLabel);

        groupField = new JTextField();
        groupField.setBounds(150, 140, 150, 25);
        add(groupField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(350, 60, 100, 25);
        phoneLabel.setFont(new Font("Times new roman", Font.BOLD, 18));
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(450, 60, 150, 25);
        add(phoneField);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(350, 100, 100, 25);
        cityLabel.setFont(new Font("Times new roman", Font.BOLD, 18));
        add(cityLabel);

        cityField = new JTextField();
        cityField.setBounds(450, 100, 150, 25);
        add(cityField);

        JLabel unitsLabel = new JLabel("Units:");
        unitsLabel.setBounds(350, 140, 100, 25);
        unitsLabel.setFont(new Font("Times new roman", Font.BOLD, 18));
        add(unitsLabel);

        unitsField = new JTextField();
        unitsField.setBounds(450, 140, 150, 25);
        add(unitsField);



        // Buttons

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(200, 200, 100, 30);
        addBtn.setBackground(new Color(0,100,0));
        addBtn.setForeground(Color.white);
        add(addBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(320, 200, 100, 30);
        deleteBtn.setBackground(new Color(153,0,0));
        deleteBtn.setForeground(Color.white);
        add(deleteBtn);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(440, 200, 100, 30);
        clearBtn.setBackground(new Color(255,153,0));
        clearBtn.setForeground(Color.white);
        add(clearBtn);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(560, 200, 100, 30);
        updateBtn.setBackground(new Color(0,0,153));
        updateBtn.setForeground(Color.WHITE);
        add(updateBtn);

        // Table

        String[] columns = {"Name", "Age", "Blood Group", "Phone", "City", "Units"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.getTableHeader().setFont(new Font ("Times new roman" ,Font.BOLD , 18));
        table.setFont(new Font ("Times new roman", Font.PLAIN , 14 ));

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50, 250, 800, 250);
        pane.setFont(new Font("Times new roman", Font.BOLD, 18));
        add(pane);

        // Add Button Action

       addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String group = groupField.getText();
                String phone = phoneField.getText();
                String city = cityField.getText();
                int units = Integer.parseInt(unitsField.getText());

                try {
                    Connection con = DBConnection.getConnection();

                    String sql = "INSERT INTO donors VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setString(1, name);
                    ps.setInt(2, age);
                    ps.setString(3, group);
                    ps.setString(4, phone);
                    ps.setString(5, city);
                    ps.setInt(6, units);

                    int result = ps.executeUpdate();

                    if (result > 0) {
                        model.addRow(new Object[]{name, age, group, phone, city, units});
                        JOptionPane.showMessageDialog(null, "Donor Added Successfully!");
                    }

                    con.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error inserting data");
                }
            }
        });

        // Delete Button Action

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete");
                }
            }
        });

        // Clear Button

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                ageField.setText("");
                groupField.setText("");
                phoneField.setText("");
                cityField.setText("");
                unitsField.setText("");

            }
        });

        // update button

        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int selectedRow = table.getSelectedRow();

                if (selectedRow >= 0) {

                    // Get updated values from text fields
                    String name = nameField.getText();
                    String age = ageField.getText();
                    String group = groupField.getText();
                    String phone = phoneField.getText();
                    String city = cityField.getText();
                    String units = unitsField.getText();

                    // Set updated values into table
                    model.setValueAt(name, selectedRow, 0);
                    model.setValueAt(age, selectedRow, 1);
                    model.setValueAt(group, selectedRow, 2);
                    model.setValueAt(phone, selectedRow, 3);
                    model.setValueAt(city, selectedRow, 4);
                    model.setValueAt(units, selectedRow, 5);

                    JOptionPane.showMessageDialog(null, "Donor Updated Successfully!");

                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to update");
                }
            }
        });
    }

    public static void main(String[] args) {
        new BloodBankApp().setVisible(true);
    }
}