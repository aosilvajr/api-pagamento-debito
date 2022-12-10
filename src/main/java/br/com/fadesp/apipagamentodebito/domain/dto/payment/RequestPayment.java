package br.com.fadesp.apipagamentodebito.domain.dto.payment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fadesp.apipagamentodebito.domain.enums.MetodoPagamentoEnum;

public class RequestPayment {

    @JsonIgnore
    private Long id;

    @NotEmpty(message = "não pode ser vazio")
    private String cpfCnpj;

    @NotNull
    private MetodoPagamentoEnum metodoPagamento;

    @NotEmpty(message = "não pode ser vazio")
    private String numeroCartao;

    @NotNull(message = "não pode ser vazio")
    private Double valorPagamento;

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

}
