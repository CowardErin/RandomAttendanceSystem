package per.attendance.main;

import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import per.attendance.mysql.DBConnection;
import per.attendance.ui.MainFrame;

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

