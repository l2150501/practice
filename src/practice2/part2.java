package practice2;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Scanner;


class MyJFrame extends JFrame implements ActionListener {

  private JLabel[] jlbl = new JLabel[3];


  private ImageIcon[] icons = new ImageIcon[4];


  private JLabel jlblSum, jlblBetting, lblId, lblPwd, lblLogin;

  private JTextField jtxtBetting, txtId, txtPwd;

  private JButton jbtnOk, btnLogin;
  private int sum = 100;


  MyJFrame()
  {

    super.setLayout(null);

    super.setTitle("NBA轉轉轉~~~~");

    for (int i=0; i< icons.length; i++){
      icons[i] = new ImageIcon(".\\part1Img\\" + String.valueOf(i) + ".jpg");
    }

    for (int i=0; i<jlbl.length; i++){
      jlbl[i] = new JLabel();
      jlbl[i].setBounds(i*200+22, 10, 180, 180);
      jlbl[i].setIcon(icons[0]);
      add(jlbl[i]);
    }




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
    lblLogin.setFont(new Font("微軟中黑體", Font.PLAIN, 25));
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






    jlblSum = new JLabel("總金額:" + String.valueOf(sum));

    jlblSum.setBounds(10, 420, 280, 32);
    jlblSum.setFont(new Font("微軟中黑體", Font.PLAIN, 30));
    add(jlblSum);

    jlblBetting = new JLabel("輸入的金額:");
    jlblBetting.setBounds(325, 420, 200, 32);
    jlblBetting.setFont(new Font("微軟中黑體", Font.PLAIN, 30));
    add(jlblBetting);

    jtxtBetting = new JTextField();
    jtxtBetting.setBounds(495, 420, 120, 35);
    jtxtBetting.setFont(new Font("微軟中黑體", Font.PLAIN, 30));
    add(jtxtBetting);

    jbtnOk = new JButton("下注請按");
    jbtnOk.setBounds(430, 350, 170, 40);
    jbtnOk.setFont(new Font("微軟中黑體", Font.PLAIN, 28));
    add(jbtnOk);



    jbtnOk.addActionListener(this::actionPerformed);

    btnLogin.addActionListener(this::actionPerformed1);


    setSize(640, 500);

    setVisible(true);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }


  public void actionPerformed(ActionEvent evt){


    Thread t = new Thread(
            new Runnable() {

              public void run() {


                int k=0, kind=-1;

                int[] n = new int[jlbl.length];
                int betting=0;
                try {

                  if (sum==0){
                    JOptionPane.showMessageDialog(null, "總金額為0元!!");
                    System.exit(0);
                  }

                  betting = Integer.parseInt(jtxtBetting.getText());

                  if (sum<betting || betting<=0){
                    JOptionPane.showMessageDialog(null, "輸入金額必須少於總金額");
                    return;
                  }
                  sum-=betting;
                  jlblSum.setText("剩餘金額:" + String.valueOf(sum));


                  jbtnOk.setEnabled(false);
                }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, "必須輸入下注金額:");
                  return;
                }
                try {
                  do {


                    for (int i=0; i<jlbl.length; i++){
                      n[i]=(int)Math.round(Math.random()*3);
                      jlbl[i].setIcon(icons[n[i]]);
                    }
                    k++;

                    Thread.currentThread().sleep(100);
                  }while (k<10);
                }catch(InterruptedException ex){ }

                if (n[0]==1 && n[1]==2 && n[2]==3){
                  kind = 5;
                }else if (n[0]==3 && n[1]==2 && n[2]==1){
                  kind = 10;
                }else if (n[0]==0 && n[1]==0 && n[2]==0){
                  kind = 30;
                }else if (n[0]==1 && n[1]==1 && n[2]==1){
                  kind = 50;
                }else if (n[0]==2 && n[1]==2 && n[2]==2){
                  kind = 80;
                }else if (n[0]==3 && n[1]==3 && n[2]==3){
                  kind = 100;
                }

                if (kind!=-1){
                  JOptionPane.showMessageDialog(null, "中獎得" + String.valueOf(kind) + "倍");

                  sum += kind*betting;
                  jlblSum.setText("總數量:" + String.valueOf(sum));
                }
                jbtnOk.setEnabled(true);
              }
            });
    t.start();
  }



 public void actionPerformed1(ActionEvent e){

   Thread s = new Thread(
           new Runnable() {

             public void run() {

               if (txtId.getText().equals("shit") && txtPwd.getText().equals("0812")) {
                 int isOk = JOptionPane.showConfirmDialog(null, "你已年滿18歲 ?", "登入作業", JOptionPane.YES_NO_OPTION,
                         JOptionPane.INFORMATION_MESSAGE);
                 if (isOk == 1){
                   JOptionPane.showMessageDialog(null, "滾~~~~!!");
                   return;
                 }
                 try {
                   Runtime runtime = Runtime.getRuntime();
                   Process process = runtime.exec("C:\\Program Files"
                           + "\\Google\\Chrome\\Application"
                           + "\\chrome.exe https://www.instagram.com/tfn_lee/");
                 } catch (Exception ex) {
                 }
               } else {
                 JOptionPane.showMessageDialog(null, "帳密錯誤", "登入作業", JOptionPane.ERROR_MESSAGE);
               }


             }
           });
   s.start();


 }




}

public class part2 {

  //JDBC資料庫連結
  static String cnstr="jdbc:sqlserver://localhost;user=sa;password=0812;database=sample";


  static void showProduct(){
    try{
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }catch(ClassNotFoundException ce){
      System.out.println("JDBC沒有驅動程式" + ce.getMessage());
      return ;
    }
    try{
      Connection cn=DriverManager.getConnection(cnstr);
      Statement sm = cn.createStatement();
      ResultSet rs = sm.executeQuery("SELECT * FROM 產品");
      ResultSetMetaData rsmd = rs.getMetaData();
      for (int i=1; i<=rsmd.getColumnCount(); i++){
        System.out.print(rsmd.getColumnName(i) + "\t");
      }
      System.out.println("\n-----------------------------");
      while (rs.next()){
        System.out.print(rs.getString("編號") + "\t" +
                rs.getString("品名") + "\t"+
                rs.getInt("單價") + "\t" +
                rs.getInt("數量") + "\n");
      }
      sm.close();
      cn.close();
    }catch(SQLException e){
      System.out.println("資料庫連接失敗\n" + e.getMessage());
    }
  }

  //依據代入的SQL語法編輯產品記錄
  static void editProduct(String sqlstr){
    try{
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }catch(ClassNotFoundException ce){
      System.out.println("JDBC沒有驅動程式" + ce.getMessage());
      return ;
    }
    try{
      Connection cn=DriverManager.getConnection(cnstr);
      Statement sm = cn.createStatement();
      int count=sm.executeUpdate(sqlstr);
      sm.close();
      cn.close();
      if (count==0) {
        System.out.println("產品編輯失敗\n");
      }else {
        System.out.println("產品編輯成功\n");
      }
    }catch(SQLException e){
      System.out.println("資料庫連接失敗\n" + e.getMessage());
    }
  }

  //顯示功能選項
  static void showMenu() {
    System.out.println("=====產品管理=====");
    System.out.println("1. 新增產品");
    System.out.println("2. 刪除產品");
    System.out.println("3. 修改產品");
    System.out.println("4. 顯示產品所有記錄");
    System.out.println("5. 離開");
    System.out.print("請輸入選項：");
  }



  public static void main(String[] args){

    new MyJFrame();



    //MSSQL主程式
    Scanner scn = new Scanner(System.in);
    String sqlstr, id, name;
    int op, price, qty;
    while(true) {
      showMenu();
      op = scn.nextInt();
      System.out.println("#############################");
      if (op==1) {	//新增作業
        System.out.println("請輸入欲新增的產品記錄");
        System.out.print("編號：");
        id = scn.next().replace("'", "''");
        System.out.print("品名：");
        name = scn.next().replace("'", "''");
        System.out.print("單價：");
        price = scn.nextInt();
        System.out.print("數量：");
        qty = scn.nextInt();
        sqlstr = "INSERT INTO 產品(編號,品名,單價,數量)Values('" + id + "','" + name + "'," + price +"," + qty + ")";
        editProduct(sqlstr);
      }else if(op==2) {	//刪除作業
        System.out.println("請輸入欲刪除的產品編號");
        System.out.print("編號：");
        id = scn.next().replace("'", "''");
        sqlstr = "DELETE FROM 產品 WHERE 編號='" + id + "'" ;
        editProduct(sqlstr);
      }else if(op==3) {	//修改作業
        System.out.println("請輸入欲修改的產品記錄");
        System.out.print("編號：");
        id = scn.next().replace("'", "''");
        System.out.print("品名：");
        name = scn.next().replace("'", "''");
        System.out.print("單價：");
        price = scn.nextInt();
        System.out.print("數量：");
        qty = scn.nextInt();
        sqlstr = "UPDATE 產品 SET 品名='" + name + "',單價=" +  price + ",數量=" + qty + " WHERE 編號='" + id + "'" ;
        editProduct(sqlstr);
      }else if(op==4) {	//顯示產品記錄
        showProduct();
      }else {
        System.out.println("離開系統");
        break ;
      }
      System.out.println("#############################");
    }







  }
}