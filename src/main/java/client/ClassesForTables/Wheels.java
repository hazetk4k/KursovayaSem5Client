package client.ClassesForTables;

public class Wheels {
    public String model_name;
    public float pressure;
    public float width;
    public int mileage;
    public int price;

    public Wheels(String model_name, float pressure, float width, int mileage, int price) {
        this.model_name = model_name;
        this.pressure = pressure;
        this.width = width;
        this.mileage = mileage;
        this.price = price;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
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
