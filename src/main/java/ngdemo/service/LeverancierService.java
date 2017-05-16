package ngdemo.service;

import java.util.List;
import ngdemo.dao.LeverancierDAO;
import ngdemo.domain.Leverancier;

public class LeverancierService {

    public List<Leverancier> getLeveranciers() {
        LeverancierDAO leverancierDao = new LeverancierDAO();
        List <Leverancier>  leveranciers = leverancierDao.findAllLeveranciers();
        
        return leveranciers;
    }
}
