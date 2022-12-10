package br.com.fadesp.apipagamentodebito.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fadesp.apipagamentodebito.domain.PagamentoDebitoCode;
import br.com.fadesp.apipagamentodebito.domain.dto.payment.RequestPayment;
import br.com.fadesp.apipagamentodebito.domain.dto.payment.ResponsePayment;
import br.com.fadesp.apipagamentodebito.domain.enums.SituacaoEnum;
import br.com.fadesp.apipagamentodebito.service.PaymentService;
import br.com.fadesp.apipagamentodebito.utils.Properties;
import br.com.fadesp.apipagamentodebito.utils.ResponseRest;

@RestController
@RequestMapping("/v1/api/payment")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseRest<List<ResponsePayment>>> list(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String cpfCnpj,
            @RequestParam(required = false) SituacaoEnum situacao) {
        List<ResponsePayment> payments = this.service.list(id, cpfCnpj, situacao);

        return ResponseEntity.ok(
                new ResponseRest<>(payments, PagamentoDebitoCode.BUSCA_GERAL_SUCESSO.getProperties(), null));
    }

    @PostMapping
    public ResponseEntity<ResponseRest<ResponsePayment>> create(@RequestBody @Valid RequestPayment payment) {
        try {
            ResponsePayment pagamento = service.create(payment);
            Properties properties = PagamentoDebitoCode.CAD_SERVICO_SUCESSO_E.getProperties();

            return ResponseEntity.ok(new ResponseRest<>(pagamento, properties, null));
        } catch (Exception e) {
            String exception = e.getMessage();
            Properties properties = PagamentoDebitoCode.CAD_SERVICO_ERROR_I.getProperties();

            return new ResponseEntity<>(new ResponseRest<>(null, properties, exception), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseRest<ResponsePayment>> update(@PathVariable Long id,
            @RequestBody SituacaoEnum situacao) {
        try {
            ResponsePayment pagamento = this.service.update(id, situacao);
            Properties properties = PagamentoDebitoCode.CAD_SERVICO_SUCESSO_A.getProperties();

            return ResponseEntity.ok(new ResponseRest<>(pagamento, properties, null));
        } catch (Exception e) {
            String exception = e.getMessage();
            Properties properties = PagamentoDebitoCode.CAD_SERVICO_ERROR_A.getProperties();

            return new ResponseEntity<>(new ResponseRest<>(null, properties, exception), HttpStatus.BAD_REQUEST);
        }
    }

}
