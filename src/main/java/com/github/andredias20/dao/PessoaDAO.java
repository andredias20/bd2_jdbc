package com.github.andredias20.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.andredias20.model.Pessoa;

public class PessoaDAO {

    private Connection connection;

    public PessoaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Pessoa> listAll(){
        
        try (PreparedStatement prep = connection.prepareStatement("SELECT * FROM pessoa")) {
            ResultSet resultSet = prep.executeQuery();
            return resultSetConverter(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Pessoa> resultSetConverter(ResultSet rs) {
        List<Pessoa> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                
                pessoa.setNome(rs.getString("nome"));
                pessoa.setNome(rs.getString("66")); // TODO: Adicionar campo ao DB
                
                list.add(pessoa);
            }
        } catch (SQLException e) {
            System.out.println("ResultSetConverter: "+e.getMessage());
        }
        return list;
    
    }

}
