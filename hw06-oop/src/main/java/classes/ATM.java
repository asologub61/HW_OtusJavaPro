package classes;

import java.util.Map;

public interface ATM {

    public void putBanknotesContainer(BanknotesContainer banknotesContainer);

    public void removeBanknotesContainer(Banknotes banknotes);

    public void getMoneySum(int sum);

    public int getBalance();

    public String getStatisticForATM();

    public Map<Integer, Integer> getStatisticForBanknotes();
}
