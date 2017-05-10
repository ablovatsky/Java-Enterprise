package by.avectis.contracts.facade.model;

import by.avectis.contracts.model.TimeSheet;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class ShortTimeSheet implements Serializable {

    long id;

    String subdivision;

    boolean state;

    LocalDate date;

    public ShortTimeSheet() {
    }

    public ShortTimeSheet(TimeSheet timeSheet) {
        id = timeSheet.getId();
        subdivision = timeSheet.getSubdivision().getName();
        state = timeSheet.getState();
        date = timeSheet.getDate();
    }

    public static Set<ShortTimeSheet> getShortTimeSheet(Set<TimeSheet> timeSheets) {
        Set<ShortTimeSheet> shortTimeSheetSet = new LinkedHashSet<>();
        timeSheets.forEach(timeSheet -> shortTimeSheetSet.add(new ShortTimeSheet(timeSheet)));
        return shortTimeSheetSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
