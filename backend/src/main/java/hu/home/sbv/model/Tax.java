package hu.home.sbv.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="taxes")
public class Tax {

    @Id
    @GeneratedValue
    private long id;

    @JoinColumn(name="tax_type")
    @ManyToOne
    private TaxType taxType;

    @Column(name="value")
    private BigDecimal value;

    @Column(name="paid")
    private boolean isPaid;

    @ManyToOne
    @JoinColumn(name="rsu")
    private Rsu rsu;

    @Column(name="due_date")
    private Date dueDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Rsu getRsu() {
        return rsu;
    }

    public void setRsu(Rsu rsu) {
        this.rsu = rsu;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
