package com.hook.form.backend.org.papercheque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/papercheque")
@RequiredArgsConstructor
public class PaperChequeController {
    @Autowired
    private final PaperChequeService service;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody PaperChequeRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<PaperCheque>> findAllBooks() {
        return ResponseEntity.ok(service.findAll());
    }  
}
