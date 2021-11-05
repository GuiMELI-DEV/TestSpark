package service


import api_responses.ApiResponse

import com.mercadopago.exceptions.MPValidationException
import com.mercadopago.resources.Payment
import com.mercadopago.resources.Preference
import dto.ItemDTO
import dto.PreferenceDTO
import factorys.PreferenceFactory
import org.mockito.MockedStatic
import org.mockito.Mockito
import services.PaymentService
import services.PreferenceService
import spock.lang.Specification
import util.MPAcess
import util.Parser

class PreferenceServiceTest extends Specification{
    def "create preference"() {
        given:
            ApiResponse api = new ApiResponse();
            String response = api.getFile("/preference_mla");
            Preference preference = Parser.toObj(response, Preference.class);
            MPAcess.MercadoPago.access();
            ItemDTO itemDTO = new ItemDTO("caderno", 2, 10)

            Preference preferenceMock = Mockito.mock(Preference);
            Mockito.when(preferenceMock.save()).thenReturn(preference);
            PreferenceFactory preferenceFactory = Mockito.mock(PreferenceFactory)
            Mockito.when(preferenceFactory.createPreference()).thenReturn(preferenceMock)
            PreferenceService.setPreferenceFactory(preferenceFactory)
        when:
            PreferenceDTO originPreference = PreferenceService.createPreference(itemDTO)
        then:
            originPreference.initPoint != null
            originPreference.preferenceId != null
    }

    def "get preference by Id"() {

        given:
            MPAcess.MercadoPago.access();

            ApiResponse api = new ApiResponse();
            String response = api.getFile("/preference_mla");
            Preference preferenceJson = Parser.toObj(response, Preference.class);

            MockedStatic<Preference> preferenceMock = Mockito.mockStatic(Preference);
            preferenceMock.when(Preference.findById(Mockito.anyString())).thenReturn(preferenceJson)
        when:
            Preference result = PreferenceService.getPreferenceId("813008278-bbd3178d-e380-47c8-b0ea-c3797e6281be")
        then:
            result.getId() == preferenceJson.getId()
    }

    def "exception MPValidationException create preference "() {
        given:
            MPAcess.MercadoPago.access();
            ApiResponse api = new ApiResponse();
            String response = api.getFile("/preference_mla");
            Preference preference = Parser.toObj(response, Preference.class);
            MPAcess.MercadoPago.access();
            ItemDTO itemDTO = new ItemDTO("caderno", 2, 10)

            Preference preferenceMock = Mockito.mock(Preference);
            Mockito.when(preferenceMock.save()).thenThrow(MPValidationException);
            PreferenceFactory preferenceFactory = Mockito.mock(PreferenceFactory)
            Mockito.when(preferenceFactory.createPreference()).thenReturn(preferenceMock)
            PreferenceService.setPreferenceFactory(preferenceFactory)

        when:
            def result = PreferenceService.createPreference(itemDTO)
        then:
            thrown(MPValidationException)
    }

}
