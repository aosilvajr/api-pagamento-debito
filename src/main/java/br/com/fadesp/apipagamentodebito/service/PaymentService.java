package br.com.fadesp.apipagamentodebito.service;

import java.util.List;

import br.com.fadesp.apipagamentodebito.domain.model.Payment;

public interface PaymentService {

    List<Payment> list();

}
