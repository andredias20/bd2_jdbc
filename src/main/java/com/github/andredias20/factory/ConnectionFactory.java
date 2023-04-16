package com.github.andredias20.factory;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private Properties persistenceProperties = new Properties();

    private String dbms;

    private String driver;

    private String databaseName;
    private String address;
    private String port;
    private String user;
    private String password;
    
    
    public ConnectionFactory() {
        try (FileReader reader = new FileReader("src/resources/persistence.properties")) {
            persistenceProperties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        initialConfiguration();

    }

    private void initialConfiguration(){

        LoadProperties();

        if(dbms.equals("postgresql")){
            this.driver = "org.postgresql.Driver";
        }else if(dbms.equals("mysql")){
            this.driver = "com.mysql.cj.jdbc.Driver";
        }else{
            System.out.println("No suitable Driver found");
        }
    
    }


    private void LoadProperties(){
        this.dbms = persistenceProperties.getProperty("DBMS");

        this.databaseName = persistenceProperties.getProperty("DATABASE");
        this.address = persistenceProperties.getProperty("ADDRESS");
        this.port = persistenceProperties.getProperty("PORT");

        this.user = persistenceProperties.getProperty("USER");
        this.password = persistenceProperties.getProperty("PASSWORD");
    }

    private String  getConnectionString() {
        return "jdbc:"+this.dbms+"://"+ this.address +":" + this.port +"/"+ this.databaseName;
    }

    private void LoadDrivers(){
        try{
            Class.forName(this.driver);
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
     
    public Connection getConnection() {
        
        String db_url = getConnectionString();

        LoadDrivers();

        Connection connection = null;

        try{
            connection = DriverManager.getConnection(db_url, this.user, this.password);
        } catch (SQLException e) {
            System.out.println("ConnectionFactory.getConnection: " + e.getMessage());
        }

        return connection;

    }

}
