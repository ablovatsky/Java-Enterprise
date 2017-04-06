package by.avectis.contracts.dao.security;


import by.avectis.contracts.model.Subdivision;

import java.util.List;

public interface  SubdivisionDao  {

    List<Subdivision> findAll();

    Subdivision findByName(String name);

    Subdivision findById(Long id);
}
