package br.com.fadesp.apipagamentodebito.domain.enums;

public enum MetodoPagamentoEnum {

    BOLETO("Boleto"),
    PIX("Pix"),
    CARTAO_CREDITO("Cartão Credito"),
    CARTAO_DEBITO("Cartão Debito");

    private String value;

    private MetodoPagamentoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
