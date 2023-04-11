package practice5;

import practice5.Snake_Game.Main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Index extends JFrame {

  private static final long serialVersionUID = 1L;
  private JButton btn1, btn2, btn3;
  private JTable table;
  private Main main;

  public Index() {
    super("Project Demo");

    btn1 = new JButton("NBA轉轉轉~~");
    btn1.setBounds(230, 80, 200, 40);
    btn1.setFont(new Font("微軟中黑體", Font.PLAIN, 21));
    add(btn1);

    btn2 = new JButton("MSSQLExample");
    btn2.setBounds(230, 140, 200, 40);
    btn2.setFont(new Font("微軟中黑體", Font.PLAIN, 21));
    add(btn2);

    btn3 = new JButton("Snake_Game");
    btn3.setBounds(230, 200, 200, 40);
    btn3.setFont(new Font("微軟中黑體", Font.PLAIN, 21));
    add(btn3);


    // UI elements
    JPanel panel = new JPanel();
    panel.add(btn1);
    panel.add(btn2);
    panel.add(btn3);


    table = new JTable();
    add(new JScrollPane(table), BorderLayout.CENTER);
    add(panel);


    btn1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new MyJFrame();
        dispose();
      }
    });

    btn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new MSSQLExample();
        dispose();
      }
    });

    btn3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

      }
    });


    // Set window properties
    setSize(680, 580);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }


  public static void main(String[] args) {
    new Index();
  }
}
