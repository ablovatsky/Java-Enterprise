package by.avectis.contracts.service.subdivision.impl;

import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.subdivision.SubdivisionDao;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.exception.ServiceException;
import by.avectis.contracts.service.subdivision.SubdivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("subdivisionService")
@Transactional
public class SubdivisionServiceImpl implements SubdivisionService {

    @Autowired
    private SubdivisionDao dao;

    @Override
    public void addSubdivision(Subdivision subdivision) throws ServiceException {
        try {
            dao.addSubdivision(subdivision);
        } catch (ServiceException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void updateSubdivision(Subdivision subdivision) throws ServiceException {
        try {
            Subdivision entity = dao.findSubdivisionById(subdivision.getId());
            if (entity != null) {
                entity.setName(subdivision.getName());
            }
        } catch (ServiceException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void deleteSubdivision(long id) throws ServiceException {
        try {
            Subdivision subdivision = dao.findSubdivisionById(id);
            if (subdivision != null) {
                dao.deleteSubdivision(subdivision);
            }
        } catch (ServiceException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public List<Subdivision> findAllSubdivisions() throws ServiceException {
        try {
            return dao.findAllSubdivisions();
        } catch (ServiceException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public Subdivision findSubdivisionByName(String name) throws ServiceException {
        try {
            return dao.findSubdivisionByName(name);
        } catch (ServiceException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public Subdivision findSubdivisionById(Long id) throws ServiceException {
        try {
            return dao.findSubdivisionById(id);
        } catch (ServiceException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public boolean isSubdivisionUnique(long id) {
        try {
            Subdivision subdivision = findSubdivisionById(id);
            return  subdivision == null;
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public boolean isSubdivisionUnique(String name) throws ServiceException {
        try {
            Subdivision subdivision = findSubdivisionByName(name);
            return  subdivision == null;
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }
}
