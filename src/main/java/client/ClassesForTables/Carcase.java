package client.ClassesForTables;

public class Carcase {
    public String model_name;
    public float metal_thickness;
    public float glass_thickness;
    public int mileage;
    public int price;

    public Carcase(String model_name, float metal_thickness, float glass_thickness, int mileage, int price) {
        this.model_name = model_name;
        this.metal_thickness = metal_thickness;
        this.glass_thickness = glass_thickness;
        this.mileage = mileage;
        this.price = price;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public float getMetal_thickness() {
        return metal_thickness;
    }

    public void setMetal_thickness(float metal_thickness) {
        this.metal_thickness = metal_thickness;
    }

    public float getGlass_thickness() {
        return glass_thickness;
    }

    public void setGlass_thickness(float glass_thickness) {
        this.glass_thickness = glass_thickness;
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
