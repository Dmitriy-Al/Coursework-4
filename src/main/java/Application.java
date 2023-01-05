import java.util.List;

public class Application {
    public static void main(String[] args) {

        Mountain elbrus = new Mountain();
        elbrus.setTitle("Эльбрус");
        elbrus.setCountry("Россия");

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


        AlpinistsGroup alpinistGroup = new AlpinistsGroup(2023, 2, 15, 17, 30, true, 3, elbrus, climber1, climber2, climber3);

        AlpinistDao alpinistDao = new AlpinistDao();
        List<Alpinist> alpinists = alpinistDao.getInfo();

        for(Alpinist a : alpinists){
            System.out.println("result = Name: " + a.getName() + ", age = " + a.getAge() + ", ID = " + a.getId());
        }
  /*
        alpinistDao.createTable();
        alpinistDao.delete(11);
        alpinistDao.add(climber3);
        alpinistDao.changeAlpinistName(climber1);
        alpinistDao.getDeterminedAge();

   */

        for(Alpinist a : alpinists){
            System.out.println("result after = Name: " + a.getName() + ", age = " + a.getAge() + ", ID = " + a.getId());
        }



    }
}


        /*

        Создать sql файл со следующими запросами:
Создать sql файл со следующими запросами:
создание таблицы Альпинист +
Добавление данных в таблицу Альпинист +
Изменение имени альпиниста +
Получение идентификатором и имен альпинистов старше 30 и младше 50 лет +
Получение названий гор, высота которых больше указанной
Получение страны, в которой расположена гора с определенным названием
Получение идентификаторов, которые совершали восхождения в прошлом
Получение идентификаторов групп, которые совершали восхождения на гору с определенным названием
Получение идентификатором и имен альпинистов, которые совершали восхождения на горы, высота которых от ... до ...
Подключить библиотеку, которая проверяет значения свойств с помощью аннотаций


         */

