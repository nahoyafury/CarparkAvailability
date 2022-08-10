package sg.edu.rp.c346.id21040742.carparkavailability;

public class Carpark {
    private String total_lots;
    private String lot_type;
    private String lots_available;
    private String carpark_number;

    public Carpark(String total_lots, String lot_type, String lots_available, String carpark_number) {
        this.total_lots = total_lots;
        this.lot_type = lot_type;
        this.lots_available = lots_available;
        this.carpark_number = carpark_number;
    }

    public String getCarpark_number() {
        return carpark_number;
    }

    public void setCarpark_number(String carpark_number) {
        this.carpark_number = carpark_number;
    }

    public String getTotal_lots() {
        return total_lots;
    }

    public void setTotal_lots(String total_lots) {
        this.total_lots = total_lots;
    }

    public String getLot_type() {
        return lot_type;
    }

    public void setLot_type(String lot_type) {
        this.lot_type = lot_type;
    }

    public String getLots_available() {
        return lots_available;
    }

    public void setLots_available(String lots_available) {
        this.lots_available = lots_available;
    }

    public String toString() {
        String msg = "Carpark Number: " + getCarpark_number() + "\nLot Type: " + getLot_type() + "\nLots Available: " + getLots_available() + "\nTotal Lots: " + getTotal_lots();
        return msg;
    }
}
