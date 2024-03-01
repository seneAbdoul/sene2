package com.ecole221;

import com.ecole221.service.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClasseService {
    public boolean findClasseByLibelle(String libelle) throws SQLException {
        try {
            DBHelper db =  DBHelper.getInstance();
            String sql = "select * from classe where libelle = ?";
            Object[] par = {libelle};
            ResultSet rs = db.excuteSelect(sql, par);
            if (rs.next()){
                rs.close();
                return true;
            }
            rs.close();
            return false;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void addClasse(String libelle,String code,int frais_inscription,int mensualite,int autres_frais,int filiere_id) throws SQLException{
        try {
            DBHelper db =  DBHelper.getInstance();
            String sql = "INSERT INTO classe VALUES(null,?,?,?,?,?,?)";
            Object[] par = {libelle,code,frais_inscription,mensualite,autres_frais,filiere_id};
            db.excuteMaj(sql, par);
        } catch (SQLException e) {
            throw e;
        }

    }
}
