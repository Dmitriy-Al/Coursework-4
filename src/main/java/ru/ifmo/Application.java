import data.AlpinistGroupDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(DbConnectionSettings.PERSISTENCE_UNIT_NAME);
        EntityManager manager = factory.createEntityManager();

        MountainDAO mountainDAO = new MountainDAO(manager);

        AlpinistGroupDao alpinistGroupDao = new AlpinistGroupDao(manager);

        AlpinistDao alpinistDao = new AlpinistDao();


        Mountain elbrus = new Mountain();
        elbrus.setTitle("Эльбрус");
        elbrus.setCountry("Россия");
        elbrus.setHeight(5642);


        Alpinist climber1 = new Alpinist();
        Alpinist climber2 = new Alpinist();
        Alpinist climber3 = new Alpinist();

        climber1.setAddress("Санкт-Петербург");
        climber1.setAge(22);
        climber1.setName("Петя");

        climber2.setAddress("Липецк");
        climber2.setAge(33);
        climber2.setName("Вася");

        climber3.setAddress("Сыктывкар");
        climber3.setAge(55);
        climber3.setName("Коля");

        //public AlpinistsGroup(int year, int month, int day, int hour, int minute, boolean isRecruit, Mountain mountain, Alpinist... alpinists)
        AlpinistsGroup someAlpinistGroup = new AlpinistsGroup(2023, 2, 15, 17, 30, true, elbrus, climber1, climber2, climber3);

        List<Alpinist> alpinistsFromDb = alpinistDao.getInfo();
        for (Alpinist a : alpinistsFromDb) {
            System.out.println("result = Name: " + a.getName() + ", age = " + a.getAge() + ", ID = " + a.getId());
        }

 /*        Создать sql файл со следующими запросами:

        создание таблицы Альпинист
        Добавление данных в таблицу Альпинист
        Изменение имени альпиниста
        Получение идентификатором и имен альпинистов старше 30 и младше 50 лет
        Получение названий гор, высота которых больше указанной
        Получение страны, в которой расположена гора с определенным названием
        Получение идентификаторов, которые совершали восхождения в прошлом
        Получение идентификаторов групп, которые совершали восхождения на гору с определенным названием
        Получение идентификатором и имен альпинистов, которые совершали восхождения на горы, высота которых от ... до ...
        Подключить библиотеку, которая проверяет значения свойств с помощью аннотаций    */


        // создание таблицы Альпинист
        alpinistDao.createTable();

        // Добавление данных в таблицу Альпинист
        alpinistDao.add(climber1);

        // Получение идентификатором и имен альпинистов старше 30 и младше 50 лет
        alpinistsFromDb = alpinistDao.getDeterminedAgeAlpinists();

        // Изменение имени альпиниста
        alpinistDao.changeAlpinistName(climber1);

        mountainDAO.add(elbrus);

        // Получение названий гор, высота которых больше указанной
        List<Mountain> mountainsAbove = mountainDAO.getMountainHigher(3000);

        // Получение страны, в которой расположена гора с определенным названием
        String country = mountainDAO.getCountry("Россия");

        alpinistGroupDao.add(someAlpinistGroup);

        // Получение идентификаторов (групп), которые совершали восхождения в прошлом
        List<Integer> getLastTimeGroupId = alpinistGroupDao.getLastTimeGroupId(someAlpinistGroup);

        // Получение идентификаторов групп, которые совершали восхождения на гору с определенным названием
        List<Integer> getGroupIdByMountain = alpinistGroupDao.getGroupIdByMountain("Альпы");

        // Получение идентификатором и имен альпинистов, которые совершали восхождения на горы, высота которых от ... до ...
        List<Integer> getGroupIdByHeight = alpinistGroupDao.getGroupIdByHeight(1000, 5000);

    }
}






