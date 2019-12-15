package per.attendance.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkerManager extends DBConnection {
	private Connection conn = null;

	// ���췽����ʵ�ִ����ݿ������
	public WorkerManager() {
		conn = getConnection();
	}
	
	// ��workers����롢ɾ�����޸Ĳ���
	public int execUpdate(String sql) throws SQLException{
		int result = 0;
		Statement statement = null;
		try{
			statement = conn.createStatement();
			result = statement.executeUpdate(sql);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally{
			if (statement != null) {
				statement.close();
				closeConnection();
			}
		}
		return result;
	}
	
	// ��worker����в�ѯ����������List�ṹ���ؽ���� ����ʱ�ر����ݿ�
	public List<Workers> Qurey(String sql) throws SQLException {
		List<Workers> list = new ArrayList<Workers>();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next()){
				Workers worker = new Workers();
				worker.setWno(rs.getString("Wno"));
				worker.setWname(rs.getString("Wname"));
				worker.setWdepartment(rs.getString("Wdepartment"));
				worker.setWleader(rs.getString("Wleader"));
				worker.setWrecords(rs.getInt("Wrecords"));
				worker.setWkeeprds(rs.getInt("Wkeeprds"));
				worker.setWtotalrds(rs.getInt("Wtotalrds"));
				list.add(worker);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally{
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
			closeConnection();
		}
		return list;
	}
	
	// ���ؼ�¼�����Ƿ�ͨ��
	public String getRecord(String tableName ,String Wno) throws SQLException
	{
		Statement statement = null;
		ResultSet rs = null;
		String record = "";
		String selectSql = "select * from `" + tableName + "` where Wno='" + Wno + "';";
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(selectSql);
			while(rs.next())
				record = rs.getString("Wrecord");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally {
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
			closeConnection();
		}
		return record;
	}
	
	// �������м�¼��ı���
	public String getRecordTableName() throws SQLException
	{
		String tableName = "";
		ResultSet rs = null;
		DatabaseMetaData data = null;
		try {
			data = conn.getMetaData();
			rs = data.getTables("workers", "%", "%",new String[]{"TABLE"}); 
			String name = "";
			while(rs.next())
			{
				name = rs.getString("TABLE_NAME");
				if (!name.equals("workers")) {
					tableName += name + "\n"; 
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally{
			if (rs != null) {
				rs.close();
			}
			closeConnection();
		}
		
		return tableName;
	}
	
	// �Լ�¼����в�ѯ����������List�ṹ���ؽ���� ����ʱ�ر����ݿ�
	public Object[][] getRecordContent(String Sql) throws SQLException
	{
		Object[][] recordContent = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(Sql);
			rs.last();		// ��ȡ������
			int allRow = rs.getRow();
			recordContent = new Object[allRow][3];
			int row = 0;
			rs.beforeFirst();
			while(rs.next()){
				recordContent[row][0] = rs.getString("Wno");
				recordContent[row][1] = rs.getString("Wname");
				recordContent[row][2] = rs.getString("Wrecord");
				row++;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
			closeConnection();
		}
		
		return recordContent;
	}
}
