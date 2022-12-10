package br.com.fadesp.apipagamentodebito;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.fadesp.apipagamentodebito.domain.enums.MetodoPagamentoEnum;
import br.com.fadesp.apipagamentodebito.domain.model.Payment;
import br.com.fadesp.apipagamentodebito.repository.PaymentRepository;

@SpringBootApplication
public class ApiPagamentoDebitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPagamentoDebitoApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(PaymentRepository paymentRepository) {
		return args -> {
			paymentRepository.deleteAll();

			Payment payment = new Payment();
			payment.setCpfCnpj("02713295211");
			payment.setMetodoPagamento(MetodoPagamentoEnum.CARTAO_CREDITO);
			payment.setNumeroCartao("123456789");
			payment.setValorPagamento(1.89);

			paymentRepository.save(payment);
		};
	}

}
