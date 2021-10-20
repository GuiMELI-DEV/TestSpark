package controller;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import dto.ItemDTO;
import dto.PreferenceDTO;
import services.PreferenceService;
import spark.Request;
import spark.Response;
import util.Parser;

public class PreferenceController {
    private PreferenceController() { }

    public static PreferenceDTO createPreference(Request request, Response response) {
        String body = request.body();
        ItemDTO itemDTO = Parser.toObj(body, ItemDTO.class);
        return PreferenceService.createPreference(itemDTO);
    }
    public static Preference getPreferenceId(String id) throws MPException {
        return PreferenceService.getPreferenceId(id);
    }

}
