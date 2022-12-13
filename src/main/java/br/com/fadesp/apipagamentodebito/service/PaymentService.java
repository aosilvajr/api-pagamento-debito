package br.com.fadesp.apipagamentodebito.service;

import java.util.List;

import br.com.fadesp.apipagamentodebito.domain.dto.payment.RequestPayment;
import br.com.fadesp.apipagamentodebito.domain.dto.payment.RequestUpdatePayment;
import br.com.fadesp.apipagamentodebito.domain.dto.payment.ResponsePayment;
import br.com.fadesp.apipagamentodebito.domain.enums.SituacaoEnum;

public interface PaymentService {

    List<ResponsePayment> list(Long id, String cpfCnpj, SituacaoEnum situacao);

    ResponsePayment create(RequestPayment payment);

    ResponsePayment update(Long id, RequestUpdatePayment payment);

    void delete(Long id);

}
