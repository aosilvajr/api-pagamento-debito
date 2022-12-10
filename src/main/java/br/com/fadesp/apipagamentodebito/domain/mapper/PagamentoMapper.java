package br.com.fadesp.apipagamentodebito.domain.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import br.com.fadesp.apipagamentodebito.domain.dto.payment.RequestPayment;
import br.com.fadesp.apipagamentodebito.domain.dto.payment.ResponsePayment;
import br.com.fadesp.apipagamentodebito.domain.model.Payment;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PagamentoMapper {

    ResponsePayment responseToDTOPayment(Payment payment);

    Payment requestToModelPayment(RequestPayment request);

}
