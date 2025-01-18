package model;

public class ExchangeRate {

    private String originRate;
    private String destinationRate;
    private double rate;
    private double exchangeRate;

    public String getOriginRate() {
        return originRate;
    }


    public String getDestinationRate() {
        return destinationRate;
    }


    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public ExchangeRate(ExchangeRateDTO exchangeRateDTO) {
        this.originRate = exchangeRateDTO.base_code();
        this.destinationRate = exchangeRateDTO.target_code();
        this.exchangeRate = exchangeRateDTO.conversion_result();
    }
}
