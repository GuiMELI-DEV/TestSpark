package services;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.PaymentMethods;
import dto.ItemDTO;
import dto.PreferenceDTO;
import exception.ErrorCreatePaymentException;
import util.ConverterToDto;

import java.util.ArrayList;

public class PreferenceService {

    private PreferenceService() { }

    public static PreferenceDTO createPreference(ItemDTO itemDTO) {
        Preference preference = new Preference();
        ArrayList arr = new ArrayList();
        ConverterToDto preferenceDTO = new ConverterToDto();
        PreferenceDTO preferenceId = new PreferenceDTO();

        try {
        PaymentMethods paymentMethods = new PaymentMethods();
        paymentMethods.setExcludedPaymentTypes("credit_card","digital_wallet","digital_currency","bank_transfer");

        preference.setPaymentMethods(paymentMethods);

            Item item = new Item();
            item.setTitle(itemDTO.getTitle())
                    .setQuantity(itemDTO.getQuantity())
                    .setUnitPrice(itemDTO.getUnit_price());
            arr.add(item);
            preference.setItems(arr);
            preference = preference.save();
            preferenceId = preferenceDTO.converterPreferenceToDto(preference);
            if(preference.getSandboxInitPoint() == null)
                throw new ErrorCreatePaymentException(preference.getLastApiResponse().getStringResponse(), preference);
        } catch (MPException e) {
            e.printStackTrace();
        }

        return preferenceId;
    }

    public static Preference getPreferenceId(String id) throws MPException {
        return Preference.findById(id);
    }
}
