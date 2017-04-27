package by.avectis.contracts.dao.Util;


import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

public class UtilDAO {

    public static long getRowCountInTable(Criteria criteria) {
        Object result = criteria.setProjection(Projections.rowCount()).uniqueResult();
        return Long.parseLong(result.toString());
    }
}
