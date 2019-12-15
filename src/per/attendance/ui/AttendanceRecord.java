package per.attendance.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import per.attendance.mysql.WorkerManager;


public class AttendanceRecord {
	private JPanel recordJPanel;	// ��ʾ�Ľ���
	
	public AttendanceRecord(){
		recordJPanel = new JPanel();
	}
	
	// ��ʾѡ��鿴������¼����
	public JPanel showRecords()
	{
		// ��ȡ��¼����
		WorkerManager reManager = new WorkerManager();
		String tableName = "";
		try {
			tableName = reManager.getRecordTableName();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		String[] itemName = tableName.split("\n");
		JComboBox<String> recoredBox = new JComboBox<String>(itemName);
		recoredBox.setMaximumRowCount(10); 				// �����ʾ����
		
		JButton okBtn = new JButton("�鿴");
		
		JPanel selectJPanel = new JPanel();
		selectJPanel.add(recoredBox);
		selectJPanel.add(okBtn);
		
		JPanel tableJPanel = new JPanel();
		
		// ȷ�ϰ�ť�����¼�
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				try {
					recordJPanel.setVisible(false);
					tableJPanel.setVisible(false);
					tableJPanel.removeAll();
					showDetail((String)recoredBox.getSelectedItem(), tableJPanel);
					tableJPanel.setVisible(true);
					recordJPanel.setVisible(true);
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});
		
		Box verticall = Box.createVerticalBox();
		verticall.add(selectJPanel);
		verticall.add(tableJPanel);
		
		recordJPanel.add(verticall);
		
		return recordJPanel;
	}
	
	// ��ʾ��¼�������
	public void showDetail(String tableName, JPanel tableJPanel) throws SQLException
	{
		WorkerManager selectManager = new WorkerManager();
		String selectSql = "select * from `" + tableName + "`";
		//System.out.println(selectSql);
		Object[][] data = selectManager.getRecordContent(selectSql);
		
		String[] columnNames = {"����", "����", "��¼"};
		
		JTable table = new JTable(data, columnNames);
        // ���þ�����ʾ
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        // ���ò����϶�
        table.getTableHeader().setReorderingAllowed(false);
        // ���ò��ɱ༭
        table.setEnabled(false);
         // ���ø߶�
        table.setRowHeight(30);
        // ���ù�����
        JScrollPane jScrollPane = new JScrollPane(table);
        
        
		tableJPanel.add(jScrollPane);
	}
}
