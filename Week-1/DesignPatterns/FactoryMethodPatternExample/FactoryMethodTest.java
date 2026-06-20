package com.factory;

public class FactoryMethodTest {
    public static void main(String[] args) {
        DocumentFactory wordFactory  = new WordDocumentFactory();
        DocumentFactory pdfFactory   = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        Document word  = wordFactory.createDocument();
        Document pdf   = pdfFactory.createDocument();
        Document excel = excelFactory.createDocument();

        word.open();  word.save();  word.close();
        pdf.open();   pdf.save();   pdf.close();
        excel.open(); excel.save(); excel.close();
    }
}
