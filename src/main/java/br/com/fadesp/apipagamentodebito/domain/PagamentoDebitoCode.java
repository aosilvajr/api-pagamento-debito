package br.com.fadesp.apipagamentodebito.domain;

import org.springframework.http.HttpStatus;

import br.com.fadesp.apipagamentodebito.utils.Properties;

public enum PagamentoDebitoCode {

    BUSCA_GERAL_SUCESSO(0, HttpStatus.OK, "Registros buscado com sucesso."),
    BUSCA_GERAL_NOT_FOUND(0, HttpStatus.NOT_FOUND, "Nenhum registro encontrado."),
    BUSCA_GERAL_ERROR(1, HttpStatus.BAD_REQUEST, "Error ao buscar registro."),
    CAD_SERVICO_SUCESSO_I(0, HttpStatus.OK, "Registro incluido com sucesso"),
    CAD_SERVICO_SUCESSO_E(0, HttpStatus.OK, "Registro cadastrado com sucesso"),
    CAD_SERVICO_SUCESSO_A(0, HttpStatus.OK, "Registro atualizado com sucesso"),
    CAD_SERVICO_ERROR_I(1, HttpStatus.BAD_REQUEST, "Erro ao incluir novo registro"),
    CAD_SERVICO_ERROR_E(1, HttpStatus.BAD_REQUEST, "Erro ao excluir registro"),
    CAD_SERVICO_ERROR_A(1, HttpStatus.BAD_REQUEST, "Erro ao atualizar registro"),
    CAD_SERVICO_ERROR_INESPERADO(1, HttpStatus.BAD_REQUEST, "Erro inesperado"),
    CAD_SERVICO_INFORMACOES_INVALIDAS(1, HttpStatus.BAD_REQUEST, "Informações inválidas");

    private final Properties properties;

    private PagamentoDebitoCode(int code, HttpStatus status, String message) {
        this.properties = new Properties(code, status, message);
    }

    public Properties getProperties() {
        return properties;
    }
}
