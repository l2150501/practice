package practice3;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;


class MyJFrame extends JFrame implements ActionListener {

	private JLabel[] jlbl = new JLabel[3];


	private ImageIcon[] icons = new ImageIcon[4];


	private JLabel jlblSum, jlblBetting;

	private JTextField jtxtBetting;

	private JButton jbtnOk;
	private int sum = 100;
	private int highest_score;
	String myFile = "NBA_Bar_High_Score.txt";


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



		jbtnOk.addActionListener(this);


		setSize(640, 500);

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		setLocationRelativeTo(null);

		setResizable(false);

		read_highest_score();
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
									write_a_file(sum);


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
									write_a_file(sum);
								}
								jbtnOk.setEnabled(true);
							}
						});
		t.start();
	}

	public void read_highest_score() {
		try {
			File myObj = new File(myFile);
			Scanner myReader = new Scanner(myObj);
			highest_score = myReader.nextInt();
			myReader.close();
		}catch(FileNotFoundException e){
			highest_score = 0;
			try {
				File myObj = new File(myFile);
				if (myObj.createNewFile()){
					System.out.println("File created: " + myObj.getName());
				}
				FileWriter myWriter = new FileWriter(myObj.getName());
				myWriter.write("" + 0);
			}catch (IOException err){
				System.out.println("An error occurred");
				err.printStackTrace();
			}

		}
	}

	public void write_a_file(int sum){
		try {
			FileWriter myWriter = new FileWriter(myFile);
			if (sum > highest_score){
				myWriter.write("" + sum);
				highest_score = sum;
			}else {
				myWriter.write("" + highest_score);
			}
			myWriter.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}


}

public class part3 {
	public static void main(String[] args){
		new MyJFrame();

			}
}