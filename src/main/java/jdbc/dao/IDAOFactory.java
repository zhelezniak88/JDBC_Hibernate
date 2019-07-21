package jdbc.dao;

public interface IDAOFactory {

    CarDAO getCarDAO();

    ClientDAO getClientDAO();
}
