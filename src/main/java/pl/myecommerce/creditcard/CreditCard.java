package pl.myecommerce.creditcard;
import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal limit;
    private BigDecimal balance;
    private int withdrawalNumber = 10;

    public CreditCard(String cardNumber){}
    public void assignCredit(BigDecimal credit){
        if (isCreditAlreadyAssigned()){
            throw new CantReassignException();
        }
        if (isCreditBelowThreshold(credit)){
            throw new CreditBelowThresholdException();
        }
        this.limit = credit;
        this.balance = credit;
    }

    public void withdraw(BigDecimal amount){
        if (isWithdrawalOverLimit(amount)) throw new WithdrawalOverLimitException();
        if (isWithdrawalOverCurrentAmount(amount)){
            throw new NotEnoughMoneyException();
        }
        if (isWithdrawalNumberLegal()){
            throw new illegalNumberOfWithdrawalsException();
        }
        this.balance.subtract(amount);

    }

    private boolean isWithdrawalNumberLegal() {
        this.withdrawalNumber = this.withdrawalNumber - 1;
        return this.withdrawalNumber < 0;
    }

    private boolean isWithdrawalOverCurrentAmount(BigDecimal amount) {
        return this.balance.compareTo(amount) < 0;
    }

    private boolean isWithdrawalOverLimit(BigDecimal amount) {
        return this.limit.compareTo(amount) < 0;
    }

    private boolean isCreditBelowThreshold(BigDecimal credit) {
        return BigDecimal.valueOf(100).compareTo(credit) >= 0;
    }

    private boolean isCreditAlreadyAssigned() {
        return this.limit != null;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }
}
