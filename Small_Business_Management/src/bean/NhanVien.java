package bean;

import java.sql.Date;

import javax.swing.ImageIcon;

public class NhanVien {
	private int maNhanVien;
	private String hoTen;
	private Date ngaySinh;
	private String diaChi;
	private String phai;
	private String luong;
	private int maNQL;
	private int maPB;
	private ImageIcon image;
	
	public int getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getPhai() {
		return phai;
	}
	public void setPhai(String phai) {
		this.phai = phai;
	}
	public String getLuong() {
		return luong;
	}
	public void setLuong(String luong) {
		this.luong = luong;
	}
	
	public NhanVien() {
		this.maNhanVien = -1;
		this.hoTen = "Unknown";
		this.ngaySinh = null;
		this.diaChi = "";
		this.phai = "";
		this.luong = "0";
		this.maNQL = -1;
		this.maPB = 0;
		this.image = null;
	}
	
	public NhanVien(int maNhanVien, String hoTen, Date ngaySinh, String diaChi, String phai, String luong, int maNQL, int maPB, ImageIcon image) {
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.phai = phai;
		this.luong = luong;
		this.maNQL = maNQL;
		this.maPB = maPB;
		this.image = image;
	}
	public int getMaNQL() {
		return maNQL;
	}
	public void setMaNQL(int maNQL) {
		this.maNQL = maNQL;
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public int getMaPB() {
		return maPB;
	}
	public void setMaPB(int maPB) {
		this.maPB = maPB;
	}
}
