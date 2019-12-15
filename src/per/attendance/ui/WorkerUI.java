package per.attendance.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.naming.event.NamespaceChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class WorkerUI extends JPanel {
	protected JLabel labels[];
	protected JTextField fields[];
	protected JButton doTask1,doTask2;
	protected JPanel innerPanelCenter,innerPanelSouth;
	protected int size;
	public static final int WNO = 0 ,WNAME = 1 ,WDEPARTMENT = 2,
			WLEADER = 3;
	public WorkerUI(String arrayString[]){
		size = arrayString.length;
		labels = new JLabel[size];
		fields = new JTextField[size];
		// ������ǩ
		for (int count =0; count<labels.length;count++)
			labels[count] = new JLabel(arrayString[count]);
		// ���������
		for(int count = 0; count<fields.length;count++)
			fields[count] = new JTextField();
		// ��ǩ������������
		innerPanelCenter = new JPanel();
		innerPanelCenter.setLayout(new GridLayout(size,2));
		for (int count = 0; count<size;count++){
			innerPanelCenter.add(labels[count]);
			innerPanelCenter.add(fields[count]);
		}
		// ����������ť
		doTask1 = new JButton(); doTask2 = new JButton();
		innerPanelSouth = new JPanel();
		innerPanelSouth.add(doTask1);
		innerPanelSouth.add(doTask2);
		// ��ǩ������򡢰�ťλ��
		setLayout(new BorderLayout());
		add(innerPanelCenter,BorderLayout.CENTER);
		add(innerPanelSouth,BorderLayout.SOUTH);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		validate();
	}
		
		public JButton getDoTask1Button(){
			return doTask1;
		}
		
		public JButton getDoTask2Button(){
			return doTask2;
		}
		
		public JTextField[] getFields(){
			return fields;
		}
		// �������������
		public void clearFields(){
			for(int count = 0;count<size; count++)
				fields[count].setText("");
		}
		// ��������������
		public void setFieldVlues(String strings[])throws IllegalArgumentException{
			if (strings.length != size)
				throw new IllegalArgumentException("There must be"+size+"String in the array");
			for (int count = 0; count<size; count++)
				fields[count].setText(strings[count]);
		}
		// �������������
		public String[] getFieldValues()
		{
			String[] values = new String[size];
			for(int count=0;count<size; count++)
				values[count] = fields[count].getText();
			return values;
		}
}
