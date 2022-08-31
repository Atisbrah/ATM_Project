package com.example.service;

import com.example.ATMMachine.DataOperations;
import com.example.entities.CreditCard;
import com.example.entities.Login;
import com.example.entities.Transaction;
import com.example.entities.User;
import com.example.repositories.CreditCardRepository;
import com.example.repositories.LoginRepository;
import com.example.repositories.TransactionRepository;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ATMService {

    private UserRepository userRepository;
    private LoginRepository loginRepository;
    private CreditCardRepository creditCardRepository;
    private TransactionRepository transactionRepository;

    private DataOperations dataOperations;

    @Autowired
    public ATMService(
            UserRepository userRepository, LoginRepository loginRepository, CreditCardRepository creditCardRepository,
            TransactionRepository transactionRepository, DataOperations dataOperations) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.creditCardRepository = creditCardRepository;
        this.transactionRepository = transactionRepository;
        this.dataOperations = dataOperations;
    }

    public void registerDataToDatabase(User user, Login login, CreditCard creditCard) {
        login.setUser(user);
        user.setLogin(login);
        user.addCreditCard(creditCard);
        creditCardRepository.save(creditCard);
        userRepository.save(user);
    }

    public void deleteAllRecordsFromAllRepos() {
        transactionRepository.deleteAll();
        creditCardRepository.deleteAll();
        loginRepository.deleteAll();
        userRepository.deleteAll();
    }

    public List<User> allUserAsList() {
        return userRepository.findAll();
    }

    public List<Login> allLoginAsList() {
        return loginRepository.findAll();
    }

    public List<CreditCard> allCreditCardAsList() {
        return creditCardRepository.findAll();
    }

    public List<Transaction> allTransactionList() {
        return transactionRepository.findAll();
    }

    public List<CreditCard> allCreditCardAsListByUserId(Long userId) {
        return allCreditCardAsList().stream().filter(x -> x.getUser().getUserId().equals(userId)).toList();
    }

    public List<Transaction> allTransactionAsListByCardId(Long cardId) {
        return allTransactionList().stream().filter(x -> x.getCreditCard().getCardId().equals(cardId)).toList();
    }

    public CreditCard getFirstCreditCardOfUser(Long userId) {
        List<CreditCard> creditCards = allCreditCardAsListByUserId(userId);
        return creditCards.get(0);
    }

    public boolean areAllTablesEmpty() {
        return userRepository.count() == 0 && loginRepository.count() == 0 && creditCardRepository.count() == 0;
    }

    public CreditCard findCreditCard(Long id) {
        return creditCardRepository.findById(id).get();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    ////////////////////////////////////////////////////////////////////
    //Felhasználói adat módosítás

    public void changeUserFirstname(User user, String newFirstname) {
        user.setFirstname(newFirstname);
        userRepository.save(user);
    }

    public void changeUserLastname(User user, String newLastname) {
        user.setLastname(newLastname);
        userRepository.save(user);
    }

    public void changeUserAddress(User user, String newAddress) {
        user.setAddress(newAddress);
        userRepository.save(user);
    }

    public void changeUserPhone(User user, Integer newPhone) {
        user.setPhone(newPhone);
        userRepository.save(user);
    }

    public void changeUserEmail(User user, String newEmail) {
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public void changeUserBirthDate(User user, LocalDate newBirthDate) {
        user.setBirthDate(newBirthDate);
        userRepository.save(user);
    }

    public void changeUserLoginUsername(User user, String newUsername) {
        user.getLogin().setUsername(newUsername);
        userRepository.save(user);
    }

    ////////////////////////////////////////////////////////////////////
    //Jelszó csere

    public void changeUserLoginPassword(User user, String newPassword) {
        user.getLogin().setPassword(newPassword);
        userRepository.save(user);
    }

    ////////////////////////////////////////////////////////////////////
    //PIN csere

    public void changeUsersCreditCardPin(CreditCard creditCard, Integer newPin) {
        creditCard.setPin(newPin);
        creditCardRepository.save(creditCard);
    }

    ////////////////////////////////////////////////////////////////////
    //pénzfeltöltés/levétel, általános metódus tranzakció létrehozásához és balance módosításához

    public void saveTransaction(CreditCard creditCard, Transaction transaction) {
        creditCard.addBalance(transaction.getAmount() - transaction.getTransactionFee());
        transaction.setCreditCard(creditCard);
        creditCard.addTransaction(transaction);
        transactionRepository.save(transaction);
        creditCardRepository.save(creditCard);
    }

    ////////////////////////////////////////////////////////////////////
    //kártyaaktiválás

    public void activateCard(CreditCard creditCard) {
        creditCard.activateCard();
        creditCardRepository.save(creditCard);
    }

    ////////////////////////////////////////////////////////////////////
    //új kártya regisztráció

    @Transactional
    public void registerCardToUser(User user, CreditCard creditCard) {
        user.addCreditCard(creditCard);
        creditCardRepository.save(creditCard);
        userRepository.save(user);
    }

    ////////////////////////////////////////////////////////////////////
    //kártya törlés

    @Transactional
    public CreditCard deleteGivenCard(User actUser, CreditCard actCreditCard, int chosenCardNumber) {
        List<CreditCard> creditCards = allCreditCardAsListByUserId(actUser.getUserId());

        CreditCard cardToDelete = creditCards.get(chosenCardNumber);
        Long cardToDeleteId = cardToDelete.getCardId();
        Long actCreditCardId = actCreditCard.getCardId();

        List<CreditCard> newCards = new ArrayList<>();

        for (CreditCard creditCard : creditCards) {
            if (!Objects.equals(creditCard.getCardId(), cardToDeleteId)) {
                newCards.add(creditCard);
            }
        }

        actUser.setCreditCards(newCards);
        userRepository.save(actUser);

        if (cardToDeleteId.equals(actCreditCardId)) {
            return creditCards.get(0);
        }
        return actCreditCard;
    }

    public void deleteCreditCard(CreditCard creditCard) {
        creditCardRepository.deleteById(creditCard.getCardId());

    }

    public CreditCard getCreditCardById(Long id) {
        return creditCardRepository.findById(id).get();

    }

    ////////////////////////////////////////////////////////////////////
    //felhasználó törlés

    public void deleteUser(User user) {
        userRepository.deleteById(user.getUserId());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
