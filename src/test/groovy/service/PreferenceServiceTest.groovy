package service

import com.mercadopago.exceptions.MPValidationException
import com.mercadopago.resources.Preference
import dto.ItemDTO
import dto.PreferenceDTO
import org.mockito.Mockito
import services.PreferenceService
import spock.lang.Specification
import util.MPAcess

class PreferenceServiceTest extends Specification{

    def "create preference"() {
        given:
            MPAcess.MercadoPago.access();
            ItemDTO itemDTO = new ItemDTO("caderno", 2, 10)
        when:
            PreferenceDTO originPreference = PreferenceService.createPreference(itemDTO)
        then:
            originPreference.initPoint != null
            originPreference.preferenceId != null
    }


    def "get preference by Id"() {

        given:
            MPAcess.MercadoPago.access();

            PreferenceService preferenceService = new PreferenceService()
        when:
            Preference result = preferenceService.getPreferenceId("813008278-bbd3178d-e380-47c8-b0ea-c3797e6281be")
        then:
            result.getId() == "813008278-bbd3178d-e380-47c8-b0ea-c3797e6281be"
    }

    def "exception MPValidationException create preference "() {
        given:
            MPAcess.MercadoPago.access();
            ItemDTO itemDTO = new ItemDTO()
            PreferenceService preferenceService = new PreferenceService()

            Preference preference1 = Mockito.mock(Preference)
            Mockito.when(preference1.save()).thenReturn(null)

        when:
            def result = preferenceService.createPreference(itemDTO)
        then:
            thrown(MPValidationException)
    }

}
