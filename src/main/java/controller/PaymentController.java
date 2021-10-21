package controller;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import dto.PaymentDTO;
import services.PaymentService;
import spark.Request;
import spark.Response;
import util.Parser;

public class PaymentController {
    public PaymentController() {
    }

    public static Payment createNewPayment(Request request, Response response) throws MPException {
        String body = request.body();
        PaymentDTO payment = Parser.toObj(body, PaymentDTO.class);
        return PaymentService.newPayment(payment);
    }

    public static Payment getPaymentId(String id) throws MPException {
        return PaymentService.getPaymentId(id);
    }

}
