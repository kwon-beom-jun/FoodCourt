package kitri.foodCourt.user.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection 
{
    public static Connection dbConn;
    
    public static Connection getConnection()
    {
        Connection conn = null;
        try {
    
        	String user = "kitri"; 
            String pw = "kitri";
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            // jdbc��� ���������� ���ٴ°� thin:@ ���� ��������
            // ��Ʈ��ȣ �ٲ�.
            
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            // �������� ������
            conn = DriverManager.getConnection(url, user, pw);
            // �����ͺ��̽� ����
            // url = Uniform Resource Locator �� �����ε� �� �״�� ����ȭ �� ���ҽ� ��ġǥ�� ��� ��
            // uri = Ư�� �ڿ��� ���� ������ ��� �Ӹ� �ƴ϶� ����ũ�� �̸��� ���� ����� ��θ� ������ �ִ°�
            
            System.out.println("Database�� ����Ǿ����ϴ�.\n");
            
        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB ����̹� �ε� ���� :"+cnfe.toString());
        } catch (SQLException sqle) {
            System.out.println("DB ���ӽ���!! : "+sqle.toString());
        } catch (Exception e) {
            System.out.println("Unkonwn error");
            e.printStackTrace();
        }
        return conn;     
    }
}
 
