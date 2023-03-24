package classes;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ATMStarter implements ATM {

    private final Map<Integer, BanknotesContainer> allBanknotesContainer = new TreeMap<>(Integer::compareTo);

    @Override
    public void putBanknotesContainer(BanknotesContainer banknotesContainer) {
        if (allBanknotesContainer.containsKey(banknotesContainer.getNominalOfBanknotes())) {
            throw new RuntimeException("Ячейка с номиналом " + banknotesContainer.getNominalOfBanknotes() + " уже установлена");
        }
        allBanknotesContainer.put(banknotesContainer.getNominalOfBanknotes(), banknotesContainer);
    }

    @Override
    public void removeBanknotesContainer(Banknotes banknotes) {
        if (allBanknotesContainer.remove(banknotes.getNominal()) == null) {
            throw new RuntimeException("Нельзя удалить. Ячейка с номиналом " + banknotes.getNominal() + " отсутствует");
        }
        allBanknotesContainer.remove(banknotes.getNominal());
    }

    @Override
    public void getMoneySum(int sum) {
        int neededBanknotes;

        for (Integer nominal : allBanknotesContainer.keySet()) {
            if (sum % nominal == 0) {
                neededBanknotes = sum / nominal;
                if (isBanknotesContainerHaveBanknotes(nominal, neededBanknotes)) {
                    try {
                        allBanknotesContainer.get(nominal).removeBanknotes(neededBanknotes);
                    }catch (RuntimeException e){
                        System.err.println(e.getMessage());
                        allBanknotesContainer.get(nominal).addBanknotes(neededBanknotes);
                    }
                    System.out.println("Выдано " + sum);
                    return;
                }
            }
        }
        throw new RuntimeException("В банкомате нет денег");
    }

    @Override
    public int getBalance() {
        int balance = 0;
        for (Integer nominal : allBanknotesContainer.keySet()) {
            balance += nominal * allBanknotesContainer.get(nominal).getCountBanknotesInContainer();
        }
        return balance;
    }

    @Override
    public String getStatisticForATM() {
        StringBuilder statisticLine = new StringBuilder();
        statisticLine.append("В банкомате осталось:\n");
        for (BanknotesContainer banknotesContainer : allBanknotesContainer.values()) {
            statisticLine.append(String.format("%d банкнот с номиналом %d\n", banknotesContainer.getCountBanknotesInContainer(),
                    banknotesContainer.getNominalOfBanknotes()));
        }
        statisticLine.append("Доступный баланс: " + getBalance());
        return statisticLine.toString();
    }


    public boolean isBanknotesContainerHaveBanknotes(int nominal, int neededBanknotes) {
        return allBanknotesContainer.get(nominal).getCountBanknotesInContainer() >= neededBanknotes;
    }

    @Override
    public Map<Integer, Integer> getStatisticForBanknotes() {
        Map<Integer, Integer> statistic = new HashMap<>();
        allBanknotesContainer.forEach((k, v) -> statistic.put(v.getNominalOfBanknotes(), v.getCountBanknotesInContainer()));
        return statistic;
    }
}

