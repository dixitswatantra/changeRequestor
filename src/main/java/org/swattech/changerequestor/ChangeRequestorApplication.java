package org.swattech.changerequestor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.swattech.changerequestor.model.Coins_Detail;
import org.swattech.changerequestor.repository.CoinRepository;

@EnableCaching
@SpringBootApplication
public class ChangeRequestorApplication {

	@Value("${app.coins}")
	private String coins;

	@Autowired
	private CoinRepository coinRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChangeRequestorApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		Integer coin = Integer.valueOf(coins);
		Coins_Detail penny = new Coins_Detail();
		penny.setCoinCount(coin);
		penny.setCoinType(0.01);
		Coins_Detail nickel = new Coins_Detail();
		nickel.setCoinCount(coin);
		nickel.setCoinType(0.05);
		Coins_Detail dime = new Coins_Detail();
		dime.setCoinCount(coin);
		dime.setCoinType(0.10);
		Coins_Detail quarter = new Coins_Detail();
		quarter.setCoinCount(coin);
		quarter.setCoinType(0.25);
		return () -> {
			coinRepository.updateCounts(0.01, coin);
			coinRepository.updateCounts(0.05, coin);
			coinRepository.updateCounts(0.10, coin);
			coinRepository.updateCounts(0.25, coin);
		};
	}

}
