package teamOne.racinggame.models;

public class Bet {
    private Integer balance;
    private Integer bet;

    public Bet() {
    }

    public Bet(Integer balance, Integer bet) {
        this.balance = balance;
        this.bet = bet;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public Integer caculateBalanceRemain(){
        Integer result = balance - bet;
        return result;
    }
    public boolean validateBalance(Integer balance){
        boolean flag = false;
        if(balance >= 0){
            flag = true;
        }
        return flag;
    }
    private boolean validateBet(double inputBet){
        boolean flag = false;
        if (inputBet > 0){
            flag = true;
        }
        return  false;
    }
    public Integer convertBetValidate(double inputBet){
        Integer bet = 0;
        if(validateBet(inputBet)){
            bet = (int) inputBet;
        }
        return bet;
    }
    
}
