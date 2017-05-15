package by.avectis.contracts.service.subdivision.impl;

import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.subdivision.SubdivisionCostDAO;
import by.avectis.contracts.dao.subdivision.SubdivisionDAO;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.SubdivisionCost;
import by.avectis.contracts.service.exception.ServiceException;
import by.avectis.contracts.service.subdivision.SubdivisionCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Transactional
@Service("SubdivisionCostService")
public class SubdivisionCostServiceImpl implements SubdivisionCostService {

    @Autowired
    private SubdivisionCostDAO subdivisionCostDAO;

    @Autowired
    private SubdivisionDAO subdivisionDAO;

    @Override
    public void add(Set<SubdivisionCost> subdivisionCostSet) throws ServiceException {
        try {
            subdivisionCostSet.forEach(subdivisionCost ->  subdivisionCostDAO.add(subdivisionCost));
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void update(Set<SubdivisionCost> subdivisionCostSet) throws ServiceException {
        try {
            for (SubdivisionCost subdivisionCost : subdivisionCostSet) {
                Long id = subdivisionCost.getId();
                if (id == null) {
                    subdivisionCostDAO.add(subdivisionCost);
                } else {
                    SubdivisionCost entity = subdivisionCostDAO.findById(id);
                    if (entity != null) {
                        entity.setDate(subdivisionCost.getDate());
                        entity.setCost(subdivisionCost.getCost());
                        entity.setSubdivision(subdivisionCost.getSubdivision());
                    }
                }
            }
        }  catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void remove(SubdivisionCost subdivisionCost) throws ServiceException {
        try {
            subdivisionCostDAO.remove(subdivisionCost);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public SubdivisionCost findById(long id) throws ServiceException {
        try {
            return subdivisionCostDAO.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public SubdivisionCost findBySubdivisionAndDate(String name, LocalDate date) {
        Subdivision subdivision = subdivisionDAO.findByName(name);
        return subdivisionCostDAO.findBySubdivisionAndDate(subdivision, date);
    }

    @Override
    public Set<SubdivisionCost> findAllByDate(LocalDate date) {
        return subdivisionCostDAO.findAllByDate(date);
    }
}
