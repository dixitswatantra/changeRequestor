package org.swattech.changerequestor.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.swattech.changerequestor.model.Coins_Detail;

@Repository
public interface CoinRepository extends JpaRepository<Coins_Detail, Long> {

	@Modifying
	@Transactional
	@Query("update COINS_DETAIL set COIN_COUNT = ?2 where COIN_TYPE = ?1")
	void updateCounts(@Param(value = "coin_type") double coinType,@Param(value = "coin_count") int coinCount);

}
