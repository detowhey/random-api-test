package br.com.henriquealmeida.utilities;

import org.json.JSONObject;

public class JSONUtil {

    public static String bookingJSONBuilder(String name, String lastname, Double totalprice, boolean depositedpaidid,
                                            String checkin, String checkout, String additionalneeds) {
        try {
            return new JSONObject()
                    .put("firstname", name)
                    .put("lastname", lastname)
                    .put("totalprice", totalprice)
                    .put("depositpaid", depositedpaidid)
                    .put("bookingdates", new JSONObject()
                            .put("checkin", checkin)
                            .put("checkout", checkout))
                    .put("additionalneeds", additionalneeds).toString();

        } catch (Exception exception) {
            return exception.getMessage();
        }
    }


}
