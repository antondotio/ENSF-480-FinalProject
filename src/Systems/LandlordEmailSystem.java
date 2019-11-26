package Systems;

import Entity.EmailMessage;

public class LandlordEmailSystem {
    public LandlordEmailSystem() {};

    public void sendEmail(EmailMessage email)
    {
        System.out.printf("Successfully sent email from renter %s to landlord %s\n", email.getSender(), email.getReceiver());
    }
}
