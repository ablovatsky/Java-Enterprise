package by.avectis.contracts.service.timeSheet.timeSheetImpl;

import by.avectis.contracts.ServiceUtil;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.subdivision.SubdivisionDAO;
import by.avectis.contracts.dao.timeSheet.TimeSheetDAO;
import by.avectis.contracts.dao.worker.WorkerDAO;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.TimeSheet;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.exception.ServiceException;
import by.avectis.contracts.service.timeSheet.TimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service("TimeSheetService")
@Transactional
public class TimeSheetServiceImpl implements TimeSheetService {

    @Autowired
    private TimeSheetDAO timeSheetDAO;

    @Autowired
    private SubdivisionDAO subdivisionDao;

    @Autowired
    private WorkerDAO workerDAO;

    @Override
    public void add(TimeSheet timeSheet) throws ServiceException {
        try {
            timeSheetDAO.add(timeSheet);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void update(TimeSheet timeSheet) throws ServiceException {
        try {
            TimeSheet entity = timeSheetDAO.findById(timeSheet.getId());
            if (entity != null) {
                entity.setState(timeSheet.getState());
                entity.setDate(timeSheet.getDate());
                entity.setSubdivision(timeSheet.getSubdivision());
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void remove(long id) throws ServiceException {
        try {
            TimeSheet entity = timeSheetDAO.findById(id);
            if (entity != null) {
                timeSheetDAO.remove(entity);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public TimeSheet findById(long id) throws ServiceException {
        try {
           return timeSheetDAO.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public Set<TimeSheet> findAllBySubdivision(String subdivisionName, int count, int setNumber, String sortingColumn, int sortingType) {
        Subdivision subdivision = subdivisionDao.findByName(subdivisionName);
        if (subdivision != null) {
            return timeSheetDAO.findAllBySubdivision(subdivisionDao.findByName(subdivisionName), count, setNumber, sortingColumn, sortingType);
        }
        return null;
    }

    @Override
    public Set<TimeSheet> findAll(int count, int setNumber, String sortingColumn, int sortingType) {
        return timeSheetDAO.findAll(count, setNumber, sortingColumn, sortingType);
    }

    @Override
    public Set<TimeSheet> findAllByState(boolean state, int count, int setNumber, String sortingColumn, int sortingType) {
        return timeSheetDAO.findAllByState(state, count, setNumber, sortingColumn, sortingType);
    }


    @Override
    public Set<TimeSheet> getTimeSheets(int count, int setNumber, String sortingColumn, int sortingType) {
        Worker worker = workerDAO.findBySSOID(ServiceUtil.getPrincipal());
        if (worker != null) {
           return timeSheetDAO.findAllBySubdivision(worker.getSubdivision(), count, setNumber, sortingColumn, sortingType);
        }
        return null;
    }

    @Override
    public long getCountRows() {
        return timeSheetDAO.getCountRows();
    }

    @Override
    public long getCountRowsBySubdivision(String subdivisionName) {
        Subdivision subdivision = subdivisionDao.findByName(subdivisionName);
        if (subdivision != null) {
            return timeSheetDAO.getCountRowsBySubdivision(subdivision);
        }
        return 0;
    }

    @Override
    public long getCountRowsByState(Boolean state) {
        return timeSheetDAO.getCountRowsByState(state);
    }
}
