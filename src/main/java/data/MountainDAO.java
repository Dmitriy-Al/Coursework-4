package data;

import data.Dao;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ru.ifmo.Mountain;

import java.util.List;

public class MountainDAO implements Dao<Mountain, Integer> {

    private EntityManager manager;

    public MountainDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Mountain mountain) {
        manager.getTransaction().begin();
        manager.persist(mountain);
        manager.getTransaction().commit();
        manager.close();
    }


    @Override
    public Mountain getById(Integer integer) {
        Mountain mountainFromDB = manager.find(Mountain.class, integer);
        manager.close();
        return mountainFromDB;
    }

    public List<Mountain> getMountainHigher(int height) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Mountain> criteriaQuery = criteriaBuilder.createQuery(Mountain.class);
        Root<Mountain> root = criteriaQuery.from(Mountain.class);
        Predicate heightAbove = criteriaBuilder.greaterThan(root.get("height"), height);
        criteriaQuery.select(root).where(heightAbove);
        TypedQuery<Mountain> query = manager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public String getCountry(String title) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Mountain> criteriaQuery = criteriaBuilder.createQuery(Mountain.class);
        Root<Mountain> root = criteriaQuery.from(Mountain.class);
        Predicate mountainTitle = criteriaBuilder.equal(root.get("title"), title);
        criteriaQuery.select(root).where(mountainTitle);
        TypedQuery<Mountain> query = manager.createQuery(criteriaQuery);
        return query.getSingleResult().getCountry();
    }

    @Override
    public List<Mountain> getInfo() {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Mountain> criteriaQuery = criteriaBuilder.createQuery(Mountain.class);
        Root<Mountain> root = criteriaQuery.from(Mountain.class);
        criteriaQuery.select(root);
        TypedQuery<Mountain> query = manager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
