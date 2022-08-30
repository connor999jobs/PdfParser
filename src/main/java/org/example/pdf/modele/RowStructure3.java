package org.example.pdf.modele;

import lombok.Data;

@Data
public class RowStructure3 {

    @Lookup(value = "(?<date>[0-9]+,[0-9]+\\h[A-Z]{3})")
    private String date;

    @Lookup(value = "(?<description>[A-Z][a-z]+( [A-Z][a-z]+))")
    private String description;


    @Lookup(value = "(?<category>[A-Z][a-z]+( [A-Z][a-z]+))")
    private String category;

//    @Lookup(value = "(?<amount>[0-9]+,[0-9]+\\h[A-Z]{3})")
//    private String amount;
}
