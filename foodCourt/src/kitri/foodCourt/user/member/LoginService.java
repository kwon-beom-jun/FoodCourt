package kitri.foodCourt.user.member;
 
import java.io.PrintStream;
import java.sql.*;

import kitri.foodCourt.user.User;
 
public class LoginService {
    
	Connection conn = null; // DB����� ����(����)�� ���� ��ü
    PreparedStatement pstm = null;  // SQL ���� ��Ÿ���� ��ü
    ResultSet rs = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
	
	LoginControl logc;
//	DBConnection dbConnection;
	
    LoginService(LoginControl logc) { 
    	
    	this.logc = logc;
//    	dbConnection = new DBConnection();
    	
    }
    
    public void logJoin() {
    	
    	String quary = "select user_id, password, name, user_point from fook_user";
    	
    	logc.login.idtextField.getText();
    	logc.login.pwtextfd.getPassword();
    	
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
			
			
			String str = new String(logc.login.pwtextfd.getPassword()); // ��й�ȣ�� ��ǥ�� �߰� ����� �ҽ��� �Ἥ ���� �����ؼ� �� �� ����.
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
//					User user = new User(user_id, rs.getString("name"), rs.getInt("user_point"));
//					user.setPhoneNumberFirst(phoneNumberFirst);
//					user.setPhoneNumberFirst(phoneNumberFirst);
					//user.set
					//System.out.println(user);
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
    
    
    
    public void doublechek() {
    	
        String quary = "select user_id from fook_user";
        
        if (logc.join.idTextFD.getText().isEmpty()) {
        	javax.swing.JOptionPane.showMessageDialog(logc.join, "���̵� �Է� �ٶ��ϴ�.");
		}
  	
	    
        try {
      		conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();

			String user_id = logc.join.idTextFD.getText();
			String DBuser_id;
			
			while(rs.next()){
				
				DBuser_id = rs.getString("user_id");
				
				if (user_id.equals(DBuser_id)) {
					javax.swing.JOptionPane.showMessageDialog(logc.join, "�ߺ��� ���̵� �ֽ��ϴ�.");
					logc.join.idTextFD.setText("");
				}else {
					javax.swing.JOptionPane.showMessageDialog(logc.join, "��� ������ ���̵� �Դϴ�.");
				}
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
    
}




























