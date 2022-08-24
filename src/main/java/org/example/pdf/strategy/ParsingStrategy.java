package org.example.pdf.strategy;


import java.util.List;

/* FROM ORM PARSER  EXAMPLE CSV*/
public interface ParsingStrategy {
   <T> List<T> read(FileReadWriteSource source, Class<?> cls);
}
