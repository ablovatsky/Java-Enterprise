package by.avectis.contracts.dao.subdivision;


import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Subdivision;
import java.util.Set;

public interface SubdivisionDAO {

    void add(Subdivision subdivision) throws DaoException;

    void update(Subdivision subdivision) throws DaoException;

    void remove(Subdivision subdivision) throws DaoException;

    Set<Subdivision> findAll();

    Subdivision findByName(String name) ;

    Subdivision findById(Long id) throws DaoException;
}
