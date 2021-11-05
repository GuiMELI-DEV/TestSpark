
import com.mercadopago.exceptions.MPConfException;
import controller.PaymentController;
import controller.PreferenceController;
import factorys.PaymentFactory;
import factorys.PreferenceFactory;
import services.PaymentService;
import services.PreferenceService;
import util.CoverterToJson;
import util.MPAcess;
import static spark.Spark.*;
import static spark.Spark.before;
import static spark.Spark.path;


public class Main {


    public static void main(String[] args) throws MPConfException {
        MPAcess.MercadoPago.access();
        PreferenceService.setPreferenceFactory(new PreferenceFactory());
        PaymentService.setPaymentFactory(new PaymentFactory());
        before("/*", (request, response) -> response.type("application/json"));
        path("/preference", () -> {
            post("/", PreferenceController::createPreference, new CoverterToJson());
            get("/:id", (request, response) -> {
                return PreferenceController.getPreferenceId(request.params(":id"));
            }, new CoverterToJson());
        });
        path("/payment", () -> {
            post("/", PaymentController::createNewPayment, new CoverterToJson());
            get("/:id", (request, response) -> {
                return PaymentController.getPaymentId(request.params(":id"));
            }, new CoverterToJson());
        });
    }

}
