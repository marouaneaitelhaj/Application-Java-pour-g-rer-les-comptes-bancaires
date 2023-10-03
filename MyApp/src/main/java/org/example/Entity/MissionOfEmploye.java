package org.example.Entity;

import java.time.LocalDate;

public class MissionOfEmploye {
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Mission mission;
    private Employe employe;

    public MissionOfEmploye(LocalDate dateStart, LocalDate dateEnd, Mission mission, Employe employe) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.mission = mission;
        this.employe = employe;
    }

    public MissionOfEmploye() {
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

}
