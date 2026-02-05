package app;

import model.GpuModel;
import service.AnalysisService;
import service.CsvParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class app {
    private static final Logger logger = LoggerFactory.getLogger(app.class);

    public static void main(String[] args) {
        logger.info("Application started");

        // Path to the CSV file
        // Assuming the file is in the 'datasheet' directory at the project root
        String csvFilePath = "datasheet/gpu_specs_prices.csv";
        File csvFile = new File(csvFilePath);

        if (!csvFile.exists()) {
            logger.error("CSV file not found at path: {}", csvFile.getAbsolutePath());
            return;
        }

        // Milestone 1: Import and Clean Data
        CsvParserService parserService = new CsvParserService();
        List<GpuModel> validGpus = parserService.parseCsv(csvFilePath);

        if (validGpus.isEmpty()) {
            logger.warn("No valid GPU records found. Exiting.");
            return;
        }

        // Milestone 2: Report and Analysis
        AnalysisService analysisService = new AnalysisService();
        analysisService.performAnalysis(validGpus);

        logger.info("Application finished successfully");
    }
}