package com.dynaccurateDesafio.conections;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import com.dynaccurate.constantes.RabbitMQConstantes;

@Component
public class RabbitMQConections {

	private static final String NomeExchange = "amq.direct";

	private AmqpAdmin admin;

	public RabbitMQConections(AmqpAdmin admin) {
		this.admin = admin;

	}

	private Queue fila(String nomeFila) {
		return new Queue(nomeFila, true, false, false);
	}

	private DirectExchange trocaDireta() {
		return new DirectExchange(NomeExchange);
	}

	private Binding relacionamento(Queue fila, DirectExchange troca) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
	}

	@PostConstruct
	private void adiciona() {
		Queue filaEvento = this.fila(RabbitMQConstantes.FILA_EVENTO);
		DirectExchange troca = this.trocaDireta();
		Binding conexao = this.relacionamento(filaEvento, troca);

		this.admin.declareQueue(filaEvento);
		this.admin.declareExchange(troca);
		this.admin.declareBinding(conexao);

	}
}
