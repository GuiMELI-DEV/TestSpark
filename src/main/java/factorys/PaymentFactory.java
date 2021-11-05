package factorys;

import com.mercadopago.resources.Payment;

public class PaymentFactory {
    public Payment createPayment() {
        return new Payment();
    }
}
