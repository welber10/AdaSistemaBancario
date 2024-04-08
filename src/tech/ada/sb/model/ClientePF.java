package tech.ada.sb.model;

public class ClientePF extends Cliente {
	
	public ClientePF(String cpf, String nome, String numeroConta) {
		super(nome, numeroConta);
		this.cpf = cpf;
	}

	private String cpf;

	public String getCPF() {
		return cpf;
	}

}
