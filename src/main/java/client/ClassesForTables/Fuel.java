package client.ClassesForTables;

public class Fuel {
    public String model_name;
    public float volume;
    public float pressure;
    public int mileage;
    public int price;

    public Fuel(String model_name, float volume, float pressure, int mileage, int price) {
        this.model_name = model_name;
        this.volume = volume;
        this.pressure = pressure;
        this.mileage = mileage;
        this.price = price;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
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