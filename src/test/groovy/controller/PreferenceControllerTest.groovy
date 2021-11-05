package controller

import api_responses.ApiResponse
import com.mercadopago.resources.Preference
import dto.ItemDTO
import dto.PreferenceDTO
import factorys.PreferenceFactory
import org.mockito.Mockito
import services.PreferenceService
import spark.Request
import spark.Response
import spock.lang.Shared
import spock.lang.Specification
import util.MPAcess
import util.Parser

class PreferenceControllerTest extends Specification {

    @Shared
    Response response

    def "create preference"() {

        given:
            MPAcess.MercadoPago.access();

            ApiResponse api = new ApiResponse();
            String responseFile = api.getFile("/preference_mla");
            Preference preference = Parser.toObj(responseFile, Preference.class);
            ItemDTO itemDTO = new ItemDTO("caderno", 2, 10)

            Preference preferenceMock = Mockito.mock(Preference);
            Mockito.when(preferenceMock.save()).thenReturn(preference);
            PreferenceFactory preferenceFactory = Mockito.mock(PreferenceFactory)
            Mockito.when(preferenceFactory.createPreference()).thenReturn(preferenceMock)
            PreferenceService.setPreferenceFactory(preferenceFactory)
            Request request = Mockito.mock(Request.class);
            Mockito.when(request.body()).thenReturn("{\n" +
                    "    \"title\": \"teste Preferece6\",\n" +
                    "    \"description\": \"Dummy description\",\n" +
                    "    \"picture_url\": \"http://www.myapp.com/myimage.jpg\",\n" +
                    "    \"category_id\": \"cat123\",\n" +
                    "    \"quantity\": 1,\n" +
                    "    \"currency_id\": \"U\$\",\n" +
                    "    \"unit_price\": 15\n" +
                    "}");


            PreferenceController preferenceController = new PreferenceController()
        when:
            PreferenceDTO result = preferenceController.createPreference(request, response)

        then:
            result != null
    }

    def "get preference id"() {
        given:
            MPAcess.MercadoPago.access();
            PreferenceController preferenceController = new PreferenceController()
        when:
            Preference result = preferenceController.getPreferenceId("813008278-bbd3178d-e380-47c8-b0ea-c3797e6281be")

        then:
            result != null
    }
}
