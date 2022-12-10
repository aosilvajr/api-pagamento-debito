package br.com.fadesp.apipagamentodebito.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fadesp.apipagamentodebito.domain.PagamentoDebitoCode;
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
    public ResponseEntity<ResponseRest<List<ResponsePayment>>> list() {
        List<ResponsePayment> payments = this.service.list();

        return ResponseEntity.ok(
                new ResponseRest<>(payments, PagamentoDebitoCode.BUSCA_GERAL_SUCESSO.getProperties(), null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseRest<ResponsePayment>> update(@PathVariable Long id, @RequestBody SituacaoEnum situacao) {
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
