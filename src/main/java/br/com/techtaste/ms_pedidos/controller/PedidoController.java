package br.com.techtaste.ms_pedidos.controller;

import br.com.techtaste.ms_pedidos.dto.PedidoRequestDto;
import br.com.techtaste.ms_pedidos.dto.PedidoResponseDto;
import br.com.techtaste.ms_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST responsável por gerenciar as operações relacionadas aos pedidos.
 * Esta classe atua como a camada de apresentação da aplicação, expondo endpoints
 * HTTP para que os clientes possam interagir com o microsserviço de pedidos.
 * Todos os endpoints são mapeados sob o caminho base "/pedidos".
 *
 * @author TechTaste Team
 * @version 1.0
 */

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    /**
     * Serviço de pedidos injetado automaticamente pelo Spring.
     * Este atributo encapsula toda a lógica de negócio relacionada à
     * gestão de pedidos, incluindo validações, regras de negócio e
     * persistência dos dados.
     */

    @Autowired
    private PedidoService service;

    /**
     * Endpoint para cadastrar um novo pedido no sistema.
     * Este método recebe os dados do pedido através do corpo da requisição
     * (request body) e delega ao serviço a responsabilidade de processar
     * e persistir o novo pedido.
     *
     * @param pedidoDto Objeto contendo os dados necessários para criação
     *                  de um novo pedido, incluindo itens, cliente e valores
     * @return ResponseEntity contendo o pedido criado com status HTTP 201 (CREATED)
     */

    @PostMapping
    public ResponseEntity<PedidoResponseDto> cadastrarPedido(@RequestBody PedidoRequestDto pedidoDto) {
        // Removeu o parâmetro "erro" que não existia na chamada
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarPedido(pedidoDto));
    }

    /**
     * Endpoint para obter todos os pedidos cadastrados no sistema.
     * Este método recupera a lista completa de pedidos existentes no banco
     * de dados e retorna seus dados formatados para o cliente.
     *
     * @return ResponseEntity contendo uma lista de PedidoResponseDto com
     *         todos os pedidos cadastrados e status HTTP 200 (OK)
     */

    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> obterTodos() {
        // Padronizando o retorno com ResponseEntity
        return ResponseEntity.ok(service.obterTodos());
    }

    /**
     * Endpoint para obter o número da porta em que a aplicação está rodando.
     * Este método é útil para testes de balanceamento de carga e verificação
     * de qual instância do microsserviço está atendendo a requisição em
     * ambientes com múltiplas instâncias.
     *
     * @param porta Porta do servidor injetada automaticamente a partir das
     *              propriedades de configuração do Spring
     * @return String contendo uma mensagem informando a porta de origem da resposta
     */

    @GetMapping("/response")
    public String obterPorta(@Value("${local.server.port}") String porta) {
        return String.format("Resposta vinda da porta %s", porta);

    }
}