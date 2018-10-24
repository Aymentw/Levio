package tn.esprit.twin.ninja.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Mandate")
public class Mandate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date StartDate;
    @Temporal(TemporalType.DATE)
    private Date EndDate;

    private float Montant ;
    @OneToMany(mappedBy = "mandate")
    private List<Ressource> listRessource;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public float getMontant() {
        return Montant;
    }

    public void setMontant(float montant) {
        Montant = montant;
    }

    public List<Ressource> getListRessource() {
        return listRessource;
    }

    public void setListRessource(List<Ressource> listRessource) {
        this.listRessource = listRessource;
    }
}
