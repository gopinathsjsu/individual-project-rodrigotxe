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

## Screenshots

Execution with file name as argument<br />
![screenshot 20](https://user-images.githubusercontent.com/62269628/144729694-ba7ff12d-0586-49c5-8b7f-d55e38de2878.jpg)

First file: Input1 - Sheet1.csv<br />
![screenshot 21](https://user-images.githubusercontent.com/62269628/144729704-897251a6-fc40-40c4-b266-69091e3cd248.jpg)

First file output:<br />
![screenshot 28](https://user-images.githubusercontent.com/62269628/144729854-d6a270ca-6c5c-4953-a920-4f7442e1ab0c.jpg)

Second  file: Input2 - Sheet1.csv<br />
![screenshot 22](https://user-images.githubusercontent.com/62269628/144729709-7c3d07da-c3a5-45a7-96ba-882ca4e3a273.jpg)

Second  file output:<br />
![screenshot 27](https://user-images.githubusercontent.com/62269628/144729749-54f6ee6f-76e7-49dc-9f58-ebb6431b00ba.jpg)

Third file: Input3 - Sheet1.csv<br />
![screenshot 23](https://user-images.githubusercontent.com/62269628/144729721-1697d1f4-9ed9-434a-98aa-93b3aa5d3d7c.jpg)

Third file output:<br />
![screenshot 29](https://user-images.githubusercontent.com/62269628/144729861-cfd2c3b0-fd97-44c2-8f34-27b6f76768cb.jpg)

End of the program<br />
![screenshot 24](https://user-images.githubusercontent.com/62269628/144729728-825ccad5-562d-4093-8f10-142b4b115e6e.jpg)
