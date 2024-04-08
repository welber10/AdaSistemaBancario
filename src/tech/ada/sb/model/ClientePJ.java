package tech.ada.sb.model;

public class ClientePJ extends Cliente {
	
	public ClientePJ(String cnpj, String nome, String numeroConta) {
		super(nome, numeroConta);
		this.cnpj = cnpj;
	}

	private String cnpj;

	public String getCNPJ() {
		return cnpj;
	}
	
}
