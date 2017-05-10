package by.avectis.contracts.dao.timeSheet;


import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.TimeSheet;

import java.time.LocalDate;
import java.util.Set;

public interface TimeSheetDAO {

    void add(TimeSheet timeSheet) throws DaoException;

    void update(TimeSheet timeSheet) throws DaoException;

    void remove(TimeSheet timeSheet) throws DaoException;

    TimeSheet findById(long id) throws DaoException;

    TimeSheet findByDate(LocalDate date);

    Set<TimeSheet> findAllBySubdivision(Subdivision subdivision, int count, int setNumber, String sortingColumn, int sortingType);

    Set<TimeSheet> findAll(int count, int setNumber, String sortingColumn, int sortingType);

    Set<TimeSheet> findAllByState(Boolean state, int count, int setNumber, String sortingColumn, int sortingType);

    long getCountRows();

    long getCountRowsBySubdivision(Subdivision subdivision);

    long getCountRowsByState(Boolean state);


}
