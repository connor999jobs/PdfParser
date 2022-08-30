package org.example.pdf.modele;


import lombok.Data;


@Data
public class RowStructure2 {

    @Lookup(value = "(?<category>[A-Z][a-z]+( [A-Z][a-z]+)?)")
    private String category;


    @Lookup(value = "(?<difference>[0-9]+,[0-9]+\\h[A-Z]{3})")
    private String difference;
}
