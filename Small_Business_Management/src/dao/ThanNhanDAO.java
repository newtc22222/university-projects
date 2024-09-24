package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import bean.ThanNhan;
import dbcontext.DBContext;

public class ThanNhanDAO {
	static Connection connection = DBContext.getConnection();
	static CallableStatement statement;
	
	public ThanNhanDAO() {}
	
	public static ArrayList<ThanNhan> LayThongTinThanNhan(){
		try {
			statement = connection.prepareCall("{call getThanNhan}");
			ResultSet rs = statement.executeQuery();
			ArrayList<ThanNhan> danhSach = new ArrayList<ThanNhan>();
			while(rs.next()) {
				ThanNhan tn = new ThanNhan(rs.getString(1),
											rs.getInt(2),
											rs.getString(3),
											rs.getDate(4),
											rs.getString(5));
				danhSach.add(tn);
			}
			return danhSach;
		} catch (SQLException err) {
			return null;
		}
	}
	
	public static ArrayList<ThanNhan> LayThongTinThanNhan(int MaNVTN){
		try {
			statement = connection.prepareCall("{call getThanNhanTheoIDNV(?)}");
			statement.setInt(1, MaNVTN);
			ResultSet rs = statement.executeQuery();
			ArrayList<ThanNhan> danhSach = new ArrayList<ThanNhan>();
			while(rs.next()) {
				ThanNhan tn = new ThanNhan(rs.getString(1),
											rs.getInt(2),
											rs.getString(3),
											rs.getDate(4),
											rs.getString(5));
				danhSach.add(tn);
			}
			return danhSach;
		} catch (SQLException err) {
			return null;
		}
	}
	
	public static boolean themThanNhan(ThanNhan tn) {
		try {
			statement = connection.prepareCall("{call insertThanNhan(?,?,?,?,?)}");
			statement.setString(1, tn.getTenThanNhan());
			statement.setInt(2, tn.getMaNVTN());
			statement.setString(3, tn.getPhai());
			statement.setDate(4, tn.getNgaySinh());
			statement.setString(5, tn.getQuanHe());
			statement.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean suaThanNhan(ThanNhan tn) {
		try {
			statement = connection.prepareCall("{call updateThanNhan(?,?,?,?,?)}");
			statement.setString(1, tn.getTenThanNhan());
			statement.setInt(2, tn.getMaNVTN());
			statement.setString(3, tn.getPhai());
			statement.setDate(4, tn.getNgaySinh());
			statement.setString(5, tn.getQuanHe());
			statement.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean xoaThanNhan(String tenTN) {
		try {
			statement = connection.prepareCall("{call deleteThanNhan(?)}");
			statement.setString(1, tenTN);
			statement.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
