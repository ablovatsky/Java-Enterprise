package by.avectis.contracts.facade.impl;

import by.avectis.contracts.ServiceUtil;
import by.avectis.contracts.dao.contracts.ContractDAO;
import by.avectis.contracts.dao.contracts.ContractStateDAO;
import by.avectis.contracts.dao.employment.EmploymentDAO;
import by.avectis.contracts.dao.laborIntensity.LaborIntensityDAO;
import by.avectis.contracts.dao.timeSheet.TimeSheetDAO;
import by.avectis.contracts.dao.worker.WorkerDAO;
import by.avectis.contracts.dto.worker.WorkerDTO;
import by.avectis.contracts.dto.worker.modelDTO.ShortInfoWorker;
import by.avectis.contracts.facade.TimeSheetFacadeService;
import by.avectis.contracts.facade.model.ShortEmployment;
import by.avectis.contracts.facade.model.ShortTimeSheet;
import by.avectis.contracts.facade.model.TimeSheetDate;
import by.avectis.contracts.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

@Transactional
@Service("TimeSheetFacade")
public class TimeSheetFacadeImpl implements TimeSheetFacadeService{

    @Autowired
    private TimeSheetDAO timeSheetDAO;

    @Autowired
    private LaborIntensityDAO laborIntensityDAO;

    @Autowired
    private EmploymentDAO employmentDAO;

    @Autowired
    private WorkerDAO workerDAO;

    @Autowired
    private WorkerDTO workerDTO;

    @Autowired
    private ContractDAO contractDAO;

    @Autowired
    private ContractStateDAO contractStateDAO;

    @Override
    public TimeSheetDate getNewTimeSheetData() {
        TimeSheetDate timeSheet = new TimeSheetDate();
        LocalDate date = getDateFirstDayPreviousMonth();
        initializeTimeSheet(timeSheet, date);
        return timeSheet;
    }

    @Override
    public TimeSheetDate getTimeSheetData(long timeSheetId) {
        TimeSheetDate fTimeSheet = new TimeSheetDate();
        TimeSheet timeSheet = timeSheetDAO.findById(timeSheetId);
        if (timeSheet != null) {
            initializeTimeSheet(fTimeSheet, timeSheet.getDate());
            Set<Employment> employments = employmentDAO.findByTimeSheet(timeSheet);
            fTimeSheet.setEmploymentSet(employments);
            Set<String> selectedContractNumberSet = new LinkedHashSet<>();
            employments.forEach(employment -> selectedContractNumberSet.add(employment.getContract().getNumber()));
            fTimeSheet.setSelectedContractNumberSet(selectedContractNumberSet);
            return fTimeSheet;
        }
        return null;
    }

    @Override
    public Set<ShortTimeSheet> getTimeSheets(int count, int setNumber, String sortingColumn, int sortingType) {
        Worker worker = workerDAO.findBySSOID(ServiceUtil.getPrincipal());
        if (worker != null) {
            return ShortTimeSheet.getShortTimeSheet(timeSheetDAO.findAllBySubdivision(worker.getSubdivision(), count, setNumber, sortingColumn, sortingType));
        }
        return null;
    }

    @Override
    public void saveTimeSheet(Set<ShortEmployment> shortEmploymentSet) {
        LocalDate date = getDateFirstDayPreviousMonth();
        TimeSheet timeSheet = timeSheetDAO.findByDate(date);
        if (timeSheet != null) {
            timeSheetDAO.remove(timeSheet);
        }
        newTimeSheet(shortEmploymentSet, date);
    }

    @Override
    public void editTimeSheet(Set<ShortEmployment> shortEmploymentSet, long timeSheetId) {
        TimeSheet timeSheet = timeSheetDAO.findById(timeSheetId);
        if (timeSheet != null) {
            LocalDate date = timeSheet.getDate();
            timeSheetDAO.remove(timeSheet);
            newTimeSheet(shortEmploymentSet, date);
        }
    }

    private void initializeTimeSheet(TimeSheetDate timeSheet, LocalDate date) {
        Subdivision subdivision = workerDAO.findBySSOID(ServiceUtil.getPrincipal()).getSubdivision();
        Set<LaborIntensity> laborIntensities = laborIntensityDAO.findAllForTimeSheet(subdivision, date, contractStateDAO.findByName("Открыт"));
        if (laborIntensities != null) {
            Set<ShortInfoWorker> workers = workerDTO.getWorkerSetBySubdivision(subdivision.getId(), 1000, 1, "ssoId", 0);
            Set<String> contractsNumber = new LinkedHashSet<>();
            laborIntensities.forEach(laborIntensity -> contractsNumber.add(laborIntensity.getContract().getNumber()));
            timeSheet.setWorkerSet(workers);
            timeSheet.setContractNumberSet(contractsNumber);
        }
    }

    private LocalDate getDateFirstDayPreviousMonth() {
        LocalDate dateNow = LocalDate.now();
        String DateString = dateNow.getYear() + "-" + ServiceUtil.addZero(String.valueOf(dateNow.getMonthValue() - 1)) + "-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(DateString, formatter);
    }

    private void newTimeSheet(Set<ShortEmployment> shortEmploymentSet, LocalDate date){
        TimeSheet timeSheet = new TimeSheet();
        timeSheet.setSubdivision(workerDAO.findBySSOID(ServiceUtil.getPrincipal()).getSubdivision());
        timeSheet.setState(true);
        timeSheet.setDate(date);
        timeSheetDAO.add(timeSheet);
        final TimeSheet entity =  timeSheetDAO.findByDate(date);
        shortEmploymentSet.forEach(shortEmployment ->  {
            Employment employment = new Employment();
            employment.setWorker(workerDAO.findBySSOID(shortEmployment.getWorkerSSOID()));
            employment.setContract(contractDAO.findByNumber(shortEmployment.getContractNumber()));
            employment.setHoursWorked(shortEmployment.getWorkedHours());
            employment.setDate(date);
            employment.setTimeSheet(entity);
            employmentDAO.add(employment);
        });
    }

}
