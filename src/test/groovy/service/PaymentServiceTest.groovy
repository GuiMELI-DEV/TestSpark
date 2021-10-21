package service
import com.mercadopago.exceptions.MPRestException;
import com.mercadopago.resources.Payment
import com.mercadopago.resources.datastructures.payment.Address
import com.mercadopago.resources.datastructures.payment.Identification
import com.mercadopago.resources.datastructures.payment.Payer
import dto.AddressDTO
import dto.IdentificationDTO
import dto.PayerDTO
import dto.PaymentDTO
import exception.ErrorCreatePaymentException
import org.mockito.Mockito
import services.PaymentService
import spock.lang.Specification
import util.MPAcess

class PaymentServiceTest extends Specification {
    def "create payment"() {
        given:
            MPAcess.MercadoPago.access();
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

            PaymentService paymentService = new PaymentService()
        when:
            def result = paymentService.newPayment(paymentDTO)
        then:
            result.payer.identification.number == payment.payer.identification.number
    }

    def "exception create payment "() {
        given:
            MPAcess.MercadoPago.access();
            PaymentService paymentService = new PaymentService()
        when:
            def result = paymentService.newPayment(null)
        then:
            def test = thrown(ErrorCreatePaymentException)
            test.message == "PaymentDTO should not be empty or null"
    }

    def "get payment by Id"() {

        given:
            MPAcess.MercadoPago.access();
            PaymentService paymentService = new PaymentService()
        when:
            Payment result = paymentService.getPaymentId("1242575550")

        then:
            result.getId() == "1242575550"
    }

}