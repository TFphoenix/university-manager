package dao;

import entities.Utilizator;

import java.util.List;

public interface UtilizatorDao {

    void createUtilizatorTable();

    void insert(Utilizator utilizator);

    Utilizator selectByID(int id);

    Utilizator selectByMail(String mail);

    List<Utilizator> selectAll();

    void delete(int id);

    void update(Utilizator utilizator, int id);
}
