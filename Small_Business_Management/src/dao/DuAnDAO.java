package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.ArrayList;

import bean.DuAn;
import dbcontext.DBContext;

public class DuAnDAO {
	static Connection connection = DBContext.getConnection();
	static CallableStatement statement;
	
	public DuAnDAO() {}
	
	public static ArrayList<DuAn> LayThongTinDuAn(){
		try {
			statement = connection.prepareCall("{call getDuAn}");
			ResultSet rs = statement.executeQuery();
			ArrayList<DuAn> danhSach = new ArrayList<DuAn>();
			while(rs.next()) {
				DuAn da = new DuAn(rs.getInt(1),
								   rs.getString(2),
								   rs.getString(3),
								   rs.getInt(4));
				danhSach.add(da);
			}
			return danhSach;
		} catch (SQLException err) {
			return null;
		}
	}
	
	public static ArrayList<DuAn> LayThongTinDuAn(int MaPB){
		try {
			statement = connection.prepareCall("{call getDuAnTheoPhong(?)}");
			statement.setInt(1, MaPB);
			ResultSet rs = statement.executeQuery();
			ArrayList<DuAn> danhSach = new ArrayList<DuAn>();
			while(rs.next()) {
				DuAn da = new DuAn(rs.getInt(1),
								   rs.getString(2),
								   rs.getString(3),
								   rs.getInt(4));
				danhSach.add(da);
			}
			return danhSach;
		} catch (SQLException err) {
			return null;
		}
	}
	
	public static boolean themDuAn(DuAn da) {
		try {
			statement = connection.prepareCall("{call insertDuAn(?,?,?,?)}");
			statement.setInt(1, da.getMaDuAn());
			statement.setString(2, da.getTenDuAn());
			statement.setString(3, da.getDiaDiem());
			statement.setInt(4, da.getPhong());
			statement.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean suaDuAn(DuAn da) {
		try {
			statement = connection.prepareCall("{call updateDuAn(?,?,?,?)}");
			statement.setInt(1, da.getMaDuAn());
			statement.setString(2, da.getTenDuAn());
			statement.setString(3, da.getDiaDiem());
			statement.setInt(4, da.getPhong());
			statement.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean xoaDuAn(int id) {
		try {
			statement = connection.prepareCall("{call deleteDuAn(?)}");
			statement.setInt(1, id);
			statement.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
