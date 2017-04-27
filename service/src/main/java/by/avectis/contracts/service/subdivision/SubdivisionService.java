package by.avectis.contracts.service.subdivision;

import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.exception.ServiceException;

import java.util.List;


public interface SubdivisionService {

    void addSubdivision(Subdivision subdivision) throws ServiceException;

    void updateSubdivision(Subdivision subdivision) throws ServiceException;

    void deleteSubdivision(long id) throws ServiceException;

    List<Subdivision> findAllSubdivisions() throws ServiceException;

    Subdivision findSubdivisionByName(String name) throws ServiceException;

    Subdivision findSubdivisionById(Long id) throws ServiceException;

    boolean isSubdivisionUnique(long id) throws ServiceException;

    boolean isSubdivisionUnique(String name) throws ServiceException;
}
