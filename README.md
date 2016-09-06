# qr-generator
Generates sheets of QR codes for liquor bottles

This project is for use in a system where people bring bottles of alcohol, potluck-style and redeem them for tokens to trade back in for mixed drinks.
In this system we need some way to track what comes in and what goes out. Bottles are assigned alphanumeric codes according to their type. For example, the very first bottle of rum would be labeled R-00001.

This program generates sheets of QR codes corresponding to these possible codes. Simply choose the first code you want printed, then click "Generate PDF." A preview image will pop up and the option to print is available. The codes are aligned to print onto sheets of Avery 1.5" square labels so that you can simply print, peel, and stick the codes to the bottles.

This program uses ZXing, Apache PDFBox, and imgscalr as dependencies.

The intent is to use this with a custom android app that will scan these codes and update a database for certain actions - when a bottle is brought in, how many tokens it's worth, when it's emptied, etc. This app has not yet been developed.

There are some improvements that need to be done here: 
  1. Though the jar is runnable, it needs to be invoked from the command line with java -jar QRCodeGenerator3.jar. If it's not, then the PDFs don't get generated.
  2. The program generates a PDF and a preview image jpeg that get written to the local path. Ideally these should be fully self-contained or at least disposed of on close.
