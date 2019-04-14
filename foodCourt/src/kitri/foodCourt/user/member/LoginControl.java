package kitri.foodCourt.user.member;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class LoginControl implements ActionListener, KeyListener {
	
	public LoginService loginService;
	public LoginMain loginMain;
	public Login login;
	public Join join;
	public FindIdMain findIdMain;
	public FindId findId;
	public FindIdCheck findIdCheck;
	public FindPwMain findPwMain;
	public FindPw findPw;
	public FindPwCheck findPwCheck;
	public FindRPw findRPw;
//	public DBConnection dbConnection;

	public LoginControl(LoginMain loginMain) {
		this.loginMain = loginMain;
		this.login = loginMain.panLogin;
		this.join = loginMain.panJoin;
		this.findIdMain = loginMain.findIdMain;
		this.findId = loginMain.findIdMain.findId;
		this.findIdCheck = loginMain.findIdMain.findIdCheck;
		this.findPwMain = loginMain.findPwMain;
		this.findPw = loginMain.findPwMain.findPw;
		this.findPwCheck = loginMain.findPwMain.findPwCheck;
		this.findRPw = loginMain.findPwMain.findRPw;
		
		this.loginService = new LoginService(this);
//		dbConnection = new DBConnection();
	}

	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
//		�α��� ������
		if (ob == this.login.joinbtn) {
			
			this.loginMain.card.show(this.loginMain.panMain, "panJoin");
			this.login.idtextField.setText("");
			this.login.pwtextfd.setText("");
			
		} else if (ob == this.login.findidbtn) {
			this.findIdMain.setVisible(true);
		} else if (ob == this.login.findpwbtn) {
			this.findPwMain.setVisible(true);
		} else if (ob == this.login.loginbtn) {
			loginService.logJoin();
			
			
			
//		���̵� ã��
		} else if (ob == this.findId.nextbtn) {
			loginService.findid();
			
			
		} else if (ob == this.findId.cancelbtn) {
			findIdMain.setVisible(false);
			findId.middleTF.setText("");
			findId.lastTF.setText("");
			findId.nameTF.setText("");
//		-------------------------------------------------------------------------------------
		} else if (ob == findIdCheck.Confirm) {
			findIdMain.setVisible(false);
			findIdMain.card.show(findIdMain.panMain, "findId");
//			findIdCheck.nameL.setText("");

			
			
			
//		��й�ȣ ã��
		} else if (ob == this.findPw.nextbtn) {
			this.findPwMain.card.show(this.findPwMain.panMain, "findPwCheck");
			this.findPw.idTF.setText("");
		} else if (ob == this.findPw.cancelbtn) {
			this.findPwMain.setVisible(false);
			this.findPwMain.card.show(this.findPwMain.panMain, "findPw");
			this.findPw.idTF.setText("");
		} else if (ob == this.findPw.idfindbtn) {
			this.findPwMain.setVisible(false);
			this.findPw.idTF.setText("");
			this.findPwMain.card.show(this.findPwMain.panMain, "findPw");
			this.findIdMain.card.show(this.findIdMain.panMain, "findId");
			this.findIdMain.setVisible(true);
//		-------------------------------------------------------------------------------------
		} else if (ob == this.findPwCheck.nextbtn) {
			this.findPwCheck.answerTF.setText("");
			this.findPwMain.card.show(this.findPwMain.panMain, "findRPw");
		} else if (ob == this.findPwCheck.cancelbtn) {
			this.findPwMain.setVisible(false);
			this.findPwCheck.answerTF.setText("");
			this.findPwMain.card.show(this.findPwMain.panMain, "findPw");
//		-------------------------------------------------------------------------------------
		} else if (ob == this.findRPw.confirmbtn) {
			this.findPwMain.setVisible(false);
			this.findRPw.againPwTF.setText("");
			this.findRPw.pwTF.setText("");
			this.findPwMain.card.show(this.findPwMain.panMain, "findPw");
		} else if (ob == this.findRPw.cancelbtn) {
			this.findPwMain.setVisible(false);
			this.findRPw.againPwTF.setText("");
			this.findRPw.pwTF.setText("");
			this.findPwMain.card.show(this.findPwMain.panMain, "findPw");

//			public JTextField idTextFD;
//			public JTextField pwTextFD;
//			public JTextField pwCheckFD;
//			public JTextField nameFD;
//			public JTextField phLastFD;
//			public JTextField phMiddleFD;
//			public JButton cancelbtn;
//			public JButton sinupbtn;
//			public JButton doublechekbtn;
//			public JComboBox comboBox;
			
			
//		ȸ������ ������
		} else if (ob == this.loginMain.panJoin.cancelbtn) {
			join.setVisible(false);
			join.idTextFD.setText("");
			join.pwTextFD.setText("");
			join.pwCheckFD.setText("");
			join.nameFD.setText("");
			join.phLastFD.setText("");
			join.phMiddleFD.setText("");
		} else if (ob == this.loginMain.panJoin.doublechekbtn) {
			loginService.doubleCheck();
		} 
//		else if (ob == this.loginMain.panJoin.comboBox) {
//			
//		}
		else if (ob == loginMain.panJoin.signupbtn) {
			loginService.signUp();
		}
	}

	
	@Override
	public void keyReleased(KeyEvent e) { 		
		
		Object ob = e.getSource();
	
//		ȸ������ ������ ��й�ȣ ��ġ Ȯ�� �ʵ�
		if (ob == join.pwCheckFD) {
			loginService.pwCompare();
		}else if (ob == join.pwTextFD) {
			loginService.pwCompare();
		}
		
	}
	
	

	
	
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
	
	

	
}
