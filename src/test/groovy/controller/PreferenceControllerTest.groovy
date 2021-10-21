package controller

import com.mercadopago.resources.Preference
import dto.PreferenceDTO
import org.mockito.Mockito
import spark.Request
import spark.Response
import spock.lang.Shared
import spock.lang.Specification
import util.MPAcess

class PreferenceControllerTest extends Specification {

    @Shared
    Response response

    def "create preference"() {

        given:
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
            MPAcess.MercadoPago.access();
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
