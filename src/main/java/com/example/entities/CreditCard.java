package com.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "credit_cards",
        uniqueConstraints = @UniqueConstraint(name = "CreditCardTableUniqueConstraints", columnNames = {"card_number"})
)
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;

    @Column(name = "pin", nullable = false)
    private Integer pin;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "credit_card_company", nullable = false, length = 50)
    private String creditCardCompany;

    @Column(name = "balance")
    private Double balance = 0.0;

    @Column(name = "activated")
    private Boolean activated = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "creditCard", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public CreditCard() {

    }

    public CreditCard(Integer pin, String cardNumber, String creditCardCompany) {
        this.pin = pin;
        this.cardNumber = cardNumber;
        this.creditCardCompany = creditCardCompany;
    }

    public CreditCard(Long cardId, int pin, String cardNumber, String creditCardCompany, Double balance, Boolean activated, User user, List<Transaction> transactions) {
        this.cardId = cardId;
        this.pin = pin;
        this.cardNumber = cardNumber;
        this.creditCardCompany = creditCardCompany;
        this.balance = balance;
        this.activated = activated;
        this.user = user;
        this.transactions = transactions;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCreditCardCompany() {
        return creditCardCompany;
    }

    public void setCreditCardCompany(String creditCardCompany) {
        this.creditCardCompany = creditCardCompany;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void addBalance(Double amount) {
        this.balance += amount;
    }

    public Boolean isActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public void activateCard() {
        this.activated = true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setCreditCard(this);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
        transaction.setCreditCard(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard )) return false;
        return cardId != null && cardId.equals(((CreditCard) o).getCardId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardId=" + cardId +
                ", pin=" + pin +
                ", cardNumber='" + cardNumber + '\'' +
                ", creditCardCompany='" + creditCardCompany + '\'' +
                ", balance=" + balance +
                ", activated=" + activated +
                ", user=" + user +
                ", transactions=" + transactions +
                '}';
    }
}
