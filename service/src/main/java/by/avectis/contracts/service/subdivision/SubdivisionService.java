package by.avectis.contracts.service.subdivision;

import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.exception.ServiceException;
import java.util.Set;


public interface SubdivisionService {

    void add(Subdivision subdivision) throws ServiceException;

    void update(Subdivision subdivision) throws ServiceException;

    void remove(long id) throws ServiceException;

    Set<Subdivision> findAll();

    Subdivision findByName(String name) throws ServiceException;

    Subdivision findById(Long id) throws ServiceException;

    boolean isSubdivisionUnique(long id) throws ServiceException;

    boolean isSubdivisionUnique(String name) throws ServiceException;
}
