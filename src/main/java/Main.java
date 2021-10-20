
import com.mercadopago.exceptions.MPConfException;
import controller.PaymentController;
import controller.PreferenceController;
import util.CoverterToJson;
import util.MPAcess;
import static spark.Spark.*;
import static spark.Spark.before;
import static spark.Spark.path;


public class Main {


    public static void main(String[] args) throws MPConfException {
        MPAcess.MercadoPago.access();
        before("/*", (request, response) -> response.type("application/json"));
        path("/preference", () -> {
                post("/",(request,response) -> {
                    return PreferenceController.createPreference(request ,response);
                }, new CoverterToJson());
                get("/:id",(request, response) -> {
                    return PreferenceController.getPreferenceId(request.params(":id"));
                }, new CoverterToJson());
         });
        path("/payment", () -> {
            post("/",(request,response) -> {
                return PaymentController.createNewPayment(request ,response);
            }, new CoverterToJson());
            get("/:id",(request, response) -> {
                return PaymentController.getPaymentId(request.params(":id"));
            }, new CoverterToJson());
        });
    }

}
