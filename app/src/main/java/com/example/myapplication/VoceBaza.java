package com.example.myapplication;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VoceBaza {
    static Dao<Voce,Integer>voceDao;
    public static void main(String[] args) {

        ConnectionSource connectionSource=null;
        try {
            connectionSource=new JdbcConnectionSource("jdbc:sqlite:proba.db");
            TableUtils.dropTable(connectionSource,Voce.class,true);
            TableUtils.createTable(connectionSource,Voce.class);

            voceDao= DaoManager.createDao(connectionSource,Voce.class);
            Voce jabuka=new Voce("jabuka","zelena");
            Voce limun=new Voce("limun","zut");
            Voce jagoda=new Voce("jagoda","crvena");
            voceDao.create(jabuka);
            voceDao.create(limun);
            voceDao.create(jagoda);

            List<Voce>voces=voceDao.queryForAll();
            for (Voce voce:voces){
                System.out.println(voce.getNaziv());
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            assert connectionSource != null;
            connectionSource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
