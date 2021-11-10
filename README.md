# MyMarket - Automated Purchasing System

## How to run the system

1 - The Stock input file ( Items dataset ) needs to be entered as the first Argument to the main class.
  - If the system cannot find the file, an error will be generated on the console.

2 - The user will be prompted to enter the file path ( full path ) for the order entry on the command line.
  - If the system cannot find the file, an error will be generated on the console. And the same question will be prompted.
  
3 - The system will validate the order items and generate an error file if it encounters any problems.

4 - If no errors were found, the system will create a new order and generate the output file with the total spent.

5 - To create a new order, the user needs to inform a new file. Otherwise, just leave the path input blank and press enter.

All output files will be created in the same input files folder.
  - Error Log = errorLog_timestamp.txt
  - Output = output_timestamp.csv

## Design Pattern

- Singleton: Class InMemoryDB
- Factory Method: Interface OutputFile, Class ErrorFile, Class CheckoutFile and Clas FileHandler

## Class Diagram

![Class Diagra - MyMarket](https://user-images.githubusercontent.com/62269628/141204024-3e5752fb-5e06-454d-b9f2-d48f429e7b23.png)
