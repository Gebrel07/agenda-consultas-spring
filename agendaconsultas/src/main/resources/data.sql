INSERT INTO profissionais (nome, especialidade, registro) VALUES
    ('Profissional 1', 'Clinica geral', 'CRM-123'),
    ('Profissional 2', 'Psiquiatria', 'CRM-456'),
    ('Profissional 3', 'Cirurgia', 'CRM-789');

INSERT INTO pacientes (nome, cpf, idade, sexo) VALUES
    ('Paciente 1', '11111111111', 26, 1),
    ('Paciente 2', '22222222222', 20, 2),
    ('Paciente 3', '33333333333', 28, 3);

INSERT INTO consultas (data, HORA_INICIO, HORA_FIM, descricao, observacao, status, profissional_id, paciente_id) VALUES
    ('2024-08-15', '09:00:00', '10:00:00', 'Consulta de rotina', 'Sem observações', 'PENDENTE', 1, 1),
    ('2024-08-15', '10:30:00', '11:30:00', 'Consulta de acompanhamento', 'Paciente com boa evolução', 'PENDENTE', 2, 2),
    ('2024-08-16', '14:00:00', '15:00:00', 'Consulta de emergência', 'Paciente com sintomas agudos', 'PENDENTE', 1, 3),
    ('2024-08-17', '08:00:00', '09:00:00', 'Consulta para avaliação', 'Avaliação inicial para novo tratamento', 'PENDENTE', 2, 1),
    ('2024-08-18', '11:00:00', '12:00:00', 'Consulta de revisão', 'Revisão após tratamento', 'CONFIRMADO', 1, 2),
    ('2024-08-19', '13:00:00', '14:00:00', 'Consulta de rotina', 'Cheque de rotina e orientações', 'CANCELADO', 2, 3);
