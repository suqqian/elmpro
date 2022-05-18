package com.elm.view.impl;

import com.elm.dao.AdminDao;
import com.elm.dao.BusinessDao;
import com.elm.dao.impl.AdminDaoImpl;
import com.elm.dao.impl.BusinessDaoImpl;
import com.elm.po.Admin;
import com.elm.po.Business;
import com.elm.view.AdminView;

import java.util.List;
import java.util.Scanner;



public class AdminViewImpl implements AdminView {

	private Scanner input = new Scanner(System.in);

	@Override
	public Admin login() {
		System.out.println("请输入管理员名称：");
		String adminName = input.next();
		System.out.println("请输入密码：");
		String password = input.next();
		AdminDao dao = new AdminDaoImpl();
		return dao.getAdminByNameByPass(adminName, password);
	}

	@Override
	public void insectAdmin() {
		System.out.println("请输入设置管理员姓名：");
		String adminName = input.next();
		System.out.println("请输入设置密码：");
		String password = input.next();
		Admin admin = new Admin();
		admin.setAdminName(adminName);
		admin.setPassword(password);
		AdminDao adminDao = new AdminDaoImpl();
		int adminId = adminDao.insectAdmin(admin);
		if (adminId != 0) {
			System.out.println("\n插入管理者信息成功！\n");
		} else {
			System.out.println("\n插入管理者信息失败！\n");
		}
	}

	@Override
	public void deleteAdminPassWord() {
		System.out.println("请输入要删除的管理员姓名：");
		String adminName = input.next();
		AdminDao adminDao = new AdminDaoImpl();
		int k = adminDao.deleteAdmin(adminName);
		if (k != 0) {
			System.out.println("\n删除成功！\n");
		} else {
			System.out.println("\n删除失败！\n");
		}
	}

	@Override
	public void listAdmin() {
		AdminDao adminDao = new AdminDaoImpl();
		List<Admin> list = adminDao.listAdmin();
		System.out.println("管理员编号\t管理员姓名\t密码");
		for(Admin b : list) {
			System.out.println(b.getAdminId()+"\t"+b.getAdminName()+"\t"+b.getPassword());
		}

	}

}

