package conversorDeMonedas;

import java.util.Map;

public class TasaCambio {
    private String base_code;
    private Map<String, Double> conversion_rates;

    public String getMonedaBase() {
        return base_code;
    }

    public Map<String, Double> getTasas() {
        return conversion_rates;
    }
}
