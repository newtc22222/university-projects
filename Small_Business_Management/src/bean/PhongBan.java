package bean;

import java.sql.Date;

public class PhongBan {
	private int maPhongBan;
	private String tenPhongBan;
	private int maTruongPhong;
	private Date ngayNhanChuc;
	private String diaDiem;
	
	public int getMaPhongBan() {
		return maPhongBan;
	}
	public void setMaPhongBan(int maPhongBan) {
		this.maPhongBan = maPhongBan;
	}
	public String getTenPhongBan() {
		return tenPhongBan;
	}
	public void setTenPhongBan(String tenPhongBan) {
		this.tenPhongBan = tenPhongBan;
	}
	public int getMaTruongPhong() {
		return maTruongPhong;
	}
	public void setMaTruongPhong(int maTruongPhong) {
		this.maTruongPhong = maTruongPhong;
	}
	public Date getNgayNhanChuc() {
		return ngayNhanChuc;
	}
	public void setNgayNhanChuc(Date ngayNhanChuc) {
		this.ngayNhanChuc = ngayNhanChuc;
	}
	public String getDiaDiem() {
		return diaDiem;
	}
	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}
	
	public PhongBan() {	}

	public PhongBan(int maPhongBan, String tenPhongBan, int maTruongPhong, Date ngayNhanChuc, String diaDiem) {
		this.maPhongBan = maPhongBan;
		this.tenPhongBan = tenPhongBan;
		this.maTruongPhong = maTruongPhong;
		this.ngayNhanChuc = ngayNhanChuc;
		this.diaDiem = diaDiem;
	}
}
