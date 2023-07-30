package br.com.gestaoprojetos.application.dto;

import jakarta.validation.constraints.NotNull;

public record MembroDto(@NotNull Long pessoaId, @NotNull Long projetoId) {}
