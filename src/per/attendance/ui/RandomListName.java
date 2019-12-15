package per.attendance.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.WebServiceException;

import per.attendance.mysql.WorkerManager;
import per.attendance.mysql.Workers;

public class RandomListName{
	private JPanel listNameJPanel;		// ������ɵ�UI����
	private List<Workers> workersList;	// ���Ա���б�
	private String[] columnName;		// �������ϵ�����Ϣ

	public RandomListName(){ 
		listNameJPanel = new JPanel();
		columnName = new String[]{"����", "����", "����", "����"};
		try {
			WorkerManager workerManager = new WorkerManager();
			String sql = "select Wno, Wname, Wdepartment, Wleader, Wrecords, Wkeeprds, Wtotalrds from workers";
			workersList = workerManager.Qurey(sql);
			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	// ��ÿ��������Ĺ�ģ
	public JPanel getWokersCounts()
	{  
		JPanel contentJPanel = new JPanel();
		
		// ��ʾԱ��������
		JPanel tipTextJPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel tipWorkersNumber = new JLabel("Ա������Ϊ: ");
		JTextArea workerNumber = new JTextArea(1, 16);	
		workerNumber.setText("\t"+workersList.size());
		workerNumber.setEditable(false);
		tipTextJPanel.add(tipWorkersNumber);
		tipTextJPanel.add(workerNumber);
		
		// ������ģ�����
		JPanel inputJPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel tipText = new JLabel("������������������Ĺ�ģ:");
		JTextField inputNumber = new JTextField(16);
		inputJPanel.add(tipText);
		inputJPanel.add(inputNumber);
		
		// ��ť
		JPanel buttonJPanel = new JPanel(new GridLayout(1, 2, 20, 10));
		JButton okBtn = new JButton("ȷ��");
		JButton clearBtn = new JButton("���");
		buttonJPanel.add(okBtn);
		buttonJPanel.add(clearBtn);
		
		// ��ֱ����
		Box verticall = Box.createVerticalBox();
		verticall.add(Box.createVerticalStrut(80));
		verticall.add(tipTextJPanel);
		verticall.add(inputJPanel);
		verticall.add(Box.createVerticalStrut(50));
		verticall.add(buttonJPanel);
		contentJPanel.add(verticall);
		
		listNameJPanel.setLayout(new BorderLayout());
		listNameJPanel.add(contentJPanel, BorderLayout.CENTER);
		//listNameJPanel.add(buttonJPanel, BorderLayout.SOUTH);
		
		// ��ť�����¼�
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				inputNumber.setText("");
			}
		});
		
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				if (inputNumber.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "������������ģ����");
					return;
				}
				int randomCounts = Integer.valueOf(inputNumber.getText());
				if (workersList.size() < randomCounts || randomCounts == 0 ) {
					JOptionPane.showMessageDialog(null, "����Ա�����������������");
					inputNumber.setText("");
					return;
				}
				listNameJPanel.setVisible(false);		// �����������
				listNameJPanel.removeAll();				// �������������ɾ��
				
				ListNameInterface(randomCounts);		// ���ƿ��ڽ���
				listNameJPanel.setVisible(true);		// ��ʾ���ڽ���
			}

		});
		
		return listNameJPanel;
	}
	
	// ��ʾ������
	private void ListNameInterface(int size) {
		// TODO �Զ����ɵķ������
		// ������¼�������
		Date nowData = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tableName = dateFormat.format(nowData);
		// ��ʾ������
		Object[][] workers = new Object[size][columnName.length];
		// �������ظ��������
		Random random = new Random();
        HashSet<Integer> hs = new HashSet<Integer>();
        for(;;){
            int temp = random.nextInt(workersList.size());
            hs.add(temp);
            if(hs.size() == size) break;
        }
        // ��ȡ�������
        int rowI=0;
		for(int index:hs){
        	workers[rowI][0] = workersList.get(index).getWno();
        	workers[rowI][1] = workersList.get(index).getWname();
        	workers[rowI][2] = workersList.get(index).getWdepartment();
        	workers[rowI][3] = new Boolean(false);
        	rowI++;
		}


        MyTable model = new MyTable(columnName, workers);
        JTable table = new JTable(model);
        // ���þ�����ʾ
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        // ���ò����϶�
        table.getTableHeader().setReorderingAllowed(false);
        // ���ù�����
        table.setRowHeight(30);
        JScrollPane jScrollPane = new JScrollPane(table);
        
        // ���ð�ť
        JPanel btnJPanel = new JPanel();
        JButton okBtn = new JButton("���");
        JButton cancelBtn = new JButton("ȡ��");
        btnJPanel.add(okBtn);
        btnJPanel.add(cancelBtn);
        
        // �ύ�����¼�
        okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				int pass = JOptionPane.showConfirmDialog(null, "ȷ���ύ��", "�ύ", JOptionPane.YES_NO_OPTION);
				if (pass==JOptionPane.YES_OPTION) {
					try {
						saveRecords(tableName, table);		// ���濼�ڼ�¼
						JOptionPane.showMessageDialog(null, "�ύ�ɹ���");
						listNameJPanel.setVisible(false);	// ��������
						listNameJPanel.removeAll();			// ɾ������
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
				}
			}
		});
        // ȡ�������¼�
        cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				listNameJPanel.setVisible(false);		// �ѵ���������
				listNameJPanel.removeAll();				// �ѵ��������ɾ��
				getWokersCounts();						// ���������ģ����
				listNameJPanel.setVisible(true);		// ��ʾ�����ģ����
			}
		});
        
        
        Box verticall = Box.createVerticalBox();
        verticall.add(jScrollPane);
        verticall.add(btnJPanel);
		listNameJPanel.add(verticall);

		}
	
	// ���濼�ڼ�¼
	protected void saveRecords(String tableName, JTable table) throws SQLException {
		// TODO �Զ����ɵķ������
		System.out.println(table.getValueAt(0, columnName.length-1));
		// ������¼��
		String tabelSql = "CREATE TABLE `" + tableName +"` ("
				+ "`Wno` CHAR(8) NOT NULL,"
				+ "`Wname` CHAR(5) NOT NULL,"
				+ "`Wrecord` CHAR(5) NOT NULL,"
				+ "PRIMARY KEY (`Wno`)"
				+ ")";
		WorkerManager cTableManager = new WorkerManager();
		cTableManager.execUpdate(tabelSql);
		// ��������д��
		//StringBuffer insertSql = new StringBuffer();
		for (int i = 0; i < workersList.size(); i++) {
			String insertSql =
					"INSERT INTO `" + tableName + "` VALUE("
					+ "'" + workersList.get(i).getWno() + "',"
					+ "'" + workersList.get(i).getWname() + "',"
					+ "'ͨ��'" + ");";
			WorkerManager insertManager = new WorkerManager();
			insertManager.execUpdate(insertSql);
		}
		// ��¼ȱ�ڵ���
		for (int row = 0; row < table.getRowCount(); row++) {
			if(!(boolean) table.getValueAt(row, columnName.length-1)){
				// ���¼�¼���
				String updateSql = "update `"+ tableName + "` set Wrecord='ȱ��' where Wno='" + table.getValueAt(row, 0) + "'";
				//System.out.println(updateSql);
				WorkerManager updateManager = new WorkerManager();
				updateManager.execUpdate(updateSql);
			}
		}
		
		// ���¿��ڳɼ�
		for(int i=0; i<workersList.size(); i++){
			// �鿴��¼�����Ƿ�ͨ��
			WorkerManager selectManager = new WorkerManager();
			String result = selectManager.getRecord(tableName, workersList.get(i).getWno());
			//System.out.println(result);
			// ����ͨ������
			if(result.equals("ͨ��")){
				// С��5�ο��ڲ�ͨ�������ڳɼ� + 1
				if(workersList.get(i).getWtotalrds() < 5)
					workersList.get(i).setWrecords(workersList.get(i).getWrecords() + 1); // �ɼ�+1
				// ����ͨ�� ���������ڼ�¼��0
				workersList.get(i).setWkeeprds(0);
			}
			// ���ڲ�ͨ������
			if (result.equals("ȱ��")) {
				// ��ȱ�ڼ�¼ + 1
				workersList.get(i).setWtotalrds(workersList.get(i).getWtotalrds() + 1); 
				// �ۼ�5�ο��ڲ�ͨ�������ڳɼ�����
				if(workersList.get(i).getWtotalrds() >= 5)		
					workersList.get(i).setWrecords(0);
				// ����ȱ�ڼ�¼ + 1
				workersList.get(i).setWkeeprds(workersList.get(i).getWkeeprds() + 1); 
				// ����ȱ�ڼ�¼����3�Σ����ڳɼ���0
				if (workersList.get(i).getWkeeprds() >= 3)
					workersList.get(i).setWrecords(0);
			}
		}
		
		// �������ݿ�
		for(int i=0; i<workersList.size(); i++)
		{
			String updateWorkerSql = "update `workers` set" + 
					" Wrecords=" + workersList.get(i).getWrecords() + "," +
					" Wkeeprds=" + workersList.get(i).getWkeeprds() + "," +
					" Wtotalrds=" + workersList.get(i).getWtotalrds()+ 
					" where Wno='" + workersList.get(i).getWno() + "'";
			//System.out.println(updateWorkerSql);
			WorkerManager updateManager = new WorkerManager();
			updateManager.execUpdate(updateWorkerSql);
		}
		
	}

	// ���������
	private class MyTable extends AbstractTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		// ������
		String[] columnNames;
		Object[][] workers;
		
		public MyTable(String[] columnName, Object[][] data) {
			// TODO �Զ����ɵĹ��캯�����
			this.columnNames = columnName;
			this.workers = data;
		}
		
		@Override
		public int getRowCount() {
			// TODO �Զ����ɵķ������
			return workers.length;
		}

		@Override
		public int getColumnCount() {
			// TODO �Զ����ɵķ������
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO �Զ����ɵķ������
			return workers[rowIndex][columnIndex];
		}

		@Override
		public String getColumnName(int column) {
			// TODO �Զ����ɵķ������
			return columnNames[column];
		}

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO �Զ����ɵķ������
			return getValueAt(0, columnIndex).getClass();
		}
		
        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO �Զ����ɵķ������
            //Note that the data/cell address is constant,
            //no matter where the cell appears on screen.
			if (columnIndex == columnNames.length-1)
				return true;
			else
				return false;

		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO �Զ����ɵķ������
			workers[rowIndex][columnIndex] = aValue;
			fireTableRowsUpdated(0, rowIndex);
		}
		
	}
	
}
