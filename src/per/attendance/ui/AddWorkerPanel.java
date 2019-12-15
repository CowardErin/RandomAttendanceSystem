package per.attendance.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import per.attendance.mysql.WorkerManager;

public class AddWorkerPanel extends JPanel {
	private JButton clearButton, writeButton;
	private WorkerUI userInterface;
	String name[] = {"��	 ��", "�� ��", "�� ��", "���� �쵼"};
	
	public AddWorkerPanel(){
		setLayout(new BorderLayout());
		userInterface = new WorkerUI(name);
		this.add(userInterface, BorderLayout.NORTH);
		
		// ���水ť�Ͱ�ť����¼�
		writeButton = userInterface.getDoTask1Button();
		writeButton.setText("����");
		writeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String fielValues[] = userInterface.getFieldValues();
				// ����ǿ�
				if (!fielValues[WorkerUI.WNO].equals("") && !fielValues[WorkerUI.WNAME].equals("") && !fielValues[WorkerUI.WDEPARTMENT].equals("")) {
					try {
						String sqlString = "INSERT INTO `workers` VALUE ('" +
								fielValues[WorkerUI.WNO] + "','" +
								fielValues[WorkerUI.WNAME] + "','" +
								fielValues[WorkerUI.WDEPARTMENT] + "','" +
								fielValues[WorkerUI.WLEADER] + "'," +
								 "0,0,0)";
						//System.out.println(sqlString);
						WorkerManager insertManager = new WorkerManager();
						int result = insertManager.execUpdate(sqlString);
						if (result != 0) {
							userInterface.clearFields();
							JOptionPane.showMessageDialog(null, "��ӳɹ���");
						}else {
							JOptionPane.showMessageDialog(null, "���鹤���Ƿ��Ѵ��ڣ�");
						}
					} catch (SQLException e1) {
						// TODO: handle exception
						System.out.println(e1);
						
					}
				}else {
					JOptionPane.showMessageDialog(null, "�������Ϣ������!");
				}
			}
		});
		
		// �����ť�Ͱ�ť����¼�
		clearButton = userInterface.getDoTask2Button();
		clearButton.setText("���");
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				userInterface.clearFields();
			}
		});
		
	}
	
}