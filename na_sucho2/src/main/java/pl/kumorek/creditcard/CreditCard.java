package pl.kumorek.creditcard;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal credit;
    private BigDecimal balance;

    public CreditCard(String cardNumber) {
        this.balance = new BigDecimal(0);
    }

    public void assignCreditLimit(BigDecimal credit) {
        if (this.credit != null) {
            throw new CreditCantBeModifiedException();
        }

        this.credit = credit;
    }

    public BigDecimal getBalance() {return balance;}

    public BigDecimal getCredit() {return credit;}

    public void withdraw(BigDecimal value) {
        if (! itCanAfford(value)) {
            throw new NotEnougntFoundsException();
        }

        this.balance = this.balance.subtract(value);
    }

    private boolean itCanAfford(BigDecimal value) {
        return this.balance.add(this.credit).compareTo(value) > 0;
    }
}
