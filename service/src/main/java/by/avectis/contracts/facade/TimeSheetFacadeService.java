package by.avectis.contracts.facade;


import by.avectis.contracts.facade.model.ShortEmployment;
import by.avectis.contracts.facade.model.ShortTimeSheet;
import by.avectis.contracts.facade.model.TimeSheetDate;

import java.util.Set;

public interface TimeSheetFacadeService {

    TimeSheetDate getNewTimeSheetData();

    TimeSheetDate getTimeSheetData(long timeSheetId);

    Set<ShortTimeSheet> getTimeSheets(int count, int setNumber, String sortingColumn, int sortingType);

    void saveTimeSheet(Set<ShortEmployment> shortEmploymentSet);

    void editTimeSheet(Set<ShortEmployment> shortEmploymentSet, long timeSheetId);
}
