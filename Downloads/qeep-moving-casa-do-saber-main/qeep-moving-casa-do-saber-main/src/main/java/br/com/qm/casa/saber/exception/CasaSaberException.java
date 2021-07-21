package br.com.qm.casa.saber.exception;

public class CasaSaberException extends Exception {

	private static final long serialVersionUID = 4226286023895905130L;
	
	private String mensagemDeErro;
	
	public CasaSaberException(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}
	
	public String getMensagemDeErro() {
		return mensagemDeErro;
	}
}
