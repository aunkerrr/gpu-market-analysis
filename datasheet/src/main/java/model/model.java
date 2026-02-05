package model;

public class model {
    private String name;
    private String brand;
    private String gpuModel;
    private String memory;
    private String memoryInterface;
    private String length;
    private String gpuInterface;
    private String chipset;
    private String baseClock;
    private String clockSpeed;
    private String frameSync;
    private String price;
    private String itemUrl;
    private int used;

    public model() {
    }

    public model(String name, String brand, String gpuModel, String memory, String memoryInterface, String length, String gpuInterface, String chipset, String baseClock, String clockSpeed, String frameSync, String price, String itemUrl, int used) {
        this.name = name;
        this.brand = brand;
        this.gpuModel = gpuModel;
        this.memory = memory;
        this.memoryInterface = memoryInterface;
        this.length = length;
        this.gpuInterface = gpuInterface;
        this.chipset = chipset;
        this.baseClock = baseClock;
        this.clockSpeed = clockSpeed;
        this.frameSync = frameSync;
        this.price = price;
        this.itemUrl = itemUrl;
        this.used = used;
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

    public String getGpuModel() {
        return gpuModel;
    }

    public void setGpuModel(String gpuModel) {
        this.gpuModel = gpuModel;
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

    @Override
    public String toString() {
        return "model{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", gpuModel='" + gpuModel + '\'' +
                ", memory='" + memory + '\'' +
                ", memoryInterface='" + memoryInterface + '\'' +
                ", length='" + length + '\'' +
                ", gpuInterface='" + gpuInterface + '\'' +
                ", chipset='" + chipset + '\'' +
                ", baseClock='" + baseClock + '\'' +
                ", clockSpeed='" + clockSpeed + '\'' +
                ", frameSync='" + frameSync + '\'' +
                ", price='" + price + '\'' +
                ", itemUrl='" + itemUrl + '\'' +
                ", used=" + used +
                '}';
    }
}
