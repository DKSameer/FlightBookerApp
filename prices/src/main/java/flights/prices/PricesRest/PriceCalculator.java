package flights.prices.PricesRest;

public class PriceCalculator {
    
    private int totalPassengers;
    private int adults;
    private int kids;
    private int babies;
    private int luggage;
    //private boolean luggage;
    private double basePrice;
    private double finalPrice;
    // Attributes
    PriceCalculator(){

    }

    public PriceCalculator(int totalPassengers, int kids, int babies, int luggage, double price) {
        this.totalPassengers = totalPassengers;
        this.kids = kids;
        this.babies = babies;
        this.luggage = luggage;
        this.basePrice = price;
        this.adults = totalPassengers - (kids+babies);
        calculateFinalPrice();
    }

    private void calculateFinalPrice(){
        finalPrice = (adults*basePrice) + (kids * basePrice * 0.4) + (luggage * basePrice * 0.1);
    }

    public int getTotalPassengers() {
        return totalPassengers;
    }

    public void setTotalPassengers(int totalPassengers) {
        this.totalPassengers = totalPassengers;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getKids() {
        return kids;
    }

    public void setKids(int kids) {
        this.kids = kids;
    }

    public int getBabies() {
        return babies;
    }

    public void setBabies(int babies) {
        this.babies = babies;
    }

    public int getLuggage() {
        return luggage;
    }

    public void setLuggage(int luggage) {
        this.luggage = luggage;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }


}
