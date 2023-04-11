package practice5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Login extends JFrame {
  private JPanel contentPane;
  private JTextField txtId, txtPwd;
  private JLabel lblId, lblPwd, lblLogin;
  private JButton btnLogin;

  Login() {

    super.setTitle("登入系統");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 480, 480);
    contentPane = new JPanel();
    setContentPane(contentPane);
    contentPane.setLayout(null);

    txtId = new JTextField();
    txtId.setToolTipText("請輸入帳號");
    txtId.setColumns(20);
    txtId.setBounds(130, 250, 120, 27);
    txtId.setFont(new Font("微軟中黑體", Font.PLAIN, 25));
    add(txtId);

    txtPwd = new JTextField();
    txtPwd.setToolTipText("請輸入密碼");
    txtPwd.setColumns(20);
    txtPwd.setBounds(130, 290, 120, 27);
    txtPwd.setFont(new Font("微軟中黑體", Font.PLAIN, 25));
    add(txtPwd);

    lblLogin = new JLabel("成人專區");
    lblLogin.setBounds(100, 210, 100, 27);
    lblLogin.setFont(new Font("正楷體", Font.PLAIN, 25));
    add(lblLogin);

    lblId = new JLabel("帳號：");
    lblId.setBounds(50, 247, 100, 27);
    lblId.setFont(new Font("微軟中黑體", Font.PLAIN, 25));
    add(lblId);

    lblPwd = new JLabel("密碼：");
    lblPwd.setBounds(50, 287, 100, 27);
    lblPwd.setFont(new Font("微軟中黑體", Font.PLAIN, 25));
    add(lblPwd);

    btnLogin = new JButton("登入");
    btnLogin.setBounds(50, 340, 80, 27);
    btnLogin.setFont(new Font("微軟中黑體", Font.PLAIN, 21));
    add(btnLogin);
    btnLogin.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (txtId.getText().equals("shit") && txtPwd.getText().equals("0812")) {
          int isOk = JOptionPane.showConfirmDialog(null, "你已年滿18歲", "登入作業", JOptionPane.YES_NO_OPTION,
                  JOptionPane.INFORMATION_MESSAGE);
          if (isOk == 1){
            JOptionPane.showMessageDialog(null, "滾~~~~!!");
            System.exit(0);
          }
          // 按下 [否] 離開
          new Index();
          dispose();  //有別於setVisible
        } else {
          JOptionPane.showMessageDialog(null, "帳密錯誤", "登入作業", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    setVisible(true);
    setLocationRelativeTo(null);
    setResizable(false);
  }
}

public class part4 {
  public static void main(String[] args) {
    Login f = new Login();
  }
}
