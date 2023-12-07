package com.hook.form.backend.org.papercheque;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class PaperChequeRequest {
    private Integer id;
    private String chequeNumber;
    private String accountName;
    private Double amount;
}
