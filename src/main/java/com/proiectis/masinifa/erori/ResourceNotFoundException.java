package com.proiectis.masinifa.erori;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    // Aceasta clasa extinde clasa RuntimeException din Java, indicand ca este o exceptie din timpul rularii.
    // Adnotarea @ResponseStatus specifica codul de stare HTTP care va fi returnat atunci cand aceasta
    // exceptie este aruncata in timpul executiei, in acest caz, NOT_FOUND (codul 404).
}
