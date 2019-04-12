package kitri.foodCourt.user.member;
 
import java.io.PrintStream;
import java.sql.*;
 
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
    	
    	String quary = "select user_id, password from fook_user";
    	
    	logc.login.idtextField.getText();
    	logc.login.pwtextfd.getPassword();
    	
        try {
        	conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();

//			System.out.println("user_id \t password");
//			System.out.println("=========================");
			
			if ((logc.login.idtextField.getText().isEmpty()) || (logc.login.pwtextfd.getPassword().length == 0)) {
				javax.swing.JOptionPane.showMessageDialog(logc.login, "���̵� �Ǵ� ��й�ȣ�� �Է��� �ּ���.");

			}
			
			String str = new String(logc.login.pwtextfd.getPassword());
			String user_id;
			String password;
			
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
				}
				
//				if (logc.login.idtextField.getText().equals(user_id)) {
//					��й�ȣ�� Ʋ�Ƚ��ϴ�.
//				}
				
			}
			
//			if (logc.login.idtextField.getText() != user_id || str != password) {
//				javax.swing.JOptionPane.showMessageDialog(logc.login, "���̵�� ��й�ȣ�� Ȯ���� �ּ���.");
//			}else if (logc.login.idtextField.getText().equals(user_id)) {
//				if (str != password) {
//					javax.swing.JOptionPane.showMessageDialog(logc.login, "��й�ȣ�� Ʋ���ϴ�.");
//				}
//			}
			
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
    
    public void showJoinpage() {
      System.out.println("test");
    }
    
}




























