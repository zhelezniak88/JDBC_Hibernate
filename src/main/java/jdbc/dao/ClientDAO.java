package jdbc.dao;

import jdbc.entity.Client;

import java.util.List;

public interface ClientDAO {

    void add(Client client);

    List<Client> getAll();

    Client getClientById(int id);

    void remove(int id);

    void remove(String name);

    void updatePhone(String phone, int id);

    void updatePhone(String phone, String name);
}

