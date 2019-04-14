package city.search;

public enum CityOrderByField {
    NAMECITY("City Name"),
    POPULATION("City population");

    CityOrderByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }
}
