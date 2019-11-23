package Systems;

import Entity.Payment;

public class FinancialInstitutionSystem {

    public FinancialInstitutionSystem() {};

    public void submitPayment(Payment payment)
    {
        System.out.printf("Payment for listing %d sent to the bank, by landlord %d\n", payment.getListingId(), payment.getLandlordId());
    }
}