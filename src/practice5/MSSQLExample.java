package practice5;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class MSSQLExample extends JFrame {

  private static final long serialVersionUID = 1L;
  private JButton btnAdd, btnDel, btnUpd, btnShow, btnExit;
  private JTable table;

  public MSSQLExample() {
    super("MSSQL Demo");

    btnAdd = new JButton("新增產品");
    btnAdd.setBounds(230, 80, 200, 40);
    btnAdd.setFont(new Font("微軟中黑體", Font.PLAIN, 21));
    add(btnAdd);

    btnDel = new JButton("刪除產品");
    btnDel.setBounds(230, 140, 200, 40);
    btnDel.setFont(new Font("微軟中黑體", Font.PLAIN, 21));
    add(btnDel);

    btnUpd = new JButton("修改產品");
    btnUpd.setBounds(230, 200, 200, 40);
    btnUpd.setFont(new Font("微軟中黑體", Font.PLAIN, 21));
    add(btnUpd);

    btnShow = new JButton("顯示產品紀錄");
    btnShow.setBounds(230, 260, 200, 40);
    btnShow.setFont(new Font("微軟中黑體", Font.PLAIN, 21));
    add(btnShow);

    btnExit = new JButton("離開");
    btnExit.setBounds(230, 320, 200, 40);
    btnExit.setFont(new Font("微軟中黑體", Font.PLAIN, 21));
    add(btnExit);


    // UI elements
    JPanel panel = new JPanel();
    panel.add(btnAdd);
    panel.add(btnDel);
    panel.add(btnUpd);
    panel.add(btnShow);
    panel.add(btnExit);


    table = new JTable();
    add(new JScrollPane(table), BorderLayout.CENTER);
    add(panel);


    btnAdd.addActionListener(new ActionListener() {
      //add
      public void actionPerformed(ActionEvent e) {
        try {
          // Connect to MSSQL database
          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
          Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;database=sample", "sa", "0812");

          // Create a dialog box to get the product information
          JPanel panel = new JPanel(new GridLayout(0, 2));
          panel.add(new JLabel("產品編號:"));
          JTextField txtID = new JTextField();
          panel.add(txtID);
          panel.add(new JLabel("產品名稱:"));
          JTextField txtName = new JTextField();
          panel.add(txtName);
          panel.add(new JLabel("產品單價:"));
          JTextField txtPrice = new JTextField();
          panel.add(txtPrice);
          panel.add(new JLabel("產品數量:"));
          JTextField txtQua = new JTextField();
          panel.add(txtQua);
          int result = JOptionPane.showConfirmDialog(null, panel, "新增產品", JOptionPane.OK_CANCEL_OPTION);
          if (result == JOptionPane.OK_OPTION) {
            // Insert new record into database
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO 產品(編號, 品名, 單價, 數量) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, txtID.getText());
            pstmt.setString(2, txtName.getText());
            pstmt.setInt(3, Integer.parseInt(txtPrice.getText()));
            pstmt.setInt(4, Integer.parseInt(txtQua.getText()));
            pstmt.executeUpdate();
            pstmt.close();
            JOptionPane.showMessageDialog(null, "產品已成功新增");
          }

          conn.close();

        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    btnDel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // show
        btnShow.doClick();

        // delete
        int row = table.getSelectedRow();
        if (row == -1) {
          JOptionPane.showMessageDialog(null, "請選擇要刪除的產品");
          return ;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "確定要刪除此產品嗎？", "刪除產品", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;database=sample", "sa", "0812");
            Statement stmt = conn.createStatement();
            String productId = table.getModel().getValueAt(row, 0).toString();
            int result = stmt.executeUpdate("DELETE FROM 產品 WHERE 編號 = '" + productId + "'");
            if (result == 1) {
              JOptionPane.showMessageDialog(null, "產品已成功刪除");
            } else {
              JOptionPane.showMessageDialog(null, "產品刪除失敗");
            }

            stmt.close();
            conn.close();
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }else {
          JOptionPane.showMessageDialog(null, "產品刪除失敗");
        }
      }
    });

    btnUpd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //show
        btnShow.doClick();

        //upgrade
        int selectedRowIndex = table.getSelectedRow();

        if (selectedRowIndex != -1) {
          try {
            // Get product id from selected row
            String productId = table.getValueAt(selectedRowIndex, 0).toString();

            // Get new product name and price from user
            String newProductName = JOptionPane.showInputDialog(null, "輸入新的產品名稱:", "編輯產品",JOptionPane.OK_CANCEL_OPTION);
            String newProductPrice = JOptionPane.showInputDialog(null, "輸入新的產品價格:", "編輯產品",JOptionPane.OK_CANCEL_OPTION);
            String newProductQua = JOptionPane.showInputDialog(null, "輸入產品數量:", "編輯產品",JOptionPane.OK_CANCEL_OPTION);

            if (newProductName == null && newProductPrice == null && newProductQua == null){
              JOptionPane.showMessageDialog(null, "請再次選擇要修改的產品");
              return;
            }

            // Connect to MSSQL database
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;database=sample", "sa", "0812");

            // Update product in database
            PreparedStatement stmt = conn.prepareStatement("UPDATE 產品 SET 品名 = ?, 單價 = ?, 數量 = ? WHERE 編號 = ?");
            stmt.setString(1, newProductName);
            stmt.setDouble(2, Double.parseDouble(newProductPrice));
            stmt.setDouble(3, Double.parseDouble(newProductQua));
            stmt.setString(4, productId);
            stmt.executeUpdate();

            // Show success message
            JOptionPane.showMessageDialog(null, "產品已成功更新");

            // Close connections
            stmt.close();
            conn.close();

            // Refresh table
            btnShow.doClick();

          } catch (Exception ex) {
            ex.printStackTrace();
          }
        } else {
          // Show error message if no row is selected
          JOptionPane.showMessageDialog(null, "請選擇要修改的產品");
        }
      }
    });

    btnShow.addActionListener(new ActionListener() {
      //show
      public void actionPerformed(ActionEvent e) {
        // Connect to MSSQL database
        try {
          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
          Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;database=sample", "sa", "0812");
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT * FROM 產品");

          // Create table model
          ResultSetTableModel model = new ResultSetTableModel(rs);

          // Show table in dialog
          table = new JTable(model);
          JOptionPane.showMessageDialog(null, new JScrollPane(table));

          // Close connections
          rs.close();
          stmt.close();
          conn.close();

        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    btnExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    // Set window properties
    setSize(680, 580);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }


  public static void main(String[] args) {
    new MSSQLExample();
  }
}
