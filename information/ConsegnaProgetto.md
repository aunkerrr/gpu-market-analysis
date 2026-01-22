## Progetto di gruppo: Osservatorio (Analisi di Open Data)
# Contesto generale

Il progetto utilizza un dataset reale di Open Data. L’obiettivo è costruire un programma che sappia:
leggere dati reali
validare e pulire informazioni non affidabili
produrre analisi significative
documentare le scelte fatte
Non è richiesto alcun frontend grafico né l’uso di un database.
Organizzazione del lavoro
Il progetto va svolto in gruppi da 3 o 4 studenti. Ogni gruppo deve organizzarsi autonomamente, decidendo:

come suddividere il lavoro
- le tempistiche
- le responsabilità
- le scelte tecniche

#

Tutte le decisioni devono essere documentate.
## Deliverable 0: Documento di progetto
Prima di iniziare a programmare, ogni gruppo deve consegnare un breve documento (1–2 pagine) che contenga:
 - Descrizione del dataset scelto
- Ipotesi sui problemi dei dati (campi mancanti, valori non validi, formati)
- Struttura del progetto Java (classi principali, enum previsti)
- Suddivisione del lavoro nel gruppo
- Proposta di tempistiche per le milestone

**Senza questo documento il progetto non parte.**

#
## Milestone 1: Import e pulizia dei dati

#
# Obiettivo
- **Leggere il dataset e costruire una versione “pulita”.**

### Attività richieste
- Lettura del file di input
- Parsing dei campi
- Validazione forte dei dati
- Gestione delle righe scartate con motivazione

*Ogni riga scartata deve avere un motivo chiaro (formato errato, valore fuori range, campo mancante, ecc.).*

## Output

- Lista di record validi
- Contatore delle righe scartate
- Log degli scarti (testuale)

## Scelte libere del gruppo

#
- quali controlli considerare “bloccanti”
- come rappresentare i motivi di scarto
- come strutturare le classi
-Milestone 2: Report e analisi (Stream)

### Obiettivo

Estrarre informazioni significative dai dati puliti usando Stream Java.

## 

#
# Ogni gruppo deve definire almeno 6 report, di cui:
- almeno 2 con groupingBy
- almeno 1 con media
- almeno 1 con max o min
- almeno 1 ordinato
#



 ## Esempi (non vincolanti):
 #
- conteggio per categoria
- top N per valore
- media per zona o periodo
- trend temporale se il dataset contiene date


## Output
- Report stampati a console
- Spiegazione di cosa rappresenta ogni report e perché è utile
#

# *Consegna finale*
## **Ogni gruppo consegna:**
### - Codice sorgente
### - Documento di progetto
### - Report prodotti


