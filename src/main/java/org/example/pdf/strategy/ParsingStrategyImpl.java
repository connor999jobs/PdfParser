package org.example.pdf.strategy;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.util.List;

public class ParsingStrategyImpl implements ParsingStrategy{

    @Override
    @SneakyThrows
    public <T> List<T> read(FileReadWriteSource source, Class<?> cls) {
        File file = source.getSource();
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        return null;
    }

}
