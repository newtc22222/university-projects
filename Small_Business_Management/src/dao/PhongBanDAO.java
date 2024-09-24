package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bean.PhongBan;
import dbcontext.DBContext;

public class PhongBanDAO {
	static Connection connection = DBContext.getConnection();
	static CallableStatement statement;
	
	public PhongBanDAO() { }
	
	public static ArrayList<PhongBan> LayThongTinPhongBan(){
		try {
			statement = connection.prepareCall("{call getPhongBan}");
			ResultSet rs = statement.executeQuery();
			ArrayList<PhongBan> danhSach = new ArrayList<PhongBan>();
			while(rs.next()) {
				PhongBan pb = new PhongBan(rs.getInt(1),
										   rs.getString(2),
										   rs.getInt(3),
										   rs.getDate(4),
										   rs.getString(5));
				danhSach.add(pb);
			}
			return danhSach;
		} catch (SQLException err) {
			System.out.print(err);
			return null;
		}
	}
	
	public static boolean themPhongBan(PhongBan pb) {
		try {
			statement = connection.prepareCall("{call insertPhongBan(?,?,?,?,?)}");
			statement.setInt(1, pb.getMaPhongBan());
			statement.setString(2, pb.getTenPhongBan());
			if(pb.getMaTruongPhong() != 0) {
				statement.setInt(3, pb.getMaTruongPhong());
			}
			else {
				statement.setNull(3, Types.INTEGER);
			}
			if(pb.getNgayNhanChuc() != null) {
				statement.setDate(4, pb.getNgayNhanChuc());
			}
			else {
				statement.setNull(4, Types.DATE);
			}
			statement.setString(5, pb.getDiaDiem());
			statement.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean suaPhongBan(PhongBan pb) {
		try {
			statement = connection.prepareCall("{call updatePhongBan(?,?,?,?,?)}");
			statement.setInt(1, pb.getMaPhongBan());
			statement.setString(2, pb.getTenPhongBan());
			if(pb.getMaTruongPhong() != 0) {
				statement.setInt(3, pb.getMaTruongPhong());
			}
			else {
				statement.setNull(3, Types.INTEGER);
			}
			if(pb.getNgayNhanChuc() != null) {
				statement.setDate(4, pb.getNgayNhanChuc());
			}
			else {
				statement.setNull(4, Types.DATE);
			}
			statement.setString(5, pb.getDiaDiem());
			statement.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean xoaPhongBan(int id) {
		try {
			statement = connection.prepareCall("{call deletePhongBan(?)}");
			statement.setInt(1, id);
			statement.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
