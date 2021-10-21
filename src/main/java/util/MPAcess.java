package util;

import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.resources.IdentificationType;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.customer.card.PaymentMethod;

public class MPAcess {
    public static class MercadoPago {
        private static final String ENV_ACCESS_TOKEN = "ENV_ACCESS_TOKEN";
        private static final String PUBLIC_KEY = "PUBLIC_KEY";

        private MercadoPago() { }

        public static void access() throws MPConfException {
            com.mercadopago.MercadoPago.SDK.setAccessToken(MPAcess.MercadoPago.getEnvAccessToken());
            new PaymentMethod();
            new IdentificationType();
            new Payment();
            new Preference();

        };

        public static String getEnvAccessToken() {
            return "TEST-6827747143070079-102021-5a79ac2f87777182e3c8f3a4abc4def6-813008278";
        }

        public static String getPublicKey() {
            return System.getenv(PUBLIC_KEY);
        }
    }
}
