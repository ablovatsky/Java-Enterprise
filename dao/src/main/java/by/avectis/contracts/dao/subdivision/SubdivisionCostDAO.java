package by.avectis.contracts.dao.subdivision;


import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.SubdivisionCost;

import java.time.LocalDate;
import java.util.Set;

public interface SubdivisionCostDAO {

    void add (SubdivisionCost subdivisionCost) throws DaoException;

    void update (SubdivisionCost subdivisionCost) throws DaoException;

    void remove (SubdivisionCost subdivisionCost) throws DaoException;

    SubdivisionCost findById(long id) throws DaoException;

    SubdivisionCost findBySubdivisionAndDate(Subdivision subdivision, LocalDate date);

    Set<SubdivisionCost> findAllByDate(LocalDate date);

}
