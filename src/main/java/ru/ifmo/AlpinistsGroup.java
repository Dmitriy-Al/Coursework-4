package ru.ifmo;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "table_group")
public class AlpinistsGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "members_count")
    private int membersCount;

    @OneToOne()
    private Mountain mountain;

    @Column(nullable = false)
    @OneToMany()
    private Alpinist[] alpinists;

    @Column
    private LocalDateTime date;

    @Column
    private boolean isRecruit;


    public AlpinistsGroup() {
    }

    public AlpinistsGroup(int year, int month, int day, int hour, int minute, boolean isRecruit, Mountain mountain, Alpinist... alpinists) {
        this.isRecruit = isRecruit;
        this.membersCount = alpinists.length;
        this.mountain = mountain;
        this.alpinists = alpinists;
        this.date = date.of(year, month, day, hour, minute);
    }

    public boolean isRecruit() {
        return isRecruit;
    }

    public int getMembers() {
        return membersCount;
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

    @Override
    public String toString() {
        return "ru.ifmo.AlpinistsGroup{" +
                "id=" + id +
                ", membersCount=" + membersCount +
                ", mountain=" + mountain +
                ", alpinists=" + Arrays.toString(alpinists) +
                ", isRecruit=" + isRecruit +
                '}';
    }
}
