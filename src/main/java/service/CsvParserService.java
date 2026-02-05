package service;

import model.GpuModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvParserService {
    private static final Logger logger = LoggerFactory.getLogger(CsvParserService.class);
    private static final String[] HEADERS = {"name", "brand", "model.model", "memory", "memory_interface", "length", "interface", "chipset", "base_clock", "clock_speed", "frame_sync", "price", "item_url", "used"};

    public List<GpuModel> parseCsv(String filePath) {
        List<GpuModel> validRecords = new ArrayList<>();
        int discardedCount = 0;

        try (Reader in = new FileReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .builder()
                    .setHeader(HEADERS)
                    .setSkipHeaderRecord(true)
                    .setIgnoreSurroundingSpaces(true)
                    .build()
                    .parse(in);

            for (CSVRecord record : records) {
                try {
                    GpuModel gpu = mapRecordToGpuModel(record);
                    validRecords.add(gpu);
                } catch (Exception e) {
                    discardedCount++;
                    logger.warn("Discarded record at line {}: {}", record.getRecordNumber(), e.getMessage());
                }
            }
        } catch (IOException e) {
            logger.error("Error reading CSV file: {}", e.getMessage());
        }

        logger.info("Parsing complete. Valid records: {}, Discarded records: {}", validRecords.size(), discardedCount);
        return validRecords;
    }

    private GpuModel mapRecordToGpuModel(CSVRecord record) {
        // Basic validation: check if essential fields are present
        if (record.size() < HEADERS.length) {
            throw new IllegalArgumentException("Missing columns");
        }

        String priceStr = record.get("price");
        if (priceStr == null || priceStr.trim().isEmpty() || priceStr.equalsIgnoreCase("N/A")) {
            throw new IllegalArgumentException("Invalid price");
        }

        GpuModel gpu = new GpuModel();
        gpu.setName(record.get("name"));
        gpu.setBrand(record.get("brand"));
        gpu.setModel(record.get("model.model"));
        gpu.setMemory(record.get("memory"));
        gpu.setMemoryInterface(record.get("memory_interface"));
        gpu.setLength(record.get("length"));
        gpu.setGpuInterface(record.get("interface"));
        gpu.setChipset(record.get("chipset"));
        gpu.setBaseClock(record.get("base_clock"));
        gpu.setClockSpeed(record.get("clock_speed"));
        gpu.setFrameSync(record.get("frame_sync"));
        gpu.setPrice(priceStr);
        gpu.setItemUrl(record.get("item_url"));
        
        try {
            gpu.setUsed(Integer.parseInt(record.get("used")));
        } catch (NumberFormatException e) {
             throw new IllegalArgumentException("Invalid 'used' flag format");
        }

        // Parse numeric values for analysis
        gpu.setPriceValue(parsePrice(gpu.getPrice()));
        gpu.setMemoryValue(parseMemory(gpu.getMemory()));
        gpu.setBaseClockValue(parseClock(gpu.getBaseClock()));
        gpu.setClockSpeedValue(parseClock(gpu.getClockSpeed()));
        gpu.setLengthValue(parseLength(gpu.getLength()));

        return gpu;
    }

    private double parsePrice(String price) {
        try {
            String cleanPrice = price.replaceAll("[^0-9.]", "");
            if (cleanPrice.isEmpty()) return 0.0;
            return Double.parseDouble(cleanPrice);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Price format error: " + price);
        }
    }

    private int parseMemory(String memory) {
        try {
            String cleanMemory = memory.replaceAll("[^0-9]", "");
            if (cleanMemory.isEmpty()) return 0;
            return Integer.parseInt(cleanMemory);
        } catch (NumberFormatException e) {
             // Some memory fields might be empty or N/A, treat as 0 or throw
             throw new IllegalArgumentException("Memory format error: " + memory);
        }
    }

    private int parseClock(String clock) {
        try {
            String cleanClock = clock.replaceAll("[^0-9]", "");
            if (cleanClock.isEmpty()) return 0;
            return Integer.parseInt(cleanClock);
        } catch (NumberFormatException e) {
             throw new IllegalArgumentException("Clock format error: " + clock);
        }
    }
    
    private int parseLength(String length) {
        try {
            // Length might be like "335 mm" or "266.7 mm"
            String cleanLength = length.replaceAll("[^0-9.]", "");
            if (cleanLength.isEmpty()) return 0;
            return (int) Double.parseDouble(cleanLength);
        } catch (NumberFormatException e) {
             // Allow 0 or skip
             return 0; 
        }
    }
}