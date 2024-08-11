package com.gabrielsantos.agendaconsultas.consulta;

public enum StatusConsulta {
    PENDENTE("Pendente"),
    CONFIRMADO("Confirmado"),
    EM_ANDAMENTO("Em Andamento"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusConsulta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
