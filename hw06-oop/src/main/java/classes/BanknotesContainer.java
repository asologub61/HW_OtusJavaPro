package classes;

public class BanknotesContainer {

    private Banknotes nominalOfBanknotes;

    private int countBanknotesInContainer;

    public BanknotesContainer(Banknotes nominalOfBanknotes, int count){
        this.nominalOfBanknotes = nominalOfBanknotes;
        this.countBanknotesInContainer = count;
    }

    public void addBanknotes(int countForAddBanknotes){
        if(countForAddBanknotes < 1){
            throw new RuntimeException("Ошибка при добавлении банкнот");
        }
            countBanknotesInContainer += countForAddBanknotes;
    }

    public void  removeBanknotes(int countForRemoveBanknotes){
        if( 1 > countForRemoveBanknotes || countForRemoveBanknotes > countBanknotesInContainer ){
            throw new RuntimeException("Ошибка при извлечении банкнот");
        }
        countBanknotesInContainer -= countForRemoveBanknotes;
    }

    public int getNominalOfBanknotes(){
        return nominalOfBanknotes.getNominal();
    }

    public int getCountBanknotesInContainer(){
        return countBanknotesInContainer;
    }
}
