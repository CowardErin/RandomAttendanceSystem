package per.attendance.mysql;

public class Workers {
	private String Wno;				// ����
	private String wname;			// ����
	private String Wdepartment;		// ����
	private String Wleader;			// ���ţ��쵼
	private int Wrecords;			// ���ڳɼ�
	private int Wkeeprds;			// ����ȱ�ڼ�¼
	private int Wtotalrds;			// �ܵ�ȱ�ڼ�¼
	
	public String getWno() {
		return Wno;
	}
	public void setWno(String wno) {
		Wno = wno;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getWdepartment() {
		return Wdepartment;
	}
	public void setWdepartment(String wdepartment) {
		Wdepartment = wdepartment;
	}
	public String getWleader() {
		return Wleader;
	}
	public void setWleader(String wleader) {
		Wleader = wleader;
	}
	public int getWrecords() {
		return Wrecords;
	}
	public void setWrecords(int wrecords) {
		Wrecords = wrecords;
	}
	public int getWkeeprds() {
		return Wkeeprds;
	}
	public void setWkeeprds(int wkeeprds) {
		Wkeeprds = wkeeprds;
	}
	public int getWtotalrds() {
		return Wtotalrds;
	}
	public void setWtotalrds(int wtotalrds) {
		Wtotalrds = wtotalrds;
	}
}
