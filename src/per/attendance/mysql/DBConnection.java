package per.attendance.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection conn = null;
	private static String username = "root";
	private static String password = "";
	private static String url = "jdbc:mysql://localhost:3306/workers?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	// ������ݿ������
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password); 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return conn;
	}
	// �ر����ݿ������
	public void closeConnection(){
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	
}
