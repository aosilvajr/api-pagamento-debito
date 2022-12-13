package br.com.fadesp.apipagamentodebito.domain.dto.payment;

import br.com.fadesp.apipagamentodebito.domain.enums.SituacaoEnum;

public class RequestUpdatePayment {

    private Long id;

    private SituacaoEnum situacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

}
