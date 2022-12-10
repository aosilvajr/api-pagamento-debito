package br.com.fadesp.apipagamentodebito.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fadesp.apipagamentodebito.domain.PagamentoDebitoCode;
import br.com.fadesp.apipagamentodebito.domain.model.Payment;
import br.com.fadesp.apipagamentodebito.service.PaymentService;
import br.com.fadesp.apipagamentodebito.utils.ResponseRest;

@RestController
@RequestMapping("/v1/api/payment")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseRest<List<Payment>>> list() {
        List<Payment> payments = this.service.list();

        return ResponseEntity.ok(
                new ResponseRest<>(payments, PagamentoDebitoCode.BUSCA_GERAL_SUCESSO.getProperties(), null));
    }

}
