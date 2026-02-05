package service;

import model.GpuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnalysisService {
    private static final Logger logger = LoggerFactory.getLogger(AnalysisService.class);

    public void performAnalysis(List<GpuModel> gpus) {
        logger.info("Starting analysis on {} records...", gpus.size());

        // 1. Count by Manufacturer (Brand)
        Map<String, Long> countByBrand = gpus.stream()
                .collect(Collectors.groupingBy(GpuModel::getBrand, Collectors.counting()));
        
        System.out.println("\n--- GPU Count by Manufacturer ---");
        countByBrand.forEach((brand, count) -> System.out.printf("%s: %d%n", brand, count));

        // 2. Average Price by Chipset
        Map<String, Double> avgPriceByChipset = gpus.stream()
                .collect(Collectors.groupingBy(GpuModel::getChipset, 
                        Collectors.averagingDouble(GpuModel::getPriceValue)));

        System.out.println("\n--- Average Price by Chipset (Top 10) ---");
        avgPriceByChipset.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(10)
                .forEach(entry -> System.out.printf("%s: $%.2f%n", entry.getKey(), entry.getValue()));

        // 3. Most Expensive and Cheapest GPU per Manufacturer
        System.out.println("\n--- Most Expensive and Cheapest GPU per Manufacturer ---");
        Map<String, List<GpuModel>> gpusByBrand = gpus.stream()
                .collect(Collectors.groupingBy(GpuModel::getBrand));
        
        gpusByBrand.forEach((brand, list) -> {
            Optional<GpuModel> mostExpensive = list.stream().max(Comparator.comparingDouble(GpuModel::getPriceValue));
            Optional<GpuModel> cheapest = list.stream().min(Comparator.comparingDouble(GpuModel::getPriceValue));
            
            if (mostExpensive.isPresent() && cheapest.isPresent()) {
                System.out.printf("Brand: %s%n", brand);
                System.out.printf("  Most Expensive: %s ($%.2f)%n", mostExpensive.get().getName(), mostExpensive.get().getPriceValue());
                System.out.printf("  Cheapest:       %s ($%.2f)%n", cheapest.get().getName(), cheapest.get().getPriceValue());
            }
        });

        // 4. Best Clock Speed per Dollar (Value for Money based on Boost Clock)
        // Ratio = ClockSpeed / Price
        System.out.println("\n--- Top 5 Best Value (Clock Speed / Price) ---");
        gpus.stream()
                .filter(g -> g.getPriceValue() > 0)
                .sorted(Comparator.comparingDouble((GpuModel g) -> g.getClockSpeedValue() / g.getPriceValue()).reversed())
                .limit(5)
                .forEach(g -> System.out.printf("%s - Ratio: %.2f (Clock: %d MHz, Price: $%.2f)%n", 
                        g.getName(), g.getClockSpeedValue() / g.getPriceValue(), g.getClockSpeedValue(), g.getPriceValue()));

        // 5. Best Memory Size (Max Memory)
        System.out.println("\n--- Top 5 GPUs by Memory Size ---");
        gpus.stream()
                .sorted(Comparator.comparingInt(GpuModel::getMemoryValue).reversed())
                .limit(5)
                .forEach(g -> System.out.printf("%s - Memory: %d GB%n", g.getName(), g.getMemoryValue()));

        // 6. Average Memory Size by Memory Interface Type
        System.out.println("\n--- Average Memory Size by Interface Type ---");
        Map<String, Double> avgMemByInterface = gpus.stream()
                .collect(Collectors.groupingBy(GpuModel::getMemoryInterface, 
                        Collectors.averagingInt(GpuModel::getMemoryValue)));
        
        avgMemByInterface.forEach((type, avg) -> System.out.printf("%s: %.2f GB%n", type, avg));
    }
}