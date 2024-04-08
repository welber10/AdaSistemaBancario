package tech.ada.sb;

import java.math.BigDecimal;

import tech.ada.sb.exception.SaldoIndisponivelException;
import tech.ada.sb.model.ClientePF;
import tech.ada.sb.model.ClientePJ;
import tech.ada.sb.model.Conta;
import tech.ada.sb.model.ContaInvestimento;
import tech.ada.sb.model.ContaPoupanca;
import tech.ada.sb.service.operacao.deposito.DepositoConta;
import tech.ada.sb.service.operacao.investimento.InvestimentoComRendimento;
import tech.ada.sb.service.operacao.rentabiliza.JurosPoupanca;
import tech.ada.sb.service.operacao.rentabiliza.RendimentoInvestimentoPF;
import tech.ada.sb.service.operacao.rentabiliza.RendimentoInvestimentoPJ;
import tech.ada.sb.service.operacao.saldo.ConsultaSaldo;
import tech.ada.sb.service.operacao.saque.Saque;
import tech.ada.sb.service.operacao.saque.SaqueComTarifa;
import tech.ada.sb.service.operacao.saque.SaqueConta;
import tech.ada.sb.service.operacao.tarifa.TaxaPJ;
import tech.ada.sb.service.operacao.transferencia.Transferencia;
import tech.ada.sb.service.operacao.transferencia.TransferenciaBancaria;
import tech.ada.sb.service.operacao.transferencia.TransferenciaComTarifa;

public class SistemaBancario {

	public static void main(String[] args) {
		
		ClientePF cliente1 = new ClientePF("cpf1", "Cliente 1", "cc1");
		ClientePF cliente2 = new ClientePF("cpf2", "Cliente 2", "cc2");
		ClientePJ cliente3 = new ClientePJ("cnpj3", "Cliente 3", "cc3");
		ClientePJ cliente4 = new ClientePJ("cnpj4", "Cliente 4", "cc4");

		Conta cc1 = cliente1.getContas().get(0);
		Conta cc2 = cliente2.getContas().get(0);
		Conta cc3 = cliente3.getContas().get(0);
		Conta cc4 = cliente4.getContas().get(0);
		
		ContaPoupanca cp2 = new ContaPoupanca(cliente2, "cp2", BigDecimal.ZERO, new JurosPoupanca());
		
		ContaInvestimento ci4 = new ContaInvestimento(cliente4, "ci4", BigDecimal.ZERO, new RendimentoInvestimentoPJ());
		
		iniciarOperacao("Saldo - Consulta saldo zerado");
		consultarSaldo(cc1);
		
		iniciarOperacao("Deposito");
		depositar(500, cc1);
		
		iniciarOperacao("Saque com saldo disponível");
		sacar(300, cc1);
		
		iniciarOperacao("Saque com saldo indisponível");
		sacar(300, cc1);

		iniciarOperacao("Deposito");
		depositar(5000, cc3);
		
		iniciarOperacao("Transferencia com saldo disponivel");
		transferir(1000, cc3, cc1);
		
		iniciarOperacao("Saque com saldo disponível");
		sacar(300, cc1);
		
		iniciarOperacao("Transferencia com saldo indisponivel");
		transferir(1000, cc2, cc4);
		
		iniciarOperacao("Deposito");
		depositar(10000, cc4);
		
		iniciarOperacao("Investir sem saldo");
		investir(1000, ci4);
		
		iniciarOperacao("Transferencia conta corrente para conta investimento");
		transferir(1000, cc4, ci4);
		
		iniciarOperacao("Investir com saldo");
		investir(1000, ci4);
		
		iniciarOperacao("Deposito poupanca");
		depositar(7000, cp2);
		
		iniciarOperacao("Saldo - Poupança");
		consultarSaldo(cp2);
		
		iniciarOperacao("Transferencia conta poupança para conta corrente");
		transferir(500, cp2, cc2);
		
		iniciarOperacao("Saque com saldo disponível poupança");
		sacar(1300, cp2);
		
		iniciarOperacao("Calculo com Juros");
		System.out.println("Valor dos juros calculados " + cp2.getRentabilidade().calcular(cp2.getSaldo()));
		
		ClientePF clientePF = new ClientePF("cpf", "Cliente PF", "ccpf");
		ClientePJ clientePJ = new ClientePJ("cnpj", "Cliente PJ", "ccpj");
		
		Conta ccpf = clientePF.getContas().get(0);
		Conta ccpj = clientePJ.getContas().get(0);
		
		iniciarOperacao("Deposito PF");
		depositar(1000, ccpf);
		
		iniciarOperacao("Deposito PJ");
		depositar(1000, ccpj);
		
		iniciarOperacao("Saque PF");
		sacar(300, ccpf);
		
		iniciarOperacao("Saque PJ");
		sacar(300, ccpj);
		
		iniciarOperacao("Transferencia PF > PJ");
		transferir(200, ccpf, ccpj);
		
		iniciarOperacao("Transferencia PJ > PF");
		transferir(200, ccpj, ccpf);
		
		ContaInvestimento cipf = new ContaInvestimento(clientePF, "cipf", BigDecimal.valueOf(10000), new RendimentoInvestimentoPF());
		ContaInvestimento cipj = new ContaInvestimento(clientePJ, "cipj", BigDecimal.valueOf(10000), new RendimentoInvestimentoPJ());
		
		iniciarOperacao("Investimento PF");
		investir(1000, cipf);
		
		iniciarOperacao("Investimento PF");
		investir(1000, cipj);
	}

	private static void investir(double valor, ContaInvestimento conta) {
		System.out.println("Saldo da conta " + conta.getNumeroConta() + " antes do investimento: " + new ConsultaSaldo().consultar(conta).toString());
		System.out.println("Valor investido na conta " + conta.getNumeroConta() + " antes do investimento: " + conta.getInvestimento().toString());
		System.out.println("Investimento - no valor de " + valor);
		try {
			new InvestimentoComRendimento().investir(BigDecimal.valueOf(valor), conta);
		} catch (SaldoIndisponivelException e) {
			System.out.println("Investimento - Erro ao tentar realizar a operacao");
			e.printStackTrace();
		}
		System.out.println("Saldo da conta " + conta.getNumeroConta() + " após o investimento: " + new ConsultaSaldo().consultar(conta).toString());
		System.out.println("Valor investido na conta " + conta.getNumeroConta() + " após o investimento: " + conta.getInvestimento().toString());
	}

	private static void consultarSaldo(Conta conta) {
		System.out.println("Saldo da conta " + conta.getNumeroConta() + ": " + new ConsultaSaldo().consultar(conta).toString());
	}

	private static void iniciarOperacao(String operacao) {
		System.out.println("-------------------------------------------------");
		System.out.println("--- " + operacao + " ---");
	}

	private static void transferir(double valor, Conta contaOrigem, Conta contaDestino) {
		System.out.println("Saldo da conta origem " + contaOrigem.getNumeroConta() + " antes da transferencia: " + new ConsultaSaldo().consultar(contaOrigem).toString());
		System.out.println("Saldo da conta destino " + contaDestino.getNumeroConta() + " antes da transferencia: " + new ConsultaSaldo().consultar(contaDestino).toString());
		System.out.println("Transferencia - no valor de " + valor + " da conta " + contaOrigem.getNumeroConta() + " para a conta " + contaDestino.getNumeroConta());
		
		Transferencia transferencia = null;
		
		if (contaOrigem.getTitular() instanceof ClientePJ)
			transferencia = new TransferenciaComTarifa(new TaxaPJ());
		else
			transferencia = new TransferenciaBancaria();
		
		try {
			transferencia.transferir(BigDecimal.valueOf(valor), contaOrigem, contaDestino);
		} catch (SaldoIndisponivelException e) {
			System.out.println("Transferencia - Erro ao tentar realizar a operacao");
			e.printStackTrace();
		}
		
		System.out.println("Saldo da conta origem " + contaOrigem.getNumeroConta() + " após a transferencia: " + new ConsultaSaldo().consultar(contaOrigem).toString());
		System.out.println("Saldo da conta destino " + contaDestino.getNumeroConta() + " após a transferencia: " + new ConsultaSaldo().consultar(contaDestino).toString());
	}

	private static void sacar(double valor, Conta conta) {
		System.out.println("Saldo da conta " + conta.getNumeroConta() + " antes do saque: " + new ConsultaSaldo().consultar(conta).toString());
		System.out.println("Saque - Saque no valor de " + valor);
		
		Saque saque = null;
		
		if (conta.getTitular() instanceof ClientePJ)
			saque = new SaqueComTarifa(new TaxaPJ());
		else
			saque = new SaqueConta();
		
		try {
			saque.sacar(BigDecimal.valueOf(valor), conta);
		} catch (SaldoIndisponivelException e) {
			System.out.println("Saque - Erro ao tentar realizar a operacao");
			e.printStackTrace();
		}
		System.out.println("Saldo da conta " + conta.getNumeroConta() + " após o saque: " + new ConsultaSaldo().consultar(conta).toString());
	}

	private static void depositar(double valor, Conta conta) {
		System.out.println("Saldo da conta " + conta.getNumeroConta() + " antes do deposito: " + new ConsultaSaldo().consultar(conta).toString());
		System.out.println("Deposito - Deposito no valor de " + valor);
		new DepositoConta().depositar(BigDecimal.valueOf(valor), conta);
		System.out.println("Saldo da conta " + conta.getNumeroConta() + " após o deposito: " + new ConsultaSaldo().consultar(conta).toString());
	}
	
	

}
