import java.io.Serializable;


public class car implements Serializable {
	protected int pknum;
	protected String carnum = "";
	protected String carvar = "";
	public int getpknum() {
		return pknum;
	}
	public String carvar() {
		return carvar;
	}

	public String getcarnum() {
		return carnum;
	}

	public void setpknum(int pknum) {
		this.pknum = pknum;
	}

	public String toString() {
		return pknum + " " + carnum;
	}

	public void setcarnum(String carnum) {
		this.carnum = carnum;
	}
	public void setcarvar(String carvar) {
		this.carvar = carvar;
	}
}
