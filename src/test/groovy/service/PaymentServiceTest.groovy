package service
import com.mercadopago.*;
import com.mercadopago.resources.Payment
import com.mercadopago.resources.datastructures.payment.Address
import com.mercadopago.resources.datastructures.payment.Identification
import com.mercadopago.resources.datastructures.payment.Payer
import dto.AddressDTO
import dto.IdentificationDTO
import dto.PayerDTO
import dto.PaymentDTO
import exception.ErrorCreatePaymentException
import org.checkerframework.checker.units.qual.C
import org.mockito.Mockito
import services.PaymentService
import spock.lang.Specification

class PaymentServiceTest extends Specification {

    def "create payment"() {
        given:
            Payment payment = new Payment();
            payment.setTransactionAmount(10)
                    .setDescription("produtoTest")
                    .setPaymentMethodId("bolbradesco")
                    .setPayer(new Payer()
                            .setFirstName("Guilherme")
                            .setLastName("Ferreira")
                            .setEmail("test@test.com")
                            .setIdentification(new Identification()
                                    .setType("CPF")
                                    .setNumber("123456789"))
                            .setAddress(new Address()
                                    .setStreetName("Av. das Nações Unidas")
                                    .setStreetNumber(3003)
                                    .setZipCode("06233200")
                                    .setNeighborhood("Bonfim")
                                    .setCity("Osasco")));

            IdentificationDTO identificationDTO = new IdentificationDTO()
            identificationDTO.setType("CPF")
            identificationDTO.setNumber("123456789")

            AddressDTO addressDTO = new AddressDTO()
            addressDTO.setStreetName("Av. das Nações Unidas")
            addressDTO.setStreetNumber(3003)
            addressDTO.setZipCode("06233200")
            addressDTO.setNeighborhood("Bonfim")
            addressDTO.setCity("Osasco")

            PayerDTO payerDTO = new PayerDTO()
            payerDTO.setFirstName("Guilherme")
            payerDTO.setLastName("Ferreira")
            payerDTO.setEmail("test@test.com")
            payerDTO.setIdentification(identificationDTO)
            payerDTO.setAddress(addressDTO)

            PaymentDTO paymentDTO = new PaymentDTO()
            paymentDTO.setTransactionAmount(10)
            paymentDTO.setDescription("produtoTest")
            paymentDTO.setPaymentMethodId("bolbradesco")
            paymentDTO.setPayer(payerDTO)

            MercadoPago.SDK.configure("ENV_ACCESS_TOKEN");
            PaymentService paymentService = new PaymentService()
        when:
            MercadoPago.SDK.configure("ENV_ACCESS_TOKEN");
            def result = paymentService.newPayment(paymentDTO)
            def result2 = paymentService.newPayment(null)
        then:
            result.payer.identification.number == payment.payer.identification.number
            def test = thrown(ErrorCreatePaymentException)
            test.message == "PaymentDTO should not be empty or null"
    }

    def "get payment by Id"() {

        given:
            MercadoPago.SDK.configure("ENV_ACCESS_TOKEN");
        Payment payment = new Payment();
            Payment mockPayment = Mockito.mock(Payment)
            Mockito.when(mockPayment.findById()).thenReturn(payment)
            PaymentService paymentService = new PaymentService()
        when:
            MercadoPago.SDK.configure("ENV_ACCESS_TOKEN");
            def result = paymentService.getPaymentId(122222)
        then:
            result.payer.identification.number == payment.payer.identification.number
    }
}