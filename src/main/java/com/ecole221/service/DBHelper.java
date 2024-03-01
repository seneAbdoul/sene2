package com.ecole221.service;

import java.sql.*;

public class DBHelper {
    private static DBHelper dbHelper;
    public static DBHelper getInstance(){
        if(dbHelper == null){
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }
    private DBHelper(){

    }
    private Connection cnx;
    private PreparedStatement pstmt;
    private void openConnection() throws SQLException {
        try {
            if (cnx == null || cnx.isClosed()) {
                String url="jdbc:mysql://localhost:3306/cours_java";
                cnx= DriverManager.getConnection(url,"root","");
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private void myPreparedQuery(String sql) throws SQLException {
        try {
            pstmt = cnx.prepareStatement(sql);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public ResultSet excuteSelect(String sql, Object[] parameters) throws SQLException {
        try {
            openConnection();
            myPreparedQuery(sql);
            if (parameters != null) {
                for(int i = 0; i < parameters.length; i++) {
                    pstmt.setObject((i+1), parameters[0]);
                }
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    public int excuteMaj(String sql, Object[] parameters) throws SQLException {
        try {
            openConnection();
            myPreparedQuery(sql);
            if (parameters != null) {
                for(int i = 0; i < parameters.length; i++) {
                    pstmt.setObject((i+1), parameters[i]);
                }
            }
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    public void closeConnection() throws SQLException {
        if (pstmt != null && !pstmt.isClosed()){
            pstmt.close();
            pstmt = null;
        }
        if (cnx != null && !cnx.isClosed()){
            cnx.close();
            cnx = null;
        }
    }
    public void beginTransaction() throws SQLException {
        cnx.setAutoCommit(false);
    }
    public void endTransaction() throws SQLException {
        cnx.setAutoCommit(true);
    }
    public void validateTransaction() throws SQLException {
        cnx.commit();
    }
    public void rollbackTransaction() throws SQLException {
        cnx.rollback();
    }
    public int executeUpdate(String sql, Object[] parameters) throws SQLException {

        try {
            openConnection();
            myPreparedQuery(sql);
            if (parameters != null) {
                for(int i = 0; i < parameters.length; i++) {
                    pstmt.setObject((i+1), parameters[i]);
                }
            }
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void executeDelete(String sql, Object[] parameters) throws SQLException {
        try {
            openConnection();
            myPreparedQuery(sql);
            if (parameters != null) {
                for(int i = 0; i < parameters.length; i++) {
                    pstmt.setObject((i+1), parameters[i]);
                }
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeConnection();
        }
    }

}
