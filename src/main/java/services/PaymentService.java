package services;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Address;
import com.mercadopago.resources.datastructures.payment.Identification;
import com.mercadopago.resources.datastructures.payment.Payer;
import dto.PaymentDTO;
import exception.ErrorCreatePaymentException;


public class PaymentService {
    public PaymentService() {
    }

    public static Payment newPayment(PaymentDTO paymentDTO) throws MPException {
        if (paymentDTO == null) {
            throw new ErrorCreatePaymentException("PaymentDTO should not be empty or null", paymentDTO);
        }
        Payment payment = new Payment();

        payment.setTransactionAmount(paymentDTO.getTransactionAmount())
                .setDescription(paymentDTO.getDescription())
                .setPaymentMethodId(paymentDTO.getPaymentMethodId())
                .setPayer(new Payer()
                        .setFirstName(paymentDTO.getPayer().getFirstName())
                        .setLastName(paymentDTO.getPayer().getLastName())
                        .setEmail(paymentDTO.getPayer().getEmail())
                        .setIdentification(new Identification()
                                .setType(paymentDTO.getPayer().getIdentification().getType())
                                .setNumber(paymentDTO.getPayer().getIdentification().getNumber()))
                        .setAddress(new Address()
                                .setStreetName(paymentDTO.getPayer().getAddress().getStreetName())
                                .setStreetName(paymentDTO.getPayer().getAddress().getStreetName())
                                .setStreetNumber(paymentDTO.getPayer().getAddress().getStreetNumber())
                                .setZipCode(paymentDTO.getPayer().getAddress().getZipCode())
                                .setNeighborhood(paymentDTO.getPayer().getAddress().getNeighborhood())
                                .setCity(paymentDTO.getPayer().getAddress().getCity())));

        payment.save();

        return payment;
    }

    public static Payment getPaymentId(String id) throws MPException {
        return Payment.findById(id);
    }

}
