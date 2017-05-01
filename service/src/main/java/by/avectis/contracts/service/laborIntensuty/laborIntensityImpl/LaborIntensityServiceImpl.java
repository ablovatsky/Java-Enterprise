package by.avectis.contracts.service.laborIntensuty.laborIntensityImpl;

import by.avectis.contracts.dao.contracts.ContractDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.laborIntensity.LaborIntensityDAO;
import by.avectis.contracts.dao.subdivision.SubdivisionDao;
import by.avectis.contracts.model.Contract;
import by.avectis.contracts.model.LaborIntensity;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.exception.ServiceException;
import by.avectis.contracts.service.laborIntensuty.LaborIntensityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service("LaborIntensityService")
@Transactional
public class LaborIntensityServiceImpl implements LaborIntensityService{

    @Autowired
    private LaborIntensityDAO laborIntensityDAO;

    @Autowired
    private ContractDAO contractDAO;

    @Autowired
    private SubdivisionDao subdivisionDao;

    @Override
    public void add(LaborIntensity laborIntensity) throws ServiceException {
        try {
            laborIntensityDAO.add(laborIntensity);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void update(LaborIntensity laborIntensity) throws ServiceException {
        try {
            LaborIntensity entity = findById(laborIntensity.getId());
            if (entity != null) {
                entity.setContract(laborIntensity.getContract());
                entity.setSubdivision(laborIntensity.getSubdivision());
                entity.setPlannedCost(laborIntensity.getPlannedCost());
                entity.setPlannedLaborIntensity(laborIntensity.getPlannedLaborIntensity());
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void remove(Long id) throws ServiceException {
        try {
            LaborIntensity entity = findById(id);
            if (entity != null) {
              laborIntensityDAO.remove(entity);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public LaborIntensity findById(Long id) throws ServiceException {
        try {
            return laborIntensityDAO.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public Set<LaborIntensity> findAll() {
        return laborIntensityDAO.findAll();
    }

    @Override
    public Set<LaborIntensity> findAllByContract(String contractNumber) {
        Contract contract = contractDAO.findByNumber(contractNumber);
        if (contract != null) {
            return laborIntensityDAO.findAllByContract(contract);
        }
        return null;
    }

    @Override
    public Set<LaborIntensity> findAllBySubdivision(String subdivisionName) {
        Subdivision subdivision = subdivisionDao.findByName(subdivisionName);
        if (subdivision != null) {
            return laborIntensityDAO.findAllBySubdivision(subdivision);
        }
        return null;
    }
}
