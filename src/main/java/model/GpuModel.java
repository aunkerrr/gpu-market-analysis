package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}