package net.cabezudo.pokemon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cabezudo.pokemon.entities.DataLog;

public interface DataLogRepository extends JpaRepository<DataLog, Integer> {

}
