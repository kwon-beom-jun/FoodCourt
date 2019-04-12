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
    	System.out.println(logc.login.pwtextfd.getPassword());
    	
        try {
        	conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();

			System.out.println("user_id \t password");
			System.out.println("=========================");
			
			while(rs.next()){
				String user_id = rs.getString("user_id");
				String password = rs.getString("password");
				
				String result = user_id+" "+password;
				System.out.println(result);
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
    
    public void showJoinpage() {
      System.out.println("test");
    }
    
}




























