package br.com.fadesp.apipagamentodebito.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SituacaoEnum {

    PENDENTE("PENDENTE"),
    SUCESSO("SUCESSO"),
    FALHA("FALHA");

    private String value;

    private SituacaoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @JsonProperty
    public static SituacaoEnum getSituacaoFromCode(String value) {
        for (SituacaoEnum situacao : SituacaoEnum.values()) {
            if (situacao.getValue().equals(value)) {
                return situacao;
            }
        }

        return null;
    }

}
