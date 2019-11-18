package Systems;

import Entity.Payment;

public class FinancialInstitutionSystem {

    public FinancialInstitutionSystem() {};

    public void submitPayment(Payment payment)
    {
        System.out.printf("Payment %d sent to the bank\n", payment.getPaymentID());
    }
}