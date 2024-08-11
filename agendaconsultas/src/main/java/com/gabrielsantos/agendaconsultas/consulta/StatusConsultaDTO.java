package com.gabrielsantos.agendaconsultas.consulta;

public class StatusConsultaDTO {
    private final StatusConsulta statusConsulta;
    private final String descricao;

    public StatusConsultaDTO(StatusConsulta statusConsulta) {
        this.statusConsulta = statusConsulta;
        this.descricao = statusConsulta.getDescricao();
    }

    public StatusConsulta getStatusConsulta() {
        return statusConsulta;
    }

    public String getDescricao() {
        return descricao;
    }
}
