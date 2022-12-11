package client.ClassesForTables;

public class Detail {
    public String model_name;
    public float text1;
    public float text2;
    public int mileage;
    public int price;

    public Detail(String model_name, float text1, float text2, int mileage, int price) {
        this.model_name = model_name;
        this.text1 = text1;
        this.text2 = text2;
        this.mileage = mileage;
        this.price = price;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public float getText2() {
        return text2;
    }

    public void setText2(float text2) {
        this.text2 = text2;
    }

    public float getText1() {
        return text1;
    }

    public void setText1(float text1) {
        this.text1 = text1;
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