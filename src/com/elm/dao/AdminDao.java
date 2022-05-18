package com.elm.dao;


import com.elm.po.Admin;

import java.util.List;

public interface AdminDao {

    public Admin getAdminByNameByPass(String adminName, String password);

    public int insectAdmin(Admin admin);

    public int deleteAdmin(String adminName);

    public List<Admin> listAdmin();

}
