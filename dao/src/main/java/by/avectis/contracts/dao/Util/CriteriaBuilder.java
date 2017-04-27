package by.avectis.contracts.dao.Util;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;


public class CriteriaBuilder {

    private Criteria criteria;

    public CriteriaBuilder(Criteria criteria) {
        this.criteria = criteria;
    }

    public CriteriaBuilder addOrder(String sortingColumn, int sortingType) {
        if (sortingType == 0) {
            criteria.addOrder(Order.asc(sortingColumn));
        } else {
            criteria.addOrder(Order.desc(sortingColumn));
        }
        return this;
    }

    public CriteriaBuilder addCertainNumberRows(int count, int setNumber) {
        criteria.setMaxResults(count);
        criteria.setFirstResult((setNumber - 1) * count);
        return this;
    }

    public Criteria getCriteria() {
        return criteria;
    }
}
