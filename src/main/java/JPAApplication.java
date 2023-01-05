import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAApplication {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("coursework");
        EntityManager manager = factory.createEntityManager();

        Mountain mountain = new Mountain();
        mountain.setTitle("Альпы");
        mountain.setCountry("Швейцария");
        mountain.setHeight(4000);

        manager.getTransaction().begin();
        manager.persist(mountain);
        manager.getTransaction().commit();
        factory.close();
        manager.close();











 /*
        // Entity классами управляет EntityManager
        // EntityManager создается EntityManagerFactory
        // EntityManagerFactory должна создаваться в try с ресурсами или у объекта д.б. вызван метод close
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("coursework"); // название таблицы в persistence-unit name

        // EntityManager должен создаваться в try с ресурсами или у объекта д.б. вызван метод close
        EntityManager manager = factory.createEntityManager();

        Mountain mountain = new Mountain(); // добавляемый объект
        mountain.setTitle("Альпы");         // столбцы
        mountain.setCountry("Швейцария");   // столбцы

        manager.getTransaction().begin(); // добавление информации об объекте в таблицу

        manager.persist(mountain); // запросы, которые дб выполнены

        // можно вызвать manager.getTransaction().rollback()
        // и отменить выполнение всех запросов
        // подтверждение транзакции, обновление таблиц,
        // состояние БД меняется согласно запросам
        manager.getTransaction().commit();

        Mountain mountainFromDB = manager.find(Mountain.class, 1); // запросить объект из таблицы соответствующий классу Mountain.class, первичный ключ "1"
        System.out.println("mountainFromDB: " + mountainFromDB.getTitle());

        // обновление информации в таблице
        mountainFromDB.setTitle("Эльбрус");
        manager.getTransaction().begin();
        manager.merge(mountainFromDB);
        manager.getTransaction().commit();
        System.out.println("mountainFromDB: " + mountainFromDB.getTitle());

        // удаление из таблицы
       manager.getTransaction().begin();
        manager.remove(mountainFromDB); // в параметре для удаления можно прописать первичный ключ(@Id) или имя объекта
        manager.getTransaction().commit();
*/



    }
}
