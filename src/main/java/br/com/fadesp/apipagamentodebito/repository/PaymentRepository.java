package br.com.fadesp.apipagamentodebito.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fadesp.apipagamentodebito.domain.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT * FROM Payment WHERE (:id IS NULL OR id = :id)" +
            " AND (:cpfCnpj IS NULL OR CPFCNPJ LIKE %:cpfCnpj%)" +
            " AND (:situacao IS NULL OR SITUACAO = :situacao)", nativeQuery = true)
    List<Payment> findByOrListAll(
            @Param("id") Long id,
            @Param("cpfCnpj") String cpfCnpj,
            @Param("situacao") String situacao);
}
