package org.example.pdf;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;
import lombok.SneakyThrows;
import org.example.pdf.modele.RowStructure;
import org.example.pdf.strategy.FileReadWriteSource;
import org.example.pdf.strategy.ParsingStrategy;
import org.example.pdf.strategy.ParsingStrategyImpl;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    private static final ParsingStrategy pdf = new ParsingStrategyImpl();
    @SneakyThrows
    public static void main(String[] args) {
        URL url = Main.class.getClassLoader().getResource("sample.pdf");
        assert url != null;
        File file = new File(url.toURI());
        PdfDocument document = new PdfDocument();
        document.loadFromBytes(Files.readAllBytes(file.toPath()));
        PdfTableExtractor pdfTableExtractor = new PdfTableExtractor(document);
        List<List<String>> tables = new ArrayList<>();
        for (int pageIndex = 0; pageIndex < document.getPages().getCount(); pageIndex++) {
            PdfTable[] tableLists = pdfTableExtractor.extractTable(pageIndex);
            if (tableLists != null && tableLists.length > 0) {
                for (PdfTable table : tableLists) {
                    tables.add(Arrays.asList(buildTable(table).toString()
                            .replaceAll("(?<=[a-zA-Z])\s+(?=[a-zA-Z])"," ")
                            .replace("\u00a0", " ").split("\r\s")));
                }
            }
        }
        FileWriter fw = new FileWriter("ExtractAllTables.txt");
        fw.write(tables.toString());
        fw.flush();
        fw.close();
    }

    private static StringBuilder buildTable(PdfTable table) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < table.getRowCount(); i++) {
            buildRow(stringBuilder, table, i);
            stringBuilder.append("\r\n");
        }
        return stringBuilder;
    }

    private static void buildRow(StringBuilder stringBuilder, PdfTable table, int i) {
        List<String> row = new ArrayList<>();
        for (int j = 0; j < table.getColumnCount(); j++) {
            String text = table.getText(i, j);
            if (text != null && text.trim().length() > 1) {
                row.add(text);
            }
        }
        stringBuilder.append(String.join(" | ", row));
    }
}
