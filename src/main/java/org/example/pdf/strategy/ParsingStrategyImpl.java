package org.example.pdf.strategy;

import lombok.SneakyThrows;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.example.pdf.modele.Lookup;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingStrategyImpl implements ParsingStrategy{

    @Override
    @SneakyThrows
    public <T> List<T> read(FileReadWriteSource source, Class<T> cls) {
        File file = source.getSource();
        String parsedText = getParsedText(file);

        return getTs(cls, parsedText);

    }

    private <T> List<T> getTs(Class<T> cls, String parsedText) {
        List<T> pdfValueList = new ArrayList<>();
        Map<String, String> map = getFieldsTableFromPDF(cls);
        String values = String.join(" ", map.values());
        Pattern pattern = Pattern.compile(values);
        Matcher matcher = pattern.matcher(parsedText);
        while (matcher.find()){
            pdfValueList.add(entityFromClass(cls, map, matcher));
        }
        return pdfValueList;
    }

    private String getParsedText(File file) throws IOException {
        String parsedText;
        PDFParser parser = new PDFParser(new FileInputStream(file));
        parser.parse();
        COSDocument cosDoc = parser.getDocument();
        PDFTextStripper pdfStripper = new PDFTextStripper();
        PDDocument pdDoc = new PDDocument(cosDoc);
        parsedText = pdfStripper.getText(pdDoc);
        return parsedText;
    }


    @SneakyThrows
    private <T> T entityFromClass(Class<T> cls, Map<String, String> values, Matcher matcher) {
        T entity = cls.getConstructor().newInstance();
        for (String s : values.keySet()) {
            Field field = cls.getDeclaredField(s);
            field.setAccessible(true);
            field.set(entity, matcher.group(s).replaceAll("\\h"," "));
        }
        return entity;
    }


    private <T> Map<String, String> getFieldsTableFromPDF(Class<T> cls) {
        Map<String, String> pdfValues = new LinkedHashMap<>();
        for (Field field : cls.getDeclaredFields()){
            field.setAccessible(true);
            if (field.isAnnotationPresent(Lookup.class)){
                pdfValues.put(field.getName(), field.getAnnotation(Lookup.class).value());
            }
        }
        return pdfValues;
    }

}
