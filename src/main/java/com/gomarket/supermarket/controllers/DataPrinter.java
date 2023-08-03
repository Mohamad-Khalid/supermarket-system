package com.gomarket.supermarket.controllers;

import javafx.print.PrinterJob;
import javafx.scene.control.TextArea;

public class DataPrinter {
    public static void printBillContent(TextArea bill) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(bill);
            if (success) {
                job.endJob();
            }
        }
    }
}
