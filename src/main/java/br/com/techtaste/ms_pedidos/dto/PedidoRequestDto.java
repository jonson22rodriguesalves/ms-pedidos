package br.com.techtaste.ms_pedidos.dto;

import br.com.techtaste.ms_pedidos.model.ItemPedido;

import java.util.List;

/**
 * Data Transfer Object (DTO) para requisições de criação de pedidos.
 * Esta classe record é utilizada para receber os dados de um novo pedido
 * através das requisições HTTP, encapsulando as informações necessárias
 * para a criação de um pedido no sistema.
 * O uso de records (introduzido no Java 14 como preview e oficializado
 * no Java 16) proporciona uma forma concisa e imutável de definir DTOs,
 * com implementação automática de construtor, getters, equals, hashCode
 * e toString.
 * @author TechTaste Team
 * @version 1.0
 * @param cpf   CPF do cliente que está realizando o pedido. Este campo
 *              identifica o cliente no sistema e é utilizado para associar
 *              o pedido ao seu respectivo cliente.
 * @param itens Lista de itens que compõem o pedido. Cada item contém
 *              informações como identificador do produto, quantidade
 *              e preço unitário.
 */

public record PedidoRequestDto(String cpf, List<ItemPedido> itens) {

}