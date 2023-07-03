package br.com.diego.apifinancehub.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User payer;

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User payee;

    @Column(columnDefinition = "DECIMAL", nullable = false)
    private float value;

    @CreationTimestamp
    @Column(updatable = false)
    private Date date;

    public Transaction() {
    }

    public Transaction(User payer, User payee, float value) {
        this.payer = payer;
        this.payee = payee;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public User getPayee() {
        return payee;
    }

    public void setPayee(User payee) {
        this.payee = payee;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", payer=" + payer +
                ", payee=" + payee +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}

