package bean;

import java.sql.Date;

public class PhanCong {
	private int maNhanVienPhanCong;
	private int maDuAn;
	private Date thoiGian;
	private int tienThuong;
	
	public int getMaNhanVienPhanCong() {
		return maNhanVienPhanCong;
	}
	public void setMaNhanVienPhanCong(int maNhanVienPhanCong) {
		this.maNhanVienPhanCong = maNhanVienPhanCong;
	}
	public int getMaDuAn() {
		return maDuAn;
	}
	public void setMaDuAn(int maDuAn) {
		this.maDuAn = maDuAn;
	}
	public Date getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}
	
	public PhanCong(int maNhanVienPhanCong, int maDuAn) {
		this.maNhanVienPhanCong = maNhanVienPhanCong;
		this.maDuAn = maDuAn;
		this.thoiGian = null;
		this.tienThuong = 0;
	}

	public PhanCong(int maNhanVienPhanCong, int maDuAn, Date thoiGian, int tienThuong) {
		this.maNhanVienPhanCong = maNhanVienPhanCong;
		this.maDuAn = maDuAn;
		this.thoiGian = thoiGian;
		this.tienThuong = tienThuong;
	}
	
	public int getTienThuong() {
		return tienThuong;
	}
	public void setTienThuong(int tienThuong) {
		this.tienThuong = tienThuong;
	}
}
