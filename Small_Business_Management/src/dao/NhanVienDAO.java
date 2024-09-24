package dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.CallableStatement;
import java.util.ArrayList;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import bean.NhanVien;
import dbcontext.DBContext;

public class NhanVienDAO {
	static Connection connection = DBContext.getConnection();
	static CallableStatement statement;
	
	public NhanVienDAO() {}
	
	public static ArrayList<NhanVien> LayThongTinNhanVien(){
		try {
			statement = connection.prepareCall("{call getNhanVien}");
			ResultSet rs = statement.executeQuery();
			ArrayList<NhanVien> danhSach = new ArrayList<NhanVien>();
			while(rs.next()) {
				NhanVien nv = new NhanVien(rs.getInt(1),
											rs.getString(2),
											rs.getDate(3),
											rs.getString(4),
											rs.getString(5),
											rs.getString(6),
											rs.getInt(7),
											rs.getInt(8),
											ByteArrayToImageIcon(rs.getBytes(9)));
				danhSach.add(nv);
			}
			return danhSach;
		} catch (SQLException err) {
			System.out.print(err);
			return null;
		}
	}
	
	public static ArrayList<NhanVien> LayThongTinNhanVien(int MaPB){
		try {
			statement = connection.prepareCall("{call getNhanVienTheoPhong(?)}");
			statement.setInt(1, MaPB);
			ResultSet rs = statement.executeQuery();
			ArrayList<NhanVien> danhSach = new ArrayList<NhanVien>();
			while(rs.next()) {
				NhanVien nv = new NhanVien(rs.getInt(1),
											rs.getString(2),
											rs.getDate(3),
											rs.getString(4),
											rs.getString(5),
											rs.getString(6),
											rs.getInt(7),
											rs.getInt(8),
											ByteArrayToImageIcon(rs.getBytes(9)));
				danhSach.add(nv);
			}
			return danhSach;
		} catch (SQLException err) {
			System.out.print(err);
			return null;
		}
	}
	
	private static ImageIcon ByteArrayToImageIcon(byte[] data) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
		    BufferedImage img = ImageIO.read(bis);
			ImageIcon icon = new ImageIcon(img);  
			return icon;
		} catch (Exception e) { 
			return null;
		}
	}

	private static byte[] ImageIconToByteArray(ImageIcon icon) {
		if (icon == null)
			return null;
		try {
			BufferedImage bi = new BufferedImage(
				    icon.getIconWidth(),
				    icon.getIconHeight(),
				    BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.createGraphics();
			icon.paintIcon(null, g, 0,0);
			g.dispose();
		    ByteArrayOutputStream bos = new ByteArrayOutputStream();
		    ImageIO.write(bi, "jpg", bos );
		    byte [] data = bos.toByteArray();
		    return data;
		} catch (Exception ex) {
		    ex.printStackTrace();
		    return null;
		}
	}
	
	public static boolean themNhanVien(NhanVien nv) {
		try {
			statement = connection.prepareCall("{call insertNhanVien(?,?,?,?,?,?,?,?,?)}");
			statement.setInt(1, nv.getMaNhanVien());
			statement.setString(2, nv.getHoTen());
			statement.setDate(3, nv.getNgaySinh());
			statement.setString(4, nv.getDiaChi());
			statement.setString(5, nv.getPhai());
			statement.setString(6, nv.getLuong());
			if(nv.getMaNQL() == 0) {
				statement.setNull(7, Types.INTEGER);
			}
			else {
				statement.setInt(7, nv.getMaNQL());
			}
			statement.setInt(8, nv.getMaPB());
			statement.setBytes(9, ImageIconToByteArray(nv.getImage()));
			statement.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean suaNhanVien(NhanVien nv) {
		try {
			statement = connection.prepareCall("{call updateNhanVien(?,?,?,?,?,?,?,?,?)}");
			statement.setInt(1, nv.getMaNhanVien());
			statement.setString(2, nv.getHoTen());
			statement.setDate(3, nv.getNgaySinh());
			statement.setString(4, nv.getDiaChi());
			statement.setString(5, nv.getPhai());
			statement.setString(6, nv.getLuong());
			if(nv.getMaNQL() == 0) {
				statement.setNull(7, Types.INTEGER);
			}
			else {
				statement.setInt(7, nv.getMaNQL());
			}
			statement.setInt(8, nv.getMaPB());
			statement.setBytes(9, ImageIconToByteArray(nv.getImage()));
			statement.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean xoaNhanVien(int id) {
		try {
			statement = connection.prepareCall("{call deleteNhanVien(?)}");
			statement.setInt(1, id);
			statement.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean kiemTraThongTinDangNhap(String taiKhoan,String passWord)
	{
		try {
			statement = connection.prepareCall("{call getTaiKhoanByID(?,?)}");
			statement.setString(1, taiKhoan);
			statement.setString(2, passWord);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}

		} catch (SQLException err) {
			return false;
		}
	}
	public static boolean updateMatKhau(String taiKhoan,String passWord)
	{
		try {
			statement = connection.prepareCall("{call updateTaiKhoan(?,?)}");
			statement.setString(1, taiKhoan);
			statement.setString(2, passWord);
			statement.executeQuery();
			return true;

		} catch (SQLException err) {
			//System.out.print(err);
			return false;
		}
	}
}
