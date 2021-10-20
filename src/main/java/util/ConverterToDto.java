package util;

import com.mercadopago.resources.Preference;
import dto.PreferenceDTO;

public class ConverterToDto {
    public PreferenceDTO converterPreferenceToDto(Preference preference){
        PreferenceDTO preferenceDTO = new PreferenceDTO();
        preferenceDTO.setPreferenceId(preference.getId());
        preferenceDTO.setInitPoint(preference.getInitPoint());
        return preferenceDTO;
    }
}
