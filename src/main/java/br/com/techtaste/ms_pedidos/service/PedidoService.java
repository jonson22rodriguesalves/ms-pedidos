package br.com.techtaste.ms_pedidos.service;

import br.com.techtaste.ms_pedidos.dto.PedidoRequestDto;
import br.com.techtaste.ms_pedidos.dto.PedidoResponseDto;
import br.com.techtaste.ms_pedidos.model.Pedido;
import br.com.techtaste.ms_pedidos.model.Status;
import br.com.techtaste.ms_pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Camada de serviço responsável pela lógica de negócio relacionada a pedidos.
 * Esta classe atua como a camada intermediária entre o controlador (PedidoController)
 * e o repositório (PedidoRepository), encapsulando toda a lógica de negócio,
 * validações e regras específicas para o gerenciamento de pedidos.
 * Utiliza a anotação @Service para ser reconhecida pelo Spring como um bean
 * de serviço e @Transactional para gerenciar transações de banco de dados.
 * @author TechTaste Team
 * @version 1.0
 */

@Service
public class PedidoService {

    /**
     * Repositório de pedidos injetado via construtor.
     * Responsável pelas operações de persistência e consulta ao banco de dados.
     * A injeção via construtor é a abordagem recomendada pelo Spring para
     * facilitar testes e garantir a imutabilidade do serviço.
     */

    private final PedidoRepository repository;

    /**
     * Construtor para injeção de dependência do repositório.
     *
     * @param repository Repositório de pedidos a ser injetado
     */

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    /**
     * Cadastra um novo pedido no sistema.
     * Este método realiza o processo de criação de um pedido, incluindo:
     * - Criação da entidade Pedido
     * - Mapeamento dos dados do DTO para a entidade
     * - Definição da data atual como data do pedido
     * - Definição do status inicial como AGUARDANDO_PAGAMENTO
     * - Configuração do relacionamento bidirecional com os itens
     * - Cálculo do valor total do pedido
     * - Persistência no banco de dados
     * - Conversão da entidade para DTO de resposta
     * A anotação @Transactional garante que todas as operações de banco
     * de dados sejam executadas dentro de uma transação, com atomicidade
     * e isolamento adequados.
     *
     * @param pedidoDto DTO contendo os dados para criação do pedido
     * @return PedidoResponseDto com os dados do pedido cadastrado
     */

    @Transactional
    public PedidoResponseDto cadastrarPedido(PedidoRequestDto pedidoDto) {
        Pedido pedido = new Pedido();

        // Mapeamento manual para evitar problemas com BeanUtils
        pedido.setCpf(pedidoDto.cpf());
        pedido.setData(LocalDate.now());
        pedido.setStatus(Status.AGUARDANDO_PAGAMENTO);

        // O setItens já configura o relacionamento bidirecional
        pedido.setItens(pedidoDto.itens());
        pedido.calcularTotal();

        Pedido pedidoSalvo = repository.save(pedido);

        return new PedidoResponseDto(
                pedidoSalvo.getId(),
                pedidoSalvo.getStatus(),
                pedidoSalvo.getCpf(),
                pedidoSalvo.getItens(),
                pedidoSalvo.getValorTotal(),
                pedidoSalvo.getData()
        );
    }

    /**
     * Obtém todos os pedidos cadastrados no sistema.
     * Este método consulta o repositório para recuperar todos os pedidos
     * existentes no banco de dados e os converte para uma lista de DTOs
     * de resposta, utilizando Stream API para transformar cada entidade
     * em seu respectivo DTO.
     * Este método não possui a anotação @Transactional pois é apenas
     * uma operação de leitura, dispensando o overhead de uma transação
     * ativa.
     *
     * @return Lista de PedidoResponseDto contendo todos os pedidos cadastrados
     */

    public List<PedidoResponseDto> obterTodos() {
        return repository.findAll().stream()
                .map(pedido -> new PedidoResponseDto(
                        pedido.getId(),
                        pedido.getStatus(),
                        pedido.getCpf(),
                        pedido.getItens(),
                        pedido.getValorTotal(),
                        pedido.getData()))
                .collect(Collectors.toList());
    }
}