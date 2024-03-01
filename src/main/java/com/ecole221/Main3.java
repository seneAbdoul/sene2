package com.ecole221;

import com.ecole221.service.DBHelper;

import java.sql.ResultSet;

public class Main3 {
    public static void main(String[] args) {

        try {
            System.out.println("voici la liste des classes");
            DBHelper db =  DBHelper.getInstance();
            String sql = "select * from classe";
            ResultSet rs = db.excuteSelect(sql, null);
            while (rs.next()){
                System.out.println(rs.getInt("id")
                        +" / "+rs.getString("libelle")
                        +" / "+rs.getString("code")
                        +" / "+rs.getInt("frais_inscription")
                        +" / "+rs.getInt("mensualite")
                        +" / "+rs.getInt("autres_frais")
                        +" / "+rs.getInt("filiere_id"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
