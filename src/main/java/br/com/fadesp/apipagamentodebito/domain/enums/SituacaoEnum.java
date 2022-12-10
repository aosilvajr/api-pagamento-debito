package br.com.fadesp.apipagamentodebito.domain.enums;

public enum SituacaoEnum {

    PENDENTE("Pendente"),
    SUCESSO("Sucesso"),
    FALHA("Falha");

    private String value;

    private SituacaoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
