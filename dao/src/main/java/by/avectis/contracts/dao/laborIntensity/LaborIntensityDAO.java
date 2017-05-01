package by.avectis.contracts.dao.laborIntensity;


import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Contract;
import by.avectis.contracts.model.LaborIntensity;
import by.avectis.contracts.model.Subdivision;

import java.util.Set;

public interface LaborIntensityDAO {

    void add(LaborIntensity laborIntensity) throws DaoException;

    void update(LaborIntensity laborIntensity) throws DaoException;

    void remove(LaborIntensity laborIntensity) throws DaoException;

    LaborIntensity findById(Long id) throws DaoException;

    Set<LaborIntensity> findAll();

    Set<LaborIntensity> findAllByContract(Contract contract);

    Set<LaborIntensity> findAllBySubdivision(Subdivision subdivision);

}
