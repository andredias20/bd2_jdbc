package com.github.andredias20;

import com.github.andredias20.dao.PessoaDAO;
import com.github.andredias20.factory.ConnectionFactory;
import com.github.andredias20.model.Pessoa;

public final class App {
    public static void main(String[] args) {
        
        ConnectionFactory factory = new ConnectionFactory();

        PessoaDAO dao = new PessoaDAO(factory.getConnection());

        //dao.create(new Pessoa(0, "Mauro", 19));
        //dao.create(new Pessoa(0, "Claudineia", 20));

        //dao.update(new Pessoa(2,  "Mauro", 50));

        //dao.listAll().forEach(System.out::println); 


        dao.listAll().forEach(System.out::println);

        //dao.delete(1);
        
        System.out.println("========================");
        System.out.println("Depois do processo");

        dao.listAll().forEach(System.out::println);
        
    }
}
