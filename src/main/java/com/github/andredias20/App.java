package com.github.andredias20;

import java.util.List;

import com.github.andredias20.dao.PessoaDAO;
import com.github.andredias20.factory.ConnectionFactory;
import com.github.andredias20.model.Pessoa;

public final class App {
    public static void main(String[] args) {
        
        ConnectionFactory factory = new ConnectionFactory();

        PessoaDAO dao = new PessoaDAO(factory.getConnection());
        
        List<Pessoa> listAll = dao.listAll();
        listAll.forEach(System.out::println);
        
    }
}
