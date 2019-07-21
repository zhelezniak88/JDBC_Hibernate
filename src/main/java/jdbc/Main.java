package jdbc;


import jdbc.dao.CarDAO;
import jdbc.dao.ClientDAO;
import jdbc.dao.DAOFactory;
import jdbc.dao.IDAOFactory;
import jdbc.entity.Car;
import jdbc.entity.Client;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IDAOFactory factory = DAOFactory.getInstance();
        CarDAO carDAO = factory.getCarDAO();

/*
        Car car = new Car();
        car.setMark("Lada");
        car.setModel("2106");
        car.setPrice(1000);

        carDAO.add(car);
*/

/*
        List<Car> cars = carDAO.getAll();

        for(Car c : cars){
            System.out.println(c.getId() + " " + c.getMark() + " " + c.getModel() + " " + c.getPrice());
        }
*/

/*
        Car car = carDAO.getById(3);
        System.out.println(car.getId() + " " + car.getMark() + " " + car.getModel() + " " + car.getPrice());
*/

//       carDAO.updatePrice(90000, 1);

//        carDAO.remove(10);

        ClientDAO clientDAO = factory.getClientDAO();

        Client client = new Client();
/*
        client.setName("Dmitriy");
        client.setAge(22);
        client.setPhone("0934552277");

        clientDAO.add(client);
*/

/*
        List<Client> clients = clientDAO.getAll();

        for(Client c : clients){
            System.out.println(c.getId() + " " + c.getName() + " " + c.getAge() + " " + c.getPhone());
        }
*/

/*
        client = clientDAO.getClientById(4);
        System.out.println(client.getId() + " " + client.getName() + " " + client.getAge() + " " + client.getPhone());
*/

//        clientDAO.updatePhone("0934445556", 4);

//        clientDAO.remove(4);
    }
}

