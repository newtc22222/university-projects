package bean;

import java.sql.Date;

public class ThanNhan {
	private String tenThanNhan;
	private String phai;
	private Date ngaySinh;
	private String quanHe;
	private int maNVTN;
	
	public String getPhai() {
		return phai;
	}
	public void setPhai(String phai) {
		this.phai = phai;
	}
	public String getTenThanNhan() {
		return tenThanNhan;
	}
	public void setTenThanNhan(String tenThanNhan) {
		this.tenThanNhan = tenThanNhan;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getQuanHe() {
		return quanHe;
	}
	public void setQuanHe(String quanHe) {
		this.quanHe = quanHe;
	}
	
	public ThanNhan() {
		this.tenThanNhan = "Unknown";
		this.phai = "undefined";
		this.ngaySinh = null;
		this.quanHe = "Unknown";
		this.maNVTN = -1;
	}
	
	public ThanNhan(String tenThanNhan, int maNVTN, String phai, Date ngaySinh, String quanHe) {
		this.tenThanNhan = tenThanNhan;
		this.phai = phai;
		this.ngaySinh = ngaySinh;
		this.quanHe = quanHe;
		this.maNVTN = maNVTN;
	}
	public int getMaNVTN() {
		return maNVTN;
	}
	public void setMaNVTN(int maNVTN) {
		this.maNVTN = maNVTN;
	}
}
