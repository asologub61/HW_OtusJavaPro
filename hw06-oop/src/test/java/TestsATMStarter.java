
import classes.ATM;
import classes.ATMStarter;
import classes.Banknotes;
import classes.BanknotesContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsATMStarter {
    static ATM atm;

    @BeforeEach
    public void createNewObject() {
        atm = new ATMStarter();
    }

    @Test
    @DisplayName("Добавление контейнера с деньгами заданного номинала")
    public void testPutBanknotesContainer() {
        atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N50, 3));
        Assertions.assertEquals("В банкомате осталось:\n" +
                        "3 банкнот с номиналом 50\n" +
                        "Доступный баланс: 150",
                atm.getStatisticForATM());
    }

    @Test
    @DisplayName("Добавление нескольких контейнеров с разными номиналами")
    public void testPutBanknotesContainerWithDifferentNominals() {
        atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N50, 3));
        atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N200, 2));
        Assertions.assertEquals("В банкомате осталось:\n" +
                        "3 банкнот с номиналом 50\n" +
                        "2 банкнот с номиналом 200\n" +
                        "Доступный баланс: 550",
                atm.getStatisticForATM());
    }

    @Test
    @DisplayName("Добавление контейнера одного и того же номинала дважды")
    public void testPutTwoEqualsBanknotesContainers() {
        atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N100, 5));
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
                () -> atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N100, 3)));
        Assertions.assertEquals("Ячейка с номиналом 100 уже установлена", runtimeException.getMessage());
    }

    @Test
    @DisplayName("В банкомате нет контейнеров")
    public void testPutEmptyBanknotesContainer() {
        Assertions.assertEquals("В банкомате осталось:\n" +
                        "Доступный баланс: 0",
                atm.getStatisticForATM());
    }

    @Test
    @DisplayName("Удаление контейнера с  банкнотами")
    public void testRemoveBanknotesContainer() {
        atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N500, 10));
        atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N1000, 5));
        atm.removeBanknotesContainer(Banknotes.N1000);
        Assertions.assertEquals("В банкомате осталось:\n" +
                "10 банкнот с номиналом 500\n" +
                "Доступный баланс: 5000", atm.getStatisticForATM());
    }

    @Test
    @DisplayName("Удаляем несуществующий контейнер")
    public void testRemoveNotExistingBanknotesContainer() {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
                () -> atm.removeBanknotesContainer(Banknotes.N2000));
        Assertions.assertEquals("Нельзя удалить. Ячейка с номиналом 2000 отсутствует", runtimeException.getMessage());
    }

    @Test
    @DisplayName("Кладем по 200, снимаем 1000")
    public  void  testGetMoneySum(){
        atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N200, 10));
        atm.getMoneySum(1000);
        Assertions.assertEquals("В банкомате осталось:\n" +
                "5 банкнот с номиналом 200\n" +
                "Доступный баланс: 1000", atm.getStatisticForATM());
    }

    @Test
    @DisplayName("Кладем 5000 и 1000, 5000 мало, снимаеи 1000ми")
    public void testGetMoneySum2(){

        atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N5000,1));
        atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N1000,15));

        atm.getMoneySum(10000);

        Assertions.assertEquals(1, atm.getStatisticForBanknotes().get(Banknotes.N5000.getNominal()));
        Assertions.assertEquals(5, atm.getStatisticForBanknotes().get(Banknotes.N1000.getNominal()));
    }

    @Test
    @DisplayName("Кладем 2*500, 3*50, 2*100  и проверяем баланс")
    public  void testGetBalance(){
        atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N500,2));
        atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N50, 3));
        atm.putBanknotesContainer(new BanknotesContainer(Banknotes.N100, 2));
        Assertions.assertEquals(1350, atm.getBalance());
    }

}
