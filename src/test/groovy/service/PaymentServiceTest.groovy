package service

import api_responses.ApiResponse
import com.mercadopago.resources.Payment
import dto.AddressDTO
import dto.IdentificationDTO
import dto.PayerDTO
import dto.PaymentDTO
import exception.ErrorCreatePaymentException
import factorys.PaymentFactory
import org.mockito.MockedStatic
import org.mockito.Mockito
import services.PaymentService
import spock.lang.Specification
import util.MPAcess
import util.Parser

class PaymentServiceTest extends Specification {
    def "create payment"() {
        given:
            MPAcess.MercadoPago.access();

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

            ApiResponse api = new ApiResponse();
            String response = api.getFile("/payment");
            Payment paymentJson = Parser.toObj(response, Payment.class);

            Payment paymentMock = Mockito.spy(new Payment());
            Mockito.doReturn(paymentJson).when(paymentMock).save()

            PaymentFactory paymentFactory = Mockito.mock(PaymentFactory)
            Mockito.when(paymentFactory.createPayment()).thenReturn(paymentMock)

            PaymentService.setPaymentFactory(paymentFactory)

        when:
            def result = PaymentService.newPayment(paymentDTO)
        then:
            result.payer.identification.number != null
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

            ApiResponse api = new ApiResponse();
            String response = api.getFile("/payment");
            Payment paymentJson = Parser.toObj(response, Payment.class);

            MockedStatic<Payment> paymentMock = Mockito.mockStatic(Payment);
            paymentMock.when(Payment.findById(Mockito.anyString())).thenReturn(paymentJson)

        when:
            Payment result = PaymentService.getPaymentId("")

        then:
            result.getId() == paymentJson.getId()
    }

}