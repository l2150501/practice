package practice;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class MyJFrame extends JFrame implements ActionListener {

  private JLabel[] jlbl = new JLabel[3];


  private ImageIcon[] icons = new ImageIcon[4];


  private JLabel jlblSum, jlblBetting;

  private JTextField jtxtBetting;

  private JButton jbtnOk;
  private int sum = 50;

  MyJFrame()
  {

    super.setLayout(null);

    super.setTitle("吃角子老虎機");

    for (int i=0; i< icons.length; i++){
      icons[i] = new ImageIcon(".\\part1Img\\" + String.valueOf(i) + ".jpg");
    }

    for (int i=0; i< jlbl.length; i++){
      jlbl[i] = new JLabel();
      jlbl[i].setBounds(i*100+10, 10, 86, 86);
      jlbl[i].setIcon(icons[0]);
      add(jlbl[i]);
    }

    jlblSum = new JLabel("總數量：" + String.valueOf(sum));
    // 設定jlblSum標籤x座標10, y座標120, 寬160, 高20
    jlblSum.setBounds(10, 120, 160, 20);
    jlblSum.setFont(new Font("微軟中黑體",Font.PLAIN, 18));
    add(jlblSum);

    jlblBetting = new JLabel("投注量：");
    jlblBetting.setBounds(160, 120, 80, 20);
    jlblBetting.setFont(new Font("微軟中黑體",Font.PLAIN, 18));
    add(jlblBetting);

    jtxtBetting = new JTextField();
    jtxtBetting.setBounds(240, 120, 50, 25);
    jtxtBetting.setFont(new Font("微軟中黑體",Font.PLAIN, 18));
    add(jtxtBetting);

    jbtnOk = new JButton("下注");
    jbtnOk.setBounds(10, 160, 80, 30);
    jbtnOk.setFont(new Font("微軟中黑體",Font.PLAIN, 18));
    add(jbtnOk);



    jbtnOk.addActionListener(this);


    setSize(320, 250);

    setVisible(true);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }


  public void actionPerformed(ActionEvent evt) {




    Thread t = new Thread(
            new Runnable() {

              public void run() {


                int k=0, kind=-1;

                int[] n = new int[jlbl.length];
                int betting=0;
                try {

                  if (sum==0){
                    JOptionPane.showMessageDialog(null, "好遜~~ 玩到破產就滾吧!!");
                    System.exit(0);
                  }

                  betting = Integer.parseInt(jtxtBetting.getText());

                  if (sum<betting || betting<=0){
                    JOptionPane.showMessageDialog(null, "沒錢了!!");
                    return;
                  }
                  sum-=betting;
                  jlblSum.setText("總數量:" + String.valueOf(sum));


                  jbtnOk.setEnabled(false);
                }catch (Exception ex){
                  JOptionPane.showMessageDialog(null, "請輸入下注的金額:");
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
                }catch (InterruptedException ex){ }

                if (n[0]==0 && n[1]==0 && n[2]==0){
                  kind = 3;
                }else if(n[0]==1 && n[1]==1 && n[2]==1){
                  kind = 10;
                }else if(n[0]==2 && n[1]==2 && n[2]==2){
                  kind = 20;
                }else if(n[0]==3 && n[1]==3 && n[2]==3){
                  kind = 50;
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
}

public class part1 {
  public static void main(String[] args){
    // 建立MyJFrame視窗(拉霸遊戲)
    new MyJFrame();
  }
}