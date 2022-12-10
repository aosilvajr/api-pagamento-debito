package br.com.fadesp.apipagamentodebito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fadesp.apipagamentodebito.domain.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
