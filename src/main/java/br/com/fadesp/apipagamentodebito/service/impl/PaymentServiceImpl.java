package br.com.fadesp.apipagamentodebito.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.fadesp.apipagamentodebito.domain.dto.payment.ResponsePayment;
import br.com.fadesp.apipagamentodebito.domain.enums.SituacaoEnum;
import br.com.fadesp.apipagamentodebito.domain.mapper.PagamentoMapper;
import br.com.fadesp.apipagamentodebito.domain.model.Payment;
import br.com.fadesp.apipagamentodebito.exception.BadRequestException;
import br.com.fadesp.apipagamentodebito.exception.NotFoundException;
import br.com.fadesp.apipagamentodebito.repository.PaymentRepository;
import br.com.fadesp.apipagamentodebito.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final PagamentoMapper mapper;

    public PaymentServiceImpl(PaymentRepository repository, PagamentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ResponsePayment> list() {
        List<Payment> payment = this.repository.findAll();

        return payment
                .stream()
                .map(mapper::responseToDTOPayment)
                .collect(Collectors.toList());
    }

    @Override
    public ResponsePayment update(Long id, SituacaoEnum situacao) {
        Payment pagamento = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Pagamento não encontrado."));

        if (pagamento.getSituacao() == SituacaoEnum.SUCESSO) {
            throw new BadRequestException("Este pagamento já está processado. Seu status não pode mais ser alterado.");
        }

        if (pagamento.getSituacao() == SituacaoEnum.PENDENTE && situacao == SituacaoEnum.PENDENTE) {
            throw new BadRequestException("O status do pagamento só pode ser alterado para SUCESSO ou FALHA.");
        }

        if (pagamento.getSituacao() == SituacaoEnum.FALHA && situacao != SituacaoEnum.PENDENTE) {
            throw new BadRequestException(
                    "Este pagamento foi processado com falha. Seu status só pode ser alterado para PENDENTE.");
        }

        try {
            return this.updateSituationAndSave(pagamento, situacao);
        } catch (Exception e) {
            this.updateSituationAndSave(pagamento, SituacaoEnum.FALHA);
            throw new BadRequestException("Falha ao processar pagamento. Tente novamente mais tarde.");
        }
    }

    private ResponsePayment updateSituationAndSave(Payment pagamento, SituacaoEnum situacao) {
        pagamento.setSituacao(situacao);
        return this.mapper.responseToDTOPayment(this.repository.save(pagamento));
    }

}
