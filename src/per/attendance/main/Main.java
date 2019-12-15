package per.attendance.main;

import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Main {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		try{
			// ����JDBC��������
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				System.out.println("�Ҳ����������򣬼�������ʧ�ܣ�");
				e.printStackTrace();
			}
			// �������ݿ�
			String url = "jdbc:mysql://localhost:3306/spj?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";	
			String username = "root";
			String password= "";
			//try {
				//Connection conn = DriverManager.getConnection(url, username, password);
				Connection conn = new DBConnection().getConnection();
/*			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				System.out.println("���ݿ�����ʧ��");
				e.printStackTrace();
			}*/
			// ִ�����ݿ�
			Statement statement = conn.createStatement();
			String sqlQuery = "select * from workers";
			
			DatabaseMetaData data  = conn.getMetaData(); 
			ResultSet tableRet = data.getTables("spj", "spj", "%",new String[]{"TABLE"}); 
			while(tableRet.next()){
				System.out.println(tableRet.getString("TABLE_NAME"));
			}
			
			
			ResultSet rs = statement.executeQuery(sqlQuery);
			while (rs.next()) {
				System.out.print(rs.getString(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t ");
				System.out.println(rs.getString(4));
			}
			// �ر�����
			rs.close();
			statement.close();
			conn.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
		JFrame tableJFrame = new JFrame("���Ա��");
		 // ����������壬ʹ�ñ߽粼��
        JPanel panel = new JPanel(new BorderLayout());

        // ��ͷ��������
        Object[] columnNames = {"����", "����", "��ѧ", "Ӣ��", "�ܷ�"};

        // �������������
        Object[][] rowData = {
                {"����", 80, 80, 80, new Boolean(false)},
                {"John", 70, 80, 90, new Boolean(false)},
                {"Sue", 70, 70, 70, new Boolean(false)},
                {"Jane", 80, 70, 60, new Boolean(false)},
                {"Joe", 80, 70, 60, new Boolean(false)}
        };

        // ����һ�����ָ�� ���������� �� ��ͷ
        JTable table = new JTable(rowData, columnNames);

        // �� ��ͷ ��ӵ�����������ʹ����ͨ���м�������ӱ��ʱ����ͷ �� ���� ��Ҫ�ֿ���ӣ�
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        // �� ������� ��ӵ���������
        panel.add(table, BorderLayout.CENTER);

        tableJFrame.setContentPane(panel);
        tableJFrame.pack();
        tableJFrame.setLocationRelativeTo(null);
        //tableJFrame.setVisible(true);
		
        //new MainInterface();
        
        // ����������
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // ��ȡ��Ļ�ߴ�
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        // ������
        if (frameSize.height > screenSize.height) {
			frameSize = screenSize; 
		}
        if (frameSize.width > screenSize.width) {
			frameSize = screenSize;
		}
        frame.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
        frame.setVisible(true);
	}
	
	
}

