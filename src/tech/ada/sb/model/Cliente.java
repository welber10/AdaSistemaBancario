package tech.ada.sb.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tech.ada.sb.enums.Status;

public abstract class Cliente {
	
	String nome;
	LocalDateTime dataCadastro;
	Status status;
	List<Conta> contas = new ArrayList<>();
	
	protected Cliente(String nome, String numeroconta) {
		this.nome = nome;
		this.dataCadastro = LocalDateTime.now();
		status = Status.ATIVO;
		// Ao criar um cliente, deve ser criada uma conta-corrente vinculada a ele.
		contas.add(new ContaCorrente(this, numeroconta, BigDecimal.ZERO));
	}

	public void adicionarConta(Conta conta) {
		this.contas.add(conta);
	}
	
	public void removerConta(Conta conta) {
		this.contas.remove(conta);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Conta> getContas() {
		return contas;
	}

}
