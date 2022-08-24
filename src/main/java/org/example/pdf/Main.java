package org.example.pdf;

import lombok.SneakyThrows;
import org.example.pdf.modele.RowStructure;
import org.example.pdf.strategy.FileReadWriteSource;
import org.example.pdf.strategy.ParsingStrategy;
import org.example.pdf.strategy.ParsingStrategyImpl;

import java.io.File;
import java.net.URL;
import java.util.List;


public class Main {

    private static final ParsingStrategy pdf = new ParsingStrategyImpl();
    @SneakyThrows
    public static void main(String[] args) {

    }
}
