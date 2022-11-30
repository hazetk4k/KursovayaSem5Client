package client.ClassesForTables;

public class Battery {
    public String model_name;
    public float capacity;
    public float voltage;
    public int mileage;
    public int price;

    public Battery(String model_name, float capacity, float voltage, int mileage, int price) {
        this.model_name = model_name;
        this.capacity = capacity;
        this.voltage = voltage;
        this.mileage = mileage;
        this.price = price;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
