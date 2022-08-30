package org.example.pdf.strategy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

/* FROM ORM PARSER  EXAMPLE FOR FILE WRITE AND READ*/
@RequiredArgsConstructor
@Getter
public class FileReadWriteSource{
    private final File source;

    @SneakyThrows
    public String getContent() {
        return FileUtils.readFileToString(source, StandardCharsets.UTF_8);
    }
}
