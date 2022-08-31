package com.example.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "transaction_amount", nullable = false)
    private Double amount;

    @Column(name = "transaction_fee", nullable = false)
    private Double transactionFee;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private CreditCard creditCard;

    public Transaction() {

    }

    public Transaction(Double amount, Double currentBalance) {
        this.amount = amount;
    }

    public Transaction(Long transactionId, LocalDateTime transactionDate, Double amount, Double transactionFee, Double currentBalance, CreditCard creditCard) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.transactionFee = transactionFee;
        this.creditCard = creditCard;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(Double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction )) return false;
        return transactionId != null && transactionId.equals(((Transaction ) o).getTransactionId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    private java.sql.Timestamp parseTimestamp(String timestamp) {
        SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                ", transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                ", transactionFee=" + transactionFee +
                ", creditCard=" + creditCard +
                '}';
    }
}