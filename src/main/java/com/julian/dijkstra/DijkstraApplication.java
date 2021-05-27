package com.julian.dijkstra;

import com.julian.dijkstra.dijkstra.DijkstraAlgorithm;
import com.julian.dijkstra.dijkstra.Node;
import com.julian.dijkstra.domain.Netwerk;
import com.julian.dijkstra.domain.Stad;
import com.julian.dijkstra.service.NetwerkService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsUtils;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DijkstraApplication {

	public static void main(String[] args) {
		SpringApplication.run(DijkstraApplication.class, args);
	}

}
