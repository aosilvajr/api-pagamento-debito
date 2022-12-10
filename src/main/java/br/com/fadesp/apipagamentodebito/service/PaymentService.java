package br.com.fadesp.apipagamentodebito.service;

import java.util.List;

import br.com.fadesp.apipagamentodebito.domain.dto.payment.ResponsePayment;
import br.com.fadesp.apipagamentodebito.domain.enums.SituacaoEnum;

public interface PaymentService {

    List<ResponsePayment> list();

    ResponsePayment update(Long id, SituacaoEnum situacao);

}
