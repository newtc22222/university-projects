package bean;

public class DuAn {
	private int maDuAn;
	private String tenDuAn;
	private String diaDiem;
	private int phong;
	
	public int getMaDuAn() {
		return maDuAn;
	}
	public void setMaDuAn(int maDuAn) {
		this.maDuAn = maDuAn;
	}
	public String getTenDuAn() {
		return tenDuAn;
	}
	public void setTenDuAn(String tenDuAn) {
		this.tenDuAn = tenDuAn;
	}
	public String getDiaDiem() {
		return diaDiem;
	}
	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}

	public DuAn() {
		this.maDuAn = 0;
		this.tenDuAn = "unknown";
		this.diaDiem = "unknown";
		this.phong = 0;
	}
	
	public DuAn(int maDuAn, String tenDuAn, String diaDiem, int phong) {
		this.maDuAn = maDuAn;
		this.tenDuAn = tenDuAn;
		this.diaDiem = diaDiem;
		this.phong = phong;
	}
	public int getPhong() {
		return phong;
	}
	public void setPhong(int phong) {
		this.phong = phong;
	}
}
