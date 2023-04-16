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

    public List<Pessoa> listAll() {

        try (PreparedStatement prep = connection.prepareStatement("SELECT * FROM pessoa")) {
            ResultSet resultSet = prep.executeQuery();
            return convertResultSetToListPessoas(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Pessoa listById(int id) {
        try (PreparedStatement prep = connection.prepareStatement("SELECT * FROM pessoa WHERE id = "+id)) {
            ResultSet resultSet = prep.executeQuery();
            resultSet.next();
            return convertResultSetToPessoas(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Pessoa pessoa){
        try(PreparedStatement prep = connection.prepareStatement("UPDATE pessoa SET nome = ?, idade = ? WHERE id = ?")){
            prep.setString(1, pessoa.getNome());
            prep.setInt(2, pessoa.getIdade());
            prep.setInt(3, pessoa.getId());

            System.out.println(prep.executeUpdate());
            return true;
            
        } catch(SQLException e){
            System.out.println("PessoaDAO.update: "+e.getMessage());
        }
        return false;
    }

    public boolean create(Pessoa pessoa){
        try(PreparedStatement prep = connection.prepareStatement("INSERT INTO pessoa(nome, idade) VALUES(?, ?)")){

            prep.setString(1, pessoa.getNome());
            prep.setInt(2, pessoa.getIdade());

            System.out.println(prep.executeUpdate());
            return true;
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id){
        try (PreparedStatement prep = connection.prepareStatement("DELETE FROM pessoa WHERE id = "+id)) {
            prep.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<Pessoa> convertResultSetToListPessoas(ResultSet rs) {
        List<Pessoa> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(convertResultSetToPessoas(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    private Pessoa convertResultSetToPessoas(ResultSet rs) {
        Pessoa pessoa = new Pessoa();
        try {
            pessoa.setId(rs.getInt("id"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setIdade(rs.getInt("idade"));
        } catch (SQLException e) {
            System.out.println("ResultSetConverter: " + e.getMessage());
        }
        return pessoa;
    }

}
