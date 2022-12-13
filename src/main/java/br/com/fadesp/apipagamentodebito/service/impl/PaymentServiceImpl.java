package br.com.fadesp.apipagamentodebito.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.fadesp.apipagamentodebito.domain.dto.payment.RequestPayment;
import br.com.fadesp.apipagamentodebito.domain.dto.payment.RequestUpdatePayment;
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
    public List<ResponsePayment> list(Long id, String cpfCnpj, SituacaoEnum situacao) {
        List<Payment> payment = this.repository.findByOrListAll(id, cpfCnpj, Objects.toString(situacao, null));

        return payment
                .stream()
                .map(mapper::responseToDTOPayment)
                .collect(Collectors.toList());
    }

    @Override
    public ResponsePayment create(RequestPayment payment) {
        Payment pagamento = mapper.requestToModelPayment(payment);
        return mapper.responseToDTOPayment(repository.save(pagamento));
    }

    @Override
    public ResponsePayment update(Long id, RequestUpdatePayment payment) {
        Payment pagamento = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Pagamento não encontrado."));

        if (pagamento.getSituacao() == SituacaoEnum.SUCESSO) {
            throw new BadRequestException("Este pagamento já está processado. Seu status não pode mais ser alterado.");
        }

        if (pagamento.getSituacao() == SituacaoEnum.PENDENTE && payment.getSituacao() == SituacaoEnum.PENDENTE) {
            throw new BadRequestException("O status do pagamento só pode ser alterado para SUCESSO ou FALHA.");
        }

        if (pagamento.getSituacao() == SituacaoEnum.FALHA && payment.getSituacao() != SituacaoEnum.PENDENTE) {
            throw new BadRequestException(
                    "Este pagamento foi processado com falha. Seu status só pode ser alterado para PENDENTE.");
        }

        try {
            return this.updateSituationAndSave(pagamento, payment.getSituacao());
        } catch (Exception e) {
            this.updateSituationAndSave(pagamento, SituacaoEnum.FALHA);
            throw new BadRequestException("Falha ao processar pagamento. Tente novamente mais tarde.");
        }
    }

    private ResponsePayment updateSituationAndSave(Payment pagamento, SituacaoEnum situacao) {
        pagamento.setSituacao(situacao);
        return this.mapper.responseToDTOPayment(this.repository.save(pagamento));
    }

    @Override
    public void delete(Long id) {
        Payment pagamento = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Pagamento não encontrado."));

        if (pagamento.getSituacao() != SituacaoEnum.PENDENTE) {
            throw new BadRequestException(
                    "Falha ao deletar pagamento, não é possivel possível deletar pagamentos processados como "
                            + pagamento.getSituacao());
        }

        repository.deleteById(id);
    }

}
