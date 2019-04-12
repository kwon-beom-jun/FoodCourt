package kitri.foodCourt.user.member;
 
import java.io.PrintStream;
import java.sql.*;
 
public class LoginService {
    
	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
	
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
			System.out.println("쿼리문 오류");
		} finally{
            // DB 연결을 종료한다.
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




























