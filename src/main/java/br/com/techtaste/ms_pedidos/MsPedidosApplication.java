package br.com.techtaste.ms_pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Classe principal da aplicação Spring Boot para o microsserviço de pedidos.
 * Esta classe é responsável por inicializar e configurar o contexto da aplicação
 * Spring, servindo como ponto de entrada para o microsserviço de gerenciamento
 * de pedidos do sistema TechTaste.
 *
 * @author TechTaste Team
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MsPedidosApplication {

	/**
	 * Método principal que inicia a aplicação Spring Boot.
	 * Este método é o ponto de entrada padrão para a JVM e utiliza o
	 * SpringApplication.run() para inicializar o contexto da aplicação,
	 * carregar os beans, configurar o servidor embutido e iniciar o
	 * microsserviço de pedidos.
	 *
	 * @param args Argumentos de linha de comando passados durante a execução
	 *             da aplicação, que podem ser utilizados para configurações
	 *             específicas ou parâmetros de ambiente.
	 */
	public static void main(String[] args) {
		SpringApplication.run(MsPedidosApplication.class, args);
	}

}