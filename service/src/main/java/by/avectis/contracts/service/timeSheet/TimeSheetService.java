package by.avectis.contracts.service.timeSheet;


import by.avectis.contracts.model.TimeSheet;
import by.avectis.contracts.service.exception.ServiceException;

import java.time.LocalDate;
import java.util.Set;

public interface TimeSheetService {

    void add(TimeSheet timeSheet) throws ServiceException;

    void update(TimeSheet timeSheet) throws ServiceException;

    void remove(long id) throws ServiceException;

    TimeSheet findById(long id) throws ServiceException;

    Set<TimeSheet> findAllBySubdivision(String subdivisionName, int count, int setNumber, String sortingColumn, int sortingType);

    Set<TimeSheet> findAll(int count, int setNumber, String sortingColumn, int sortingType);

    Set<TimeSheet> findAllByState(boolean state, int count, int setNumber, String sortingColumn, int sortingType);

    Set<TimeSheet> getTimeSheets(int count, int setNumber, String sortingColumn, int sortingType);

    long getCountRows();

    long getCountRowsBySubdivision(String subdivisionName);

    long getCountRowsByState(Boolean state);
}
