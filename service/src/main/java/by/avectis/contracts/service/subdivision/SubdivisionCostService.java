package by.avectis.contracts.service.subdivision;


import by.avectis.contracts.model.SubdivisionCost;
import by.avectis.contracts.service.exception.ServiceException;

import java.time.LocalDate;
import java.util.Set;

public interface SubdivisionCostService {

    void add (Set<SubdivisionCost> subdivisionCostSet) throws ServiceException;

    void update (Set<SubdivisionCost> subdivisionCostSet) throws ServiceException;

    void remove (SubdivisionCost subdivisionCost) throws ServiceException;

    SubdivisionCost findById(long id) throws ServiceException;

    SubdivisionCost findBySubdivisionAndDate(String name, LocalDate date);

    Set<SubdivisionCost> findAllByDate(LocalDate date);
}
