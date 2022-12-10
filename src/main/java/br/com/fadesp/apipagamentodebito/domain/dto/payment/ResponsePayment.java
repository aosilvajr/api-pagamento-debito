package br.com.fadesp.apipagamentodebito.domain.dto.payment;

import br.com.fadesp.apipagamentodebito.domain.enums.MetodoPagamentoEnum;
import br.com.fadesp.apipagamentodebito.domain.enums.SituacaoEnum;

public class ResponsePayment {
    private Long id;

    private String cpfCnpj;

    private MetodoPagamentoEnum metodoPagamento;

    private String numeroCartao;

    private Double valorPagamento;

    private SituacaoEnum situacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public MetodoPagamentoEnum getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamentoEnum metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

}
