package moe.perci.server.nicktalk.Contract;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class Test {
    public HashMap<String, Object> f(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("msg", "");
        result.put("data", "");

        try {

            result.put("code", 1);
            result.put("data", "");
        } catch (Exception e) {
            result.put("msg", e.getMessage());
        } catch (Error e) {
            result.put("msg", e.toString());
        }

        return result;
    }
}
