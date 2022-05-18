package com.elm.dao.impl;

import com.elm.dao.AdminDao;
import com.elm.po.Admin;
import com.elm.po.Business;
import com.elm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminDaoImpl implements AdminDao {
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	@Override
	public Admin getAdminByNameByPass(String adminName, String password) {
		Admin admin = null;
		String sql = "select * from admin where adminName=? and password=?";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, adminName);
			pst.setString(2, password);
			rs = pst.executeQuery();
			while(rs.next()) {
				admin = new Admin();
				admin.setAdminId(rs.getInt("adminId"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst, con);
		}
		return admin;
	}

	@Override
	public int insectAdmin(Admin admin) {
		int result = 0;
		String name = admin.getAdminName();
		String password = admin.getPassword();
		String sql = "insert into admin(adminName,password) value(?,?)";
		con = DBUtil.getConnection();
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteAdmin(String adminName) {
		int result = 0;
		String sql = "delete from admin where adminName=?";
		try {
			con = DBUtil.getConnection();
			//开启一个事务
			con.setAutoCommit(false);
			pst = con.prepareStatement(sql);
			pst.setString(1, adminName);
			result = pst.executeUpdate();

			con.commit();
		} catch (SQLException e) {
			result = 0;
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.close(null, pst, con);
		}
		return result;
	}

	@Override
	public List<Admin> listAdmin() {
		List<Admin> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer("select * from admin");
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			while(rs.next()) {
				Admin admin = new Admin();
				admin.setAdminId(rs.getInt("adminId"));
				admin.setAdminName(rs.getString("AdminName"));
				admin.setPassword(rs.getString("Password"));
				list.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst, con);
		}
		return list;
	}
}
