package services;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.PaymentMethods;
import dto.ItemDTO;
import dto.PreferenceDTO;
import factorys.PreferenceFactory;
import util.ConverterToDto;

import java.util.ArrayList;

public class PreferenceService {
    private static PreferenceFactory preferenceFactory;


    public PreferenceService() {
    }

    public static PreferenceDTO createPreference(ItemDTO itemDTO) throws MPException {

        Preference preference = preferenceFactory.createPreference();
        ArrayList<Item> arr = new ArrayList<>();
        ConverterToDto preferenceDTO = new ConverterToDto();
        PreferenceDTO preferenceId = new PreferenceDTO();

        PaymentMethods paymentMethods = new PaymentMethods();
        paymentMethods.setExcludedPaymentTypes("credit_card", "digital_wallet", "digital_currency", "bank_transfer");

        preference.setPaymentMethods(paymentMethods);

        Item item = new Item();
        item.setTitle(itemDTO.getTitle())
                .setQuantity(itemDTO.getQuantity())
                .setUnitPrice(itemDTO.getUnit_price());
        arr.add(item);
        preference.setItems(arr);
        Preference preference1 = preference.save();
        preferenceId = preferenceDTO.converterPreferenceToDto(preference1);

        return preferenceId;
    }

    public static void setPreferenceFactory(PreferenceFactory preferenceFactory) {
        PreferenceService.preferenceFactory = preferenceFactory;
    }

    public static Preference getPreferenceId(String id) throws MPException {
        return Preference.findById(id);
    }

}
