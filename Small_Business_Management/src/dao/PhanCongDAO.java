package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bean.PhanCong;
import dbcontext.DBContext;

public class PhanCongDAO {
	static Connection connection = DBContext.getConnection();
	static CallableStatement statement;
	private static Object[] datas;
	
	public PhanCongDAO() {}
	
	public static ArrayList<PhanCong> LayThongTinPhanCong(){
		try {
			statement = connection.prepareCall("{call getPhanCong}");
			ResultSet rs = statement.executeQuery();
			ArrayList<PhanCong> danhSach = new ArrayList<PhanCong>();
			while(rs.next()) {
				PhanCong pc = new PhanCong(rs.getInt(1),
										   rs.getInt(2),
										   rs.getDate(3),
										   rs.getInt(4));
				danhSach.add(pc);
			}
			return danhSach;
		} catch (SQLException err) {
			return null;
		}
	}
	
	public static boolean themPhanCong(PhanCong pc) {
		try {
			statement = connection.prepareCall("{call insertPhanCong(?,?,?,?)}");
			statement.setInt(1, pc.getMaNhanVienPhanCong());
			statement.setInt(2, pc.getMaDuAn());
			if(pc.getThoiGian() != null) {
				statement.setDate(3, pc.getThoiGian());
			}
			else {
				statement.setNull(3, Types.DATE);
			}
			statement.setInt(4, pc.getTienThuong());
			statement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean suaPhanCong(PhanCong pc) {
		try {
			statement = connection.prepareCall("{call updatePhanCong(?,?,?,?)}");
			statement.setInt(1, pc.getMaNhanVienPhanCong());
			statement.setInt(2, pc.getMaDuAn());
			if(pc.getThoiGian() != null) {
				statement.setDate(3, pc.getThoiGian());
			}
			else {
				statement.setNull(3, Types.DATE);
			}
			statement.setInt(4, pc.getTienThuong());
			statement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean xoaPhanCong(int maNV, int maDA) {
		try {
			statement = connection.prepareCall("{call deletePhanCong(?, ?)}");
			statement.setInt(1, maNV);
			statement.setInt(2, maDA);
			statement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Object[] layThongTinChiTiet(int maNV, int maDA){
		datas = new Object[8];
		try {
			statement = connection.prepareCall("{call getThongTinPhanCongChiTiet(?, ?)}");
			statement.setInt(1, maNV);
			statement.setInt(2, maDA);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				datas[0] = rs.getObject(1);
				datas[1] = rs.getObject(2);
				datas[2] = rs.getObject(3);
				datas[3] = rs.getObject(4);
				datas[4] = rs.getObject(5);
				datas[5] = rs.getObject(6);
				datas[6] = rs.getObject(7);
				datas[7] = rs.getObject(8);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datas;
	}
}
