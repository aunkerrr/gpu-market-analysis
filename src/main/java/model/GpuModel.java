package model;

public class GpuModel {
    private String name;
    private String brand;
    private String model;
    private String memory; // e.g., "24 GB"
    private String memoryInterface; // e.g., "GDDR6X"
    private String length; // e.g., "335 mm"
    private String gpuInterface; // e.g., "PCIe x16"
    private String chipset; // e.g., "GeForce RTX 3090"
    private String baseClock; // e.g., "1395 MHz"
    private String clockSpeed; // e.g., "1785 MHz"
    private String frameSync; // e.g., "G-Sync"
    private String price; // e.g., "$1,289.99"
    private String itemUrl;
    private int used;

    // Parsed numeric fields for analysis
    private double priceValue;
    private int memoryValue; // in GB
    private int baseClockValue; // in MHz
    private int clockSpeedValue; // in MHz
    private int lengthValue; // in mm

    public GpuModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getMemoryInterface() {
        return memoryInterface;
    }

    public void setMemoryInterface(String memoryInterface) {
        this.memoryInterface = memoryInterface;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getGpuInterface() {
        return gpuInterface;
    }

    public void setGpuInterface(String gpuInterface) {
        this.gpuInterface = gpuInterface;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getBaseClock() {
        return baseClock;
    }

    public void setBaseClock(String baseClock) {
        this.baseClock = baseClock;
    }

    public String getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(String clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public String getFrameSync() {
        return frameSync;
    }

    public void setFrameSync(String frameSync) {
        this.frameSync = frameSync;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public double getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(double priceValue) {
        this.priceValue = priceValue;
    }

    public int getMemoryValue() {
        return memoryValue;
    }

    public void setMemoryValue(int memoryValue) {
        this.memoryValue = memoryValue;
    }

    public int getBaseClockValue() {
        return baseClockValue;
    }

    public void setBaseClockValue(int baseClockValue) {
        this.baseClockValue = baseClockValue;
    }

    public int getClockSpeedValue() {
        return clockSpeedValue;
    }

    public void setClockSpeedValue(int clockSpeedValue) {
        this.clockSpeedValue = clockSpeedValue;
    }

    public int getLengthValue() {
        return lengthValue;
    }

    public void setLengthValue(int lengthValue) {
        this.lengthValue = lengthValue;
    }

    @Override
    public String toString() {
        return "GpuModel{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}