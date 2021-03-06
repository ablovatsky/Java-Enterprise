package by.avectis.contracts.dto.subdivision.modelDTO;


import by.avectis.contracts.model.Subdivision;

import java.io.Serializable;

public class ShortInfoSubdivision implements Serializable {

    private long id;

    private String name;

    public ShortInfoSubdivision(Subdivision subdivision) {
        id = subdivision.getId();
        name = subdivision.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortInfoSubdivision that = (ShortInfoSubdivision) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
