package kitri.foodCourt.user.member;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoginMain extends JFrame {
	
	JPanel contentPane;
	Login panLogin = new Login(this);
	Join panJoin = new Join(this);

	JPanel panMain = new JPanel();
	CardLayout card = new CardLayout();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMain frame = new LoginMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	FindIdMain findIdMain = new FindIdMain(this);
	FindPwMain findPwMain = new FindPwMain(this);
	LoginControl loginControl;

	
	public LoginMain() {
		this.loginControl = new LoginControl(this);

		this.contentPane = new JPanel();
		this.contentPane.setBorder(new javax.swing.border.EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new java.awt.BorderLayout(0, 0));
		this.contentPane.add(this.panMain);
		setDefaultCloseOperation(3);
		setContentPane(this.contentPane);

		this.panMain.setLayout(this.card);
		setBounds(100, 100, 1200, 800);
		this.panMain.add(this.panLogin, "panLogin");
		this.panLogin.setLayout(null);
		this.panMain.add(this.panJoin, "panJoin");
		this.panMain.setBounds(0, 0, 1200, 800);
		this.card.show(this.panMain, "panLogin");
		setVisible(true);
		
//		�α��� ������
		this.panLogin.joinbtn.addActionListener(this.loginControl);
		this.panLogin.findidbtn.addActionListener(this.loginControl);
		this.panLogin.findpwbtn.addActionListener(this.loginControl);
		this.panLogin.loginbtn.addActionListener(this.loginControl);

		
//		���̵� ã�� ������
		this.findIdMain.findId.nextbtn.addActionListener(this.loginControl);
		this.findIdMain.findId.cancelbtn.addActionListener(this.loginControl);
		this.findIdMain.findIdCheck.Confirm.addActionListener(this.loginControl);
		
//		��й�ȣ ã�� ������
		findPwMain.findPw.nextbtn.addActionListener(this.loginControl);
		findPwMain.findPw.cancelbtn.addActionListener(this.loginControl);
		findPwMain.findPw.idfindbtn.addActionListener(this.loginControl);
		
//		-----------------------------------------------------------------------------------------
		this.findPwMain.findPwCheck.nextbtn.addActionListener(this.loginControl);
		this.findPwMain.findPwCheck.cancelbtn.addActionListener(this.loginControl);
//		-----------------------------------------------------------------------------------------
		findPwMain.findRPw.cancelbtn.addActionListener(this.loginControl);
		findPwMain.findRPw.confirmbtn.addActionListener(this.loginControl);
		findPwMain.findRPw.againPwTF.addKeyListener(loginControl);
		findPwMain.findRPw.pwTF.addKeyListener(loginControl);
		
//		ȸ������ ������
		panJoin.cancelbtn.addActionListener(loginControl);
		panJoin.doublechekbtn.addActionListener(loginControl);
		panJoin.pwTextFD.addKeyListener(loginControl);
		panJoin.pwCheckFD.addKeyListener(loginControl);
		panJoin.comboBox.addActionListener(loginControl);
		panJoin.signupbtn.addActionListener(loginControl);
		panJoin.questionTF.addActionListener(loginControl);
		panJoin.answerTF.addActionListener(loginControl);
		
	}
}
