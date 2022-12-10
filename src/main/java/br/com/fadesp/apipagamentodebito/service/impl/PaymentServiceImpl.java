package br.com.fadesp.apipagamentodebito.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fadesp.apipagamentodebito.domain.model.Payment;
import br.com.fadesp.apipagamentodebito.repository.PaymentRepository;
import br.com.fadesp.apipagamentodebito.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Payment> list() {
        return this.repository.findAll();
    }

}
