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
            return System.getenv(ENV_ACCESS_TOKEN);
        }

        public static String getPublicKey() {
            return System.getenv(PUBLIC_KEY);
        }
    }
}
