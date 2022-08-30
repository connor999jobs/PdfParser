package org.example.pdf.modele;

import lombok.Data;


@Data
public class RowStructure {

    @Lookup(value = "(?<category>[A-Z][a-z]+( [A-Z][a-z]+))")
    private String category;

    @Lookup(value = "(?<budget>[0-9]+,[0-9]+\\h[A-Z]{3})")
    private String budget;

    @Lookup(value = "(?<actual>[0-9]+,[0-9]+\\h[A-Z]{3})")
    private String actual;




}
