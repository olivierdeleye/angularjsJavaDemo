package ngdemo.service;

import static com.mchange.v2.c3p0.impl.C3P0Defaults.user;
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
