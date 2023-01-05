import java.time.LocalDateTime;


public class AlpinistsGroup {

    private boolean isRecruit;
    private int members;
    private int id;
    private Mountain mountain;
    private Alpinist[] alpinists;
    private LocalDateTime date;

    public AlpinistsGroup(int year, int month, int day, int hour, int minute, boolean isRecruit, int members, Mountain mountain, Alpinist... alpinists){
        this.isRecruit = isRecruit;
        this.members = members;
        this.mountain = mountain;
        this.alpinists = alpinists;
        this.date = date.of(year, month, day, hour, minute);
    }

    public boolean isRecruit() {
        return isRecruit;
    }

    public int getMembers() {
        return members;
    }

    public Mountain getMountain() {
        return mountain;
    }

    public Alpinist[] getAlpinists() {
        return alpinists;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getId() {
        return id;
    }
}
