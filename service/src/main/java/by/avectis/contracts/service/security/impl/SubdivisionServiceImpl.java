package by.avectis.contracts.service.security.impl;

import by.avectis.contracts.dao.security.SubdivisionDao;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.security.SubdivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("subdivisionService")
@Transactional
public class SubdivisionServiceImpl implements SubdivisionService {

    @Autowired
    private SubdivisionDao dao;

    @Override
    public List<Subdivision> findAll() {
        return dao.findAll();
    }

    @Override
    public Subdivision findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public Subdivision findById(Long id) {
        return dao.findById(id);
    }
}
