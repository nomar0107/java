import java.io.Serializable;

public class owner extends car implements Serializable {
	private String ownername = "";
	private int birthyear = 0;
	private int utime = 0;
	private int oldtime = 0;
	private int rtime = 0;

	public int getoldtime() {
		return oldtime;
	}

	public String getownername() {
		return ownername;
	}

	public int getbirthyear() {
		return birthyear;
	}

	
	
	public int getutime() {
		return utime;
	}

	public int getrtime() {
		return rtime;
	}

	
	public String toString() {
		return "���ָ� : "+ownername+" ������ȣ :" + carnum + " " + "���� : " + carvar +" " +"�������"+birthyear+" " +"���ð� : "
				+ utime / 3600 + "�ð�" + utime / 60 % 60 + "��" + " " + "�����ð� : "
				+ rtime / 3600 + "�ð�" + rtime / 60 % 60 + "��" + " " + "����������ȣ : "
				+ pknum;
	}

	public void setoldtime(int oldtime) {
		this.oldtime = oldtime;
	}

	

	public void setownername(String ownername) {
		this.ownername = ownername;
	}

	public void setbirthyear(int birthyear) {
		this.birthyear = birthyear;
	}

	
	public void setutime(int utime) {
		this.utime = utime;
	}

	public void setrtime(int rtime) {
		this.rtime = rtime;
	}

	

}
