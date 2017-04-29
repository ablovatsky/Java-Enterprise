package by.avectis.contracts.service.subdivision.impl;

import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.subdivision.SubdivisionDao;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.exception.ServiceException;
import by.avectis.contracts.service.subdivision.SubdivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service("subdivisionService")
@Transactional
public class SubdivisionServiceImpl implements SubdivisionService {

    @Autowired
    private SubdivisionDao dao;

    @Override
    public void add(Subdivision subdivision) throws ServiceException {
        try {
            dao.add(subdivision);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void update(Subdivision subdivision) throws ServiceException {
        try {
            Subdivision entity = dao.findById(subdivision.getId());
            if (entity != null) {
                entity.setName(subdivision.getName());
            }
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void remove(long id) throws ServiceException {
        try {
            Subdivision subdivision = dao.findById(id);
            if (subdivision != null) {
                dao.remove(subdivision);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public Set<Subdivision> findAll() throws ServiceException {
        return dao.findAll();
    }

    @Override
    public Subdivision findByName(String name) throws ServiceException {
        return dao.findByName(name);
    }

    @Override
    public Subdivision findById(Long id) throws ServiceException {
        try {
            return dao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public boolean isSubdivisionUnique(long id) {
        try {
            Subdivision subdivision = findById(id);
            return  subdivision == null;
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public boolean isSubdivisionUnique(String name) {
        Subdivision subdivision = findByName(name);
        return  subdivision == null;
    }
}
