# GPU Market Analysis (Progetto Osservatorio)

Un potente strumento per l'importazione, la pulizia e l'analisi dei dati di mercato delle schede video (GPU) a partire da dataset CSV Open Data, interamente sviluppato in Java.

**Membri del Gruppo:** Nikita Myroniuk, Riccardo Ailoaiei, Piskaj Kristian, Alessandro Marchesi

---

## 📝 Descrizione

Questo progetto nasce con l'obiettivo di elaborare un dataset reale contenente specifiche tecniche e prezzi delle GPU (`gpu_specs_prices.csv`). Poiché i dati "grezzi" presentano spesso formattazioni eterogenee, valori mancanti (es. `"N/A"`) o unità di misura mischiate a numeri (es. `"$1,289.99"` o `"24 GB"`), l'applicazione implementa un rigoroso sistema di **Data Cleaning (Milestone 1)**.

Una volta validata e pulita, la collezione di dati viene passata a un motore di analisi basato sulle **Java Streams API (Milestone 2)**, che estrae report statistici avanzati come il rapporto qualità/prezzo, i trend di costo per architettura e le configurazioni di memoria più performanti.

---

## ✨ Features e Architettura del Codice

Il progetto è suddiviso in moduli chiari per separare le responsabilità (Modello, Parsing, Analisi).

### 1. CSV Parsing e Data Cleaning (Milestone 1)

L'applicazione legge e processa i dati utilizzando la libreria **Apache Commons CSV**. Il caricamento iniziale viene gestito in `app.java`, che funge da entry-point:

```java
// app.java - Entry point dell'applicazione
String csvFilePath = "datasheet/gpu_specs_prices.csv";

// Inizializzazione del servizio di parsing
CsvParserService parserService = new CsvParserService();
List<GpuModel> validGpus = parserService.parseCsv(csvFilePath);

if (validGpus.isEmpty()) {
    logger.warn("No valid GPU records found. Exiting.");
    return;
}
```

La vera pulizia avviene in `CsvParserService.java`. Qui il codice applica una validazione severa: se un record non ha tutte le colonne o il prezzo è invalido, viene scartato. Inoltre, utilizza le **espressioni regolari (Regex)** per separare i numeri dalle stringhe di testo:

```java
// CsvParserService.java - Esempio di Data Cleaning tramite Regex
private double parsePrice(String price) {
    try {
        // Rimuove simboli di valuta come '$' o lettere, mantenendo solo numeri e punti decimali
        String cleanPrice = price.replaceAll("[^0-9.]", "");
        if (cleanPrice.isEmpty()) return 0.0;
        return Double.parseDouble(cleanPrice);
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Price format error: " + price);
    }
}
```

---

### 2. Modello Dati Flessibile

La classe `GpuModel.java` è progettata per immagazzinare sia i dati grezzi letti dal file, sia i valori numerici convertiti e pronti per i calcoli matematici (come `priceValue` e `clockSpeedValue`).

```java
// GpuModel.java - Struttura dati ibrida
public class GpuModel {
    // Dati grezzi originali dal CSV
    private String name;
    private String price;        // es., "$1,289.99"
    private String memory;       // es., "24 GB"

    // Campi numerici parsati pronti per l'analisi
    private double priceValue;   // es., 1289.99
    private int memoryValue;     // in GB
    private int clockSpeedValue; // in MHz

    // ... getter e setter ...
}
```

---

### 3. Data Analysis Avanzata (Milestone 2)

Una volta ottenuta una lista di `GpuModel` validi, la classe `AnalysisService.java` genera **6 report dettagliati** utilizzando la programmazione funzionale (Java Streams).

Tra le analisi più interessanti troviamo la classifica delle GPU per **Value for Money** (Miglior rapporto Clock/Prezzo):

```java
// AnalysisService.java - Calcolo del Value for Money (Clock / Prezzo)
System.out.println("\n--- Top 5 Best Value (Clock Speed / Price) ---");
gpus.stream()
    .filter(g -> g.getPriceValue() > 0)
    .sorted(Comparator.comparingDouble((GpuModel g) ->
        g.getClockSpeedValue() / g.getPriceValue()).reversed())
    .limit(5)
    .forEach(g -> System.out.printf("%s - Ratio: %.2f (Clock: %d MHz, Price: $%.2f)%n",
        g.getName(), g.getClockSpeedValue() / g.getPriceValue(),
        g.getClockSpeedValue(), g.getPriceValue()));
```

Un altro esempio di utilizzo avanzato degli Stream è il raggruppamento per calcolare la **media dei prezzi per architettura (Chipset)**:

```java
// AnalysisService.java - Prezzo Medio per architettura (Chipset)
Map<String, Double> avgPriceByChipset = gpus.stream()
    .collect(Collectors.groupingBy(GpuModel::getChipset,
        Collectors.averagingDouble(GpuModel::getPriceValue)));

avgPriceByChipset.entrySet().stream()
    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
    .limit(10)
    .forEach(entry -> System.out.printf("%s: $%.2f%n", entry.getKey(), entry.getValue()));
```

---

### 4. Logging e Reporting

L'applicazione non utilizza stampe generiche per gli errori, ma implementa un sistema di logging professionale (`org.slf4j.Logger`) per tracciare i record scartati, garantendo un output pulito in cui i report finali risaltano chiaramente.

---

## 🛠 Technologies Used

| Tecnologia | Utilizzo |
|---|---|
| **Java 8+** | Logica core, OOP e Java Streams API |
| **Apache Commons CSV** | Parsing fluido e sicuro dei file CSV |
| **SLF4J** | Logging standardizzato del flusso applicativo |

---

## 🚀 Come Eseguire il Progetto

### Prerequisiti di Sistema

- **Java Development Kit (JDK):** Versione 8 o superiore
- **Dipendenze Esterne:** `org.apache.commons:commons-csv` e `org.slf4j:slf4j-api`
  > Si consiglia di gestire il progetto tramite **Maven** o **Gradle** per risolvere automaticamente le dipendenze. In alternativa, scaricare i file `.jar` e aggiungerli manualmente al Classpath.

---

### Struttura delle Cartelle Richiesta

Il punto di ingresso `app.java` cerca il dataset nel percorso relativo `datasheet/gpu_specs_prices.csv`. La struttura della cartella deve essere la seguente:

```
cartella-progetto/
├── src/                          # Codice sorgente Java
│   ├── app/
│   │   └── app.java              # Entry point (Main)
│   ├── model/
│   │   └── GpuModel.java
│   └── service/
│       ├── AnalysisService.java
│       └── CsvParserService.java
├── datasheet/                    # Cartella da creare manualmente nella root
│   └── gpu_specs_prices.csv      # Il dataset scaricato
└── pom.xml                       # (Opzionale) Configurazione Maven
```

---

### Esecuzione tramite IDE (Metodo Consigliato)

> Compatible con: **IntelliJ IDEA**, **Eclipse**, **VS Code**

1. **Importa il progetto:** Apri l'IDE e importa il progetto. Se presente il `pom.xml`, importalo come *Maven Project* per scaricare automaticamente le dipendenze.
2. **Verifica il file CSV:** Assicurati di aver creato la cartella `datasheet/` allo stesso livello di `src/` e di avervi inserito il file `gpu_specs_prices.csv`.
3. **Avvia il Main:** Naviga fino al file `app.java` nel package `app`.
4. **Esegui:** Clicca con il tasto destro e seleziona **Run 'app.main()'**.
5. **Leggi i Report:** Visualizzerai prima i log informativi e i `WARN` relativi alle righe scartate, seguiti dalle stampe a console dei **6 report statistici** generati dagli Stream.