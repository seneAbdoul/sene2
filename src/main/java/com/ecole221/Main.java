package com.ecole221;

import com.ecole221.service.DBHelper;

import java.sql.ResultSet;

public class Main {
    public static void main(String[] args)   {
        try {
            DBHelper db =  DBHelper.getInstance();
            String sql = "select * from filiere";
            ResultSet rs = db.excuteSelect(sql, null);
            while (rs.next()){
                System.out.println(rs.getInt("id")+" / "+rs.getString("libelle"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}