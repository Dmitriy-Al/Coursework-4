package ru.ifmo;

public class Alpinist {

    private String address;
    private String name;
    private int age;
    private int id;

    private AlpinistsGroup alp;

    //Альпинист создаётся с именем (не менее 3 символов), адресом проживания (не менее 5 символов), возрастом.
    public Alpinist() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        if (address.length() < 5) {
            throw new IllegalArgumentException("Адрес проживания должен содержать не менее 5 символов");
        } else {
            this.address = address;
        }
    }

    public void setName(String name) {
        if (name.length() < 3) {
            throw new IllegalArgumentException("Имя альпиниста должно содержать не менее 3 символов");
        } else {
            this.name = name;
        }
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAlpinistsGroup(AlpinistsGroup alpinistsGroup) {
        this.alp = alpinistsGroup;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ru.ifmo.Alpinist{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", alp=" + alp +
                '}';
    }
}
