package com.polifono.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.polifono.domain.PlayerPhase;

public interface PlayerPhaseRepository extends CrudRepository<PlayerPhase, UUID> {

	@Query("SELECT playerPhase FROM PlayerPhase playerPhase WHERE playerPhase.id = :id")
	public PlayerPhase findNextPhaseOLD(@Param("id") int id);
	
	@Query("SELECT playerPhase FROM PlayerPhase playerPhase, Phase phase, Map map "
	+ "WHERE playerPhase.phase.id = phase.id AND phase.map.id = map.id AND playerPhase.player.id = :playerId "
	+ "AND playerPhase.phasestatus.id = 3 AND map.game.id = :gameId ORDER BY phase.order DESC")
	public List<PlayerPhase> findLastPhaseCompleted(@Param("playerId") int playerId, @Param("gameId") int gameId);
	
	@Query("SELECT playerPhase FROM PlayerPhase playerPhase, Phase phase, Map map, Game game WHERE playerPhase.phase.id = phase.id AND phase.map.id = map.id AND map.game.id = game.id AND playerPhase.player.id = :playerId ORDER BY game.id, phase.id ASC")
	public List<PlayerPhase> findPlayerPhaseByPlayer(@Param("playerId") int playerId);
	
	@Query("SELECT playerPhase FROM PlayerPhase playerPhase WHERE playerPhase.player.id = :playerId AND playerPhase.phase.id = :phaseId AND playerPhase.phasestatus.id = :phasestatusId")
	public PlayerPhase findPlayerPhaseByPlayerPhaseAndStatus(@Param("playerId") int playerId, @Param("phaseId") int phaseId, @Param("phasestatusId") int phasestatusId);
}