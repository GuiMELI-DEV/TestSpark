package controller

import com.mercadopago.resources.Payment
import org.mockito.Mockito
import spark.Request
import spark.Response
import spock.lang.Shared
import spock.lang.Specification
import util.MPAcess

class PaymentControllerTest extends Specification {
    @Shared
    Response response

    def "create payment"() {

        given:
            Request request = Mockito.mock(Request.class);
            Mockito.when(request.body()).thenReturn("{\n" +
                    "        \"transaction_amount\": 10,\n" +
                    "        \"description\": \"teste\",\n" +
                    "        \"payment_method_id\": \"bolbradesco\",\n" +
                    "        \"payer\": {\n" +
                    "            \"email\": \"guilherme.lemos+813008278@mercadolivre.com\",\n" +
                    "            \"identification\":{\n" +
                    "                \"type\":\"CPF\",\n" +
                    "                \"number\": \"15635614680\"\n" +
                    "            },\n" +
                    "            \"first_name\": \"Test\",\n" +
                    "            \"last_name\": \"Test\",\n" +
                    "            \"address\": {\n" +
                    "                \"zip_code\": \"08820390\",\n" +
                    "                \"street_name\": \"Testing Street\",\n" +
                    "                \"street_number\": 1450,\n" +
                    "                \"neighborhood\": \"Rio Grande do Sul\",\n" +
                    "                \"city\": \"Porto Alegre (90570070)\"\n" +
                    "            }\n" +
                    "        } \n" +
                    "    }");
            MPAcess.MercadoPago.access();
            PaymentController paymentController = new PaymentController()
        when:
            Payment result = paymentController.createNewPayment(request, response)

        then:
            result != null
    }

    def "get payment id"() {
        given:
            MPAcess.MercadoPago.access();
            PaymentController paymentController = new PaymentController()
        when:
            Payment result = paymentController.getPaymentId("1242575550")

        then:
            result != null
    }

}
