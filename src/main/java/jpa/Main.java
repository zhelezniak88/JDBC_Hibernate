package jpa;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientFactory factory = new ClientFactory();

        Client client = new Client();
        client.setName("Dmytro");
        client.setAge(30);
        client.setPhone("0732123344");

        factory.add(client);

        Client client1 = factory.getById(3);
        System.out.println(client1.getId() + " " + client1.getName() + " "
                + client1.getAge() + " " + client1.getPhone());

        factory.remove(client1);

        List<Client> clients = factory.getAll();
        for (Client c : clients){
            System.out.println(c.getId() + " " + c.getName() + " "
                    + c.getAge() + " " + c.getPhone());
        }

        Client client2 = factory.getById(4);
        client2.setPhone("0771112233");
        factory.add(client2);
    }
}
