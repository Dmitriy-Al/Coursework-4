package data;

import data.Dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ru.ifmo.AlpinistsGroup;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlpinistGroupDao implements Dao<AlpinistsGroup, Integer> {

    private EntityManager manager;

    public AlpinistGroupDao(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public void add(AlpinistsGroup group) {
        manager.getTransaction().begin();
        manager.persist(group);
        manager.getTransaction().commit();
        manager.close();
    }


    @Override
    public AlpinistsGroup getById(Integer integer) {
        AlpinistsGroup groupFromDB = manager.find(AlpinistsGroup.class, integer);
        manager.close();
        return groupFromDB;
    }


    @Override
    public List<AlpinistsGroup> getInfo() {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<AlpinistsGroup> criteriaQuery = criteriaBuilder.createQuery(AlpinistsGroup.class);
        Root<AlpinistsGroup> root = criteriaQuery.from(AlpinistsGroup.class);
        criteriaQuery.select(root);
        TypedQuery<AlpinistsGroup> query = manager.createQuery(criteriaQuery);
        return query.getResultList();
    }


    // Всё что написано тут и ниже дичь, понимаю что всё должно делаться через запросы,
    // но из-за вылетающей ошибки, которую так и не починил, проверить работу методов
    // невозможно, чтобы не написать ещё большей ерунды, делаю так...

    public List<Integer> getLastTimeGroupId(AlpinistsGroup group) {
        LocalDateTime date = LocalDateTime.now();
        List<Integer> groupId = new ArrayList<>();

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<AlpinistsGroup> criteriaQuery = criteriaBuilder.createQuery(AlpinistsGroup.class);
        Root<AlpinistsGroup> root = criteriaQuery.from(AlpinistsGroup.class);
        Predicate lastTime = criteriaBuilder.lessThan(root.get("date"), date);
        criteriaQuery.select(root).where(lastTime);
        TypedQuery<AlpinistsGroup> query = manager.createQuery(criteriaQuery);
        for (int i = 0; i < query.getResultList().size(); i++) {
            groupId.add(query.getResultList().get(i).getId());
        }
        return groupId;
    }


    public List<Integer> getGroupIdByMountain(String mountainTitle) {
        List<Integer> groupId = new ArrayList<>();

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<AlpinistsGroup> criteriaQuery = criteriaBuilder.createQuery(AlpinistsGroup.class);
        Root<AlpinistsGroup> root = criteriaQuery.from(AlpinistsGroup.class);
        criteriaQuery.select(root);
        TypedQuery<AlpinistsGroup> query = manager.createQuery(criteriaQuery);
        for (int i = 0; i < query.getResultList().size(); i++) {
            if (query.getResultList().get(i).getMountain().getTitle().equals(mountainTitle)) {
                groupId.add(query.getResultList().get(i).getId());
            }
        }
        return groupId;
    }


    public List<Integer> getGroupIdByHeight(int from, int to) {
        List<Integer> groupId = new ArrayList<>();

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<AlpinistsGroup> criteriaQuery = criteriaBuilder.createQuery(AlpinistsGroup.class);
        Root<AlpinistsGroup> root = criteriaQuery.from(AlpinistsGroup.class);
        criteriaQuery.select(root);
        TypedQuery<AlpinistsGroup> query = manager.createQuery(criteriaQuery);
        for (int i = 0; i < query.getResultList().size(); i++) {
            if (query.getResultList().get(i).getMountain().getHeight() > from &&
                    query.getResultList().get(i).getMountain().getHeight() < to) {
                groupId.add(query.getResultList().get(i).getId());
            }
        }
        return groupId;
    }


}
