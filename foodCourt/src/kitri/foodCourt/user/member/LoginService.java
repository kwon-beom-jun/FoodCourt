package kitri.foodCourt.user.member;
 
import java.awt.Color;
import java.io.PrintStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import kitri.foodCourt.user.User;
 
public class LoginService {
    
	Connection conn = null; // DB����� ����(����)�� ���� ��ü
    PreparedStatement pstm = null;  // SQL ���� ��Ÿ���� ��ü
    ResultSet rs = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
    
	
	LoginControl logc;
//	DBConnection dbConnection;

	int doubleidCheck; // ȸ������ ���������� ���̵� �ߺ�Ȯ���� �������� �ȴ������� �Ǵ��ϱ� ���� ����
	int pwCheck; // ��й�ȣ ��ġ�Ҷ��� ���Լ����ϱ� ���� ����
	String userIdJoin;
	// ȸ�����Թ�ư�� �����ÿ� �ߺ���ư�� ���� ��밡�� ���̵�� �Ǻ��� ���̵�� ���� �� ���̵� �´��� Ȯ��.
	// �ߺ�Ȯ�� ������ �� ��밡���� �� �� �ٽ� ���̵� �ٲٰ� �ߺ�Ȯ���� ������ �ʰ� �����ϴ°��� �����ϱ� ���ؼ� ������ ����.
	// ������ �ȿ� �־���� �Ǵ� ������?? logincontrol�ؿ� �ֵ� �ȉ�� ������??
	// ������ �ȿ� ������ �ν��� �ȵǼ� ���� ������� ��.

	LoginService(LoginControl logc) { 
		doubleidCheck = 0;
		pwCheck = 0;
//		userIdJoin = logc.join.idTextFD.getText();
		this.logc = logc;
//    	dbConnection = new DBConnection();
		
	}

	
    
    public void logJoin() {
    	
    	String quary = "select user_id, password, name, user_point, phone_first, phone_middle, phone_last, password  from fook_user";
    	
//    	logc.login.idtextField.getText();
//    	logc.login.pwtextfd.getPassword();
    	
    	//��ȿ�� ����
		if ((logc.login.idtextField.getText().isEmpty()) || (logc.login.pwtextfd.getPassword().length == 0)) {
			javax.swing.JOptionPane.showMessageDialog(logc.login, "���̵� �Ǵ� ��й�ȣ�� �Է��� �ּ���.");
		}
    	
        try {
        	
        	conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();

//			System.out.println("user_id \t password");
//			System.out.println("=========================");
			
			
			String str = new String(logc.login.pwtextfd.getPassword()); // ��й�ȣ�� ��ǥ�� �߰� ����� �ҽ��� �Ἥ ���� �����ؾ� �� �� ����.
			String user_id;
			String password;
			int checking = 0;
			
			while(rs.next()){
				user_id = rs.getString("user_id");
				password = rs.getString("password");
				
//				Integer.parseInt(password);
				
//				System.out.println(logc.login.pwtextfd.getPassword());
//				System.out.println("�����ͺ��̽� password : " + password + " ���� ģ password  : " + str );
//				System.out.println("��� : " + str);
//				System.out.println(logc.login.idtextField.getText());
//				String result = user_id+" "+password;
//				System.out.println(result);
				
				if (logc.login.idtextField.getText().equals(user_id) && str.equals(password)) {
					javax.swing.JOptionPane.showMessageDialog(logc.login, "�α��� ����.");
					checking += 1;
					//���� 
					User user = new User(user_id, rs.getString("name"), rs.getInt("user_point"));
					user.setPhoneNumberFirst(rs.getString("phone_first"));
					user.setPhoneNumberMiddle(rs.getString("phone_middle"));
					user.setPhoneNumberlast(rs.getString("phone_last"));
					user.setPassword(rs.getString("password"));
					
					System.out.println(user);
				}
			}
			
			
			if (checking == 0) {
				javax.swing.JOptionPane.showMessageDialog(logc.login, "���̵�� ����� �ٸ��ϴ�.");

			}
			
			
			logc.login.idtextField.setText("");
			logc.login.pwtextfd.setText("");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("������ ����");
		} finally{
            // DB ������ �����Ѵ�.
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
        }
        
        
    }
    
    
//    ------------------------------------------------------------------------------------------------
    

    
    public void doubleCheck() {
    	
        String quary = "select user_id from fook_user";
        userIdJoin = logc.join.idTextFD.getText();
        
        if (userIdJoin.isEmpty()) {
        	javax.swing.JOptionPane.showMessageDialog(logc.join, "���̵� �Է� �ٶ��ϴ�.");
		}
  	
	    
        try {
      		conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();


			String DBuser_id;
			
			int check = 0;
			
			while(rs.next()){
				
				DBuser_id = rs.getString("user_id");
				
				if (userIdJoin.equals(DBuser_id)) {
					
					javax.swing.JOptionPane.showMessageDialog(logc.join, "�ߺ��� ���̵� �ֽ��ϴ�.");
					logc.join.idTextFD.setText("");
					
					check = 1;
					doubleidCheck = 1;
				}
			}
			
			if( check == 0 ) {
				javax.swing.JOptionPane.showMessageDialog(logc.join, "��� ������ ���̵� �Դϴ�.");
				doubleidCheck = 2;
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("������ ����");
		} finally{
          // DB ������ �����Ѵ�.
          try{
              if ( rs != null ){rs.close();}   
              if ( pstm != null ){pstm.close();}   
              if ( conn != null ){conn.close(); }
          }catch(Exception e){
              throw new RuntimeException(e.getMessage());
          }
          
		}
        
        
    }
    
//    --------------------------------------------------------------------------------
    
        
    public void pwCompare() {
    	
    	
    	String pwTextFD = new String(logc.join.pwTextFD.getPassword());
    	String pwCheckFD = new String(logc.join.pwCheckFD.getPassword());
    	
    	
    	if (!(pwTextFD.equals(pwCheckFD)) || pwTextFD.isEmpty() || pwCheckFD.isEmpty()) {
				logc.join.pwCheckL.setForeground(Color.RED);
				logc.join.pwCheckL.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				pwCheck = 0;
			}else if (pwTextFD.length() < 6 || pwCheckFD.length() < 6 ) {
				logc.join.pwCheckL.setForeground(Color.RED);
				logc.join.pwCheckL.setText("��й�ȣ�� 6�ڸ� �̻� �Է��� �ּ���.");
				pwCheck = 0;
			}else if (pwTextFD.equals(pwCheckFD)) {
				logc.join.pwCheckL.setForeground(Color.GREEN);			
				logc.join.pwCheckL.setText("��й�ȣ�� ��ġ�մϴ�.");
				pwCheck = 1;
	//    		if (pwTextFD.isEmpty() || pwCheckFD.isEmpty()) {
	//    			logc.join.pwCheckL.setForeground(Color.DARK_GRAY);
	//    			logc.join.pwCheckL.setText("��й�ȣ�� �Է� �ٶ��ϴ�.");
	//    		}
			} 	
    	}
    
    

	public void signUp() {
		
		
		String id = logc.join.idTextFD.getText();
		String pw = new String(logc.join.pwTextFD.getPassword());
		String pwC = new String(logc.join.pwCheckFD.getPassword());
		String name = logc.join.nameFD.getText();
		String phF = logc.join.comboBox.getSelectedItem().toString();
		String phM = logc.join.phMiddleFD.getText();
		String phL = logc.join.phLastFD.getText();
		String question = logc.join.questionTF.getText();
		String answer = logc.join.answerTF.getText();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd");
		String str = dayTime.format(new Date());
		
		String quary = "INSERT INTO fook_user(user_id, password, name, phone_first, "
				+ "phone_middle, phone_last, user_point, password_quiz, password_answer, "
				+ "enable) " + 
				"VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		// doubleidcheck �̿� �ߺ���ư�� �غô��� üũ ����.
		// 0�̸� �ȴ���. 1�̸� �ߺ�. 2�̸� ����.
		
		if ( id.isEmpty() || pw.isEmpty() || pwC.isEmpty()
			|| name.isEmpty() || phM.isEmpty() || phL.isEmpty()) { // == �� �ּҰ� ��.
			JOptionPane.showMessageDialog(logc.join, "�� ������ �Է��� �ּ���.");
		}else if(doubleidCheck == 0 || doubleidCheck == 1) {
			JOptionPane.showMessageDialog(logc.join, "�ߺ� Ȯ���� �����ּ���.");
		}else if ( id.length() > 32) {
			JOptionPane.showMessageDialog(logc.join, "�ѱ� 16�� ���� �Ǵ� ���� 32�� ���Ϸ� ���̵� �ۼ��ϼ���.");
		}else if (!(userIdJoin.equals(id))) {
			JOptionPane.showMessageDialog(logc.join, "�ߺ� Ȯ���� �����ּ���.");
		}else if ( pwCheck == 0) {
			JOptionPane.showMessageDialog(logc.join, "��й�ȣ�� Ȯ���� �ּ���.");
		}else if (name.length() > 32) {
			JOptionPane.showMessageDialog(logc.join, "�ѱ� 16�� ���� �Ǵ� ���� 32�� ���Ϸ� �̸��� �ۼ��ϼ���.");
		}else if (phM.length() != 4 || phL.length() != 4) {
			JOptionPane.showMessageDialog(logc.join, "�޴��� ��ȣ�� ���� 4�ڸ��� �Է��� �ּ���.");
		}
		
		
		try {
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			
			pstm.setString(1, id);
			pstm.setString(2, pw); 
			pstm.setString(3, name);
			pstm.setString(4, phF);
			pstm.setString(5, phM);
			pstm.setString(6, phL);
			pstm.setInt(7, 0);
			pstm.setString(8, question);
			pstm.setString(9, answer);
			pstm.setString(10, "N");

			
			// SQL ������ �����ϰ� ����� ���� - SQL ���� ���� ��, ����� row �� int type ����
			int r = pstm.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}finally { 
			if(pstm != null) { 
				try { 
					pstm.close(); 
				} catch (SQLException e) { 
					e.printStackTrace(); 
				} 
			} 
			if (conn != null) { 
				try { conn.close(); 
			} catch (SQLException e) { 
				e.printStackTrace(); } 
			}
		}
		
	}



	public void findid() {
		
		String quary = "select name, user_id, phone_first, phone_middle, phone_last from fook_user";
        
		String user_name = logc.findId.nameTF.getText();
		String phF = logc.findId.comboBox.getSelectedItem().toString();
		String phM = logc.findId.middleTF.getText();
		String phL = logc.findId.lastTF.getText();
        
        System.out.println(phM.length());
		
        if (phM.length() != 4 || phL.length() != 4) {
        	javax.swing.JOptionPane.showMessageDialog(logc.join, "�ڵ��� ��ȣ�� ����� �Է��� �ּ���.");		
		}else if (user_name.isEmpty()) {
        	javax.swing.JOptionPane.showMessageDialog(logc.join, "���̵� �Է� �ٶ��ϴ�.");
		}else if (phM.isEmpty() || phL.isEmpty()) {
        	javax.swing.JOptionPane.showMessageDialog(logc.join, "�ڵ��� ��ȣ�� �Է� �ٶ��ϴ�.");
		}
        
  	
	    
        try {
      		conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();


			String DBuser_id;
			String DBphF;
			String DBphM;
			String DBphL;
			String Username;
			
			int check = 0;
			
			while(rs.next()){
				
				Username = rs.getString("name");
				DBuser_id = rs.getString("user_id");
				DBphF = rs.getString("phone_first");
				DBphM = rs.getString("phone_middle");
				DBphL = rs.getString("phone_last");
				
				if (user_name.equals(Username) && phF.equals(DBphF) && phM.equals(DBphM) &&
						phL.equals(DBphL)) {
					
					logc.findIdCheck.nameL.setText(DBuser_id);
					logc.findIdMain.card.show(logc.findIdMain.panMain, "findIdCheck");
					logc.findId.middleTF.setText("");
					logc.findId.lastTF.setText("");
					logc.findId.nameTF.setText("");
					check = 1;
				}
				
			}

			
			if (check == 0) {
				javax.swing.JOptionPane.showMessageDialog(logc.join, "��ġ�ϴ� ���̵� �����ϴ�.");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("������ ����");
		} finally{
          // DB ������ �����Ѵ�.
          try{
              if ( rs != null ){rs.close();}   
              if ( pstm != null ){pstm.close();}   
              if ( conn != null ){conn.close(); }
          }catch(Exception e){
              throw new RuntimeException(e.getMessage());
          }
          
		}
		
	}

	String findPwId; // ��й�ȣ  �纯��� �ʿ�
	
	
	
	
	
	
//	��й�ȣ ã�� ������
	public void reSetting() {
		findPwId = null;
	}

	public void findPw() {
		
		String quary = "select user_id, nvl(password_quiz, 'null') \"password_quiz\" , password_answer from fook_user";
		String name = logc.findPw.idTF.getText();
		String passwordA = logc.findPwCheck.answerTF.getText();
		
        
        if (name.isEmpty()) {
        	javax.swing.JOptionPane.showMessageDialog(logc.findPw, "���̵� �Է� �ٶ��ϴ�.");
		}
  	
	    
        try {
      		
        	conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();


			int idC = 0;
			
			while(rs.next()){

				String DBuser_id = rs.getString("user_id");
				String PasswordQ = rs.getString("password_quiz");
				String PasswordA = rs.getString("password_answer");
				
				if (name.equals(DBuser_id)) {
					logc.findPw.idTF.setText("");
					if (PasswordQ.equals("null")) {
						logc.findPwCheck.questionL.setText("���� ����� ���� �ʾҽ��ϴ�.");
						logc.findPwCheck.answerTF.setEditable(false);
						// Ȯ�� ������ �������� �������� �����. findPwId �������� �ʱ�ȭ �����ֱ�
					}else{
						logc.findPwCheck.questionL.setText(PasswordQ);
						findPwId = DBuser_id;
					}
					logc.findPwMain.card.show(logc.findPwMain.panMain, "findPwCheck");
					idC = 1;
				}
			}
			if (idC == 0) {
				javax.swing.JOptionPane.showMessageDialog(logc.findPw, "�ִ� ���̵� �����ϴ�.");					
			}
			
			
			
			
			
        }catch (SQLException e) {
			e.printStackTrace();
			System.out.println("������ ����");
		} finally{
          // DB ������ �����Ѵ�.
          try{
              if ( rs != null ){rs.close();}   
              if ( pstm != null ){pstm.close();}   
              if ( conn != null ){conn.close(); }
          }catch(Exception e){
              throw new RuntimeException(e.getMessage());
          }
          
		}


	}
	
//	----------------------------------------------------------------------------

	
	public void findPwCheck() {
		
		String quary = "select user_id, password_answer from fook_user";
		String answerTF = logc.findPwCheck.answerTF.getText();
		
		try {
			
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();

			while(rs.next()) {
				
				String DBuser_id = rs.getString("user_id");
				String AnswerTF = rs.getString("password_answer");
				
				if (findPwId.equals(DBuser_id)) {
					if (answerTF.equals(AnswerTF)) {
						logc.findPwCheck.answerTF.setText("");
						logc.findPwMain.card.show(logc.findPwMain.panMain, "findRPw");
					}else {
						javax.swing.JOptionPane.showMessageDialog(logc.findPwCheck, "������ ��ġ���� �ʽ��ϴ�.");					
					}
				}

			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

//	----------------------------------------------------------------------------------
	
	int RpwCheck = 0; // Ȯ���ϱ� ��й�ȣ�� ����� ������ �Ǿ�����.
	
	public void Rpwcompare() {
		
		String password = logc.findRPw.pwTF.getText();
		String againpwTF = logc.findRPw.againPwTF.getText();
		
		
    	if (!(password.equals(againpwTF)) || password.isEmpty() || againpwTF.isEmpty()) {
			logc.findRPw.checkL.setForeground(Color.RED);
			logc.findRPw.checkL.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			RpwCheck = 0;
		}else if (password.length() < 6 || againpwTF.length() < 6 ) {
			logc.findRPw.checkL.setForeground(Color.RED);
			logc.findRPw.checkL.setText("��й�ȣ�� 6�ڸ� �̻� �Է��� �ּ���.");
			RpwCheck = 0;
		}else if (password.equals(againpwTF)) {
			logc.findRPw.checkL.setForeground(Color.GREEN);
			logc.join.pwCheckL.setText("��й�ȣ�� ��ġ�մϴ�.");
			RpwCheck = 1;
		} 
    	
	}

	public void findRPw() {

		String quary = "update fook_user set password = ? where user_id = ?";
		String password = logc.findRPw.pwTF.getText();
		String againpwTF = logc.findRPw.againPwTF.getText();
		
		
		try {
			
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			
			if ( RpwCheck == 1 ) {
				pstm.setString(1, password);
				pstm.setString(2, findPwId);
				int rs = pstm.executeUpdate();
				logc.findPwMain.setVisible(false);
				logc.findPwMain.card.show(logc.findPwMain.panMain, "findPw");
				RpwCheck = 0;
				findPwId = null; // �������� �ʱ�ȭ �����ֱ�.
			}else if ( RpwCheck == 0) {
				javax.swing.JOptionPane.showMessageDialog(logc.findRPw, "��й�ȣ�� Ȯ���� �ּ���.");					
			}
						
			
			
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}



        
        
        
        
        
        
        
        
        
    
    
}




























