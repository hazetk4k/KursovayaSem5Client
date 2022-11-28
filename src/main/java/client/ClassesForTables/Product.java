package client.ClassesForTables;

public class Product {
    public int modelID;
    public String modelName;

    public String modelFuel;

    public String modelBattery;

    public String modelCarcase;

    public String modelChassis;

    public Product(int modelID, String modelName, String modelFuel, String modelBattery, String modelCarcase, String modelChassis){
        this.modelID = modelID;
        this.modelName = modelName;
        this.modelFuel = modelFuel;
        this.modelBattery = modelBattery;
        this.modelCarcase = modelCarcase;
        this.modelChassis = modelChassis;

    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelFuel() {
        return modelFuel;
    }

    public void setModelFuel(String modelFuel) {
        this.modelFuel = modelFuel;
    }

    public String getModelBattery() {
        return modelBattery;
    }

    public void setModelBattery(String modelBattery) {
        this.modelBattery = modelBattery;
    }

    public String getModelCarcase() {
        return modelCarcase;
    }

    public void setModelCarcase(String modelCarcase) {
        this.modelCarcase = modelCarcase;
    }

    public String getModelChassis() {
        return modelChassis;
    }

    public void setModelChassis(String modelChassis) {
        this.modelChassis = modelChassis;
    }
}
