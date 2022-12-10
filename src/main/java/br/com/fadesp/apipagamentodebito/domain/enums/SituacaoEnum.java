package br.com.fadesp.apipagamentodebito.domain.enums;

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

}
