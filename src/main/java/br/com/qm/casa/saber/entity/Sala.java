package br.com.qm.casa.saber.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nro_sala")
	private int nroSala;
	
	private float largura;
	
	private float comprimento;
	
	private float altura;
	
	@OneToOne
	@JoinColumn(name = "turma_fk", referencedColumnName = "cod_turma")
	private Turma turma;

	public Sala(int nroSala, float largura, float comprimento, float altura, Turma turma) {
		this.nroSala = nroSala;
		this.largura = largura;
		this.comprimento = comprimento;
		this.altura = altura;
		this.turma = turma;
	}

	public Sala() {
	}

	public int getNroSala() {
		return nroSala;
	}

	public void setNroSala(int nroSala) {
		this.nroSala = nroSala;
	}

	public float getLargura() {
		return largura;
	}

	public void setLargura(float largura) {
		this.largura = largura;
	}

	public float getComprimento() {
		return comprimento;
	}

	public void setComprimento(float comprimento) {
		this.comprimento = comprimento;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
}
