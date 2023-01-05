import jakarta.persistence.*;


@Entity
@Table (name = "table_mountains")
public class Mountain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, unique = true, nullable = false)
    private String title;

    @Column(length = 30, unique = true, nullable = false)
    private String country;

    @Column(nullable = false)
    private int height;


    //Гора создаётся с названием (не менее 4 символов), страной (не менее 4 символов) и высотой (не менее 100 метров)
    public Mountain(){}

    public void setTitle(String title) {
        if(title.length() >= 4){
            this.title = title;
        } else {
            throw new IllegalArgumentException("Название горы должно быть не менее 4 символов");
        }
    }

    public void setCountry(String country) {
        if(country.length() >= 4){
            this.country = country;
        } else {
            throw new IllegalArgumentException("Название страны должно быть не менее 4 символов");
        }
    }

    public void setHeight(int height) {
        if(height >= 100){
            this.height = height;
        } else {
            throw new IllegalArgumentException("Высота горы должна быть не менее 100 метров");
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCountry() {
        return country;
    }

    public int getHeight() {
        return height;
    }
}


