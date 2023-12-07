package com.hook.form.backend.org.papercheque;


import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaperChequeService {
    private final PaperChequeRepository repository;

   public void save(PaperChequeRequest request) {
     PaperCheque paperCheque = PaperCheque.builder()
                .id(request.getId())
                .chequeNumber(request.getChequeNumber())
                .accountName(request.getAccountName())
                .amount(request.getAmount())
                .build();
        repository.save(paperCheque);
    }

      public List<PaperCheque> findAll() {
        return repository.findAll();
    }
}
