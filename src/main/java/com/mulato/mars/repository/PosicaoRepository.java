package com.mulato.mars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mulato.mars.model.Posicao;

public interface PosicaoRepository extends JpaRepository<Posicao, Long> {

}