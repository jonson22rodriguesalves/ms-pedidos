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

@Service
public class PedidoService {
    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

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