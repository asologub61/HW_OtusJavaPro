package classes;

import classes.Banknotes;
import classes.BanknotesContainer;

import java.util.Map;

public interface ATM {

    public void putBanknotesContainer(BanknotesContainer banknotesContainer);

    public void removeBanknotesContainer(Banknotes banknotes);

    public void getMoneySum(int sum);

    public int getBalance();

    public String getStatisticForTests();

    public Map<Integer, Integer> getStatisticForBanknotes();
}
