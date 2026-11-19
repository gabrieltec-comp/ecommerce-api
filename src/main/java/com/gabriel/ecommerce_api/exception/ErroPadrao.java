package com.gabriel.ecommerce_api.exception;


import java.time.Instant;
import java.util.Map;

public record ErroPadrao(Instant timestamp,
                         Integer status,
                         String erro,
                         String mensagem,
                         String caminho,
                         Map<String, String> campos)
{ }
