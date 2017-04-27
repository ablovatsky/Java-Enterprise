package by.avectis.contracts.dao.subdivision;


import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Subdivision;

import java.util.List;

public interface  SubdivisionDao  {

    void addSubdivision(Subdivision subdivision) throws DaoException;

    void updateSubdivision(Subdivision subdivision) throws DaoException;

    void deleteSubdivision(Subdivision subdivision) throws DaoException;

    List<Subdivision> findAllSubdivisions() throws DaoException;

    Subdivision findSubdivisionByName(String name) throws DaoException;

    Subdivision findSubdivisionById(Long id) throws DaoException;
}
