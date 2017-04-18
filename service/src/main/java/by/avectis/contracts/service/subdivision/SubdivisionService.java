package by.avectis.contracts.service.subdivision;

import by.avectis.contracts.model.Subdivision;

import java.util.List;


public interface SubdivisionService {

    List<Subdivision> findAll();

    Subdivision findByName(String name);

    Subdivision findById(Long id);
}
