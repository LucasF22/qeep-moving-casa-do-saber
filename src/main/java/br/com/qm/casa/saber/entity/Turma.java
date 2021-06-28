package br.com.qm.casa.saber.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Turma {

//	Você deverá modelar em seu sistema um menu (e todas as classes que julgar necessário) para gerir as turmas, através dos quais nós seremos capazes de 
//	- criar uma turma, 
//	- adicionar um professor à uma turma, 
//	- adicionar um aluno à uma turma (só deverá ser possível caso a turma já possua um professor), 
//	- imprimir a lista de chamada (gerar um arquivo TXT com o nome do professor e dos alunos daquela turma), 
//	EX: 
//		Turma XPTO Sala X
//		Professor: <Nome do Professor>
//		  Nome               Matrícula
//		<Nome Aluno1>    <Matricula aluno1>
//		<Nome Aluno2>    <Matricula aluno2>
//
//
//	- listar as turmas (mostrar somente código e sala) 
//	- e excluir uma turma. 

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_turma")
	private int codTurma;

	@OneToOne(mappedBy = "turma")
	private Sala sala;

	@ManyToOne
	@JoinColumn(name = "cod_funcionario_fk", referencedColumnName = "cod_funcionario")
	private Professor professor;

	@ManyToMany
	@JoinTable(name = "turmas_alunos", 
	joinColumns = @JoinColumn(name = "turma_fk", referencedColumnName = "cod_turma"), 
	inverseJoinColumns = @JoinColumn(name = "aluno_fk", referencedColumnName = "matricula"))
	private List<Aluno> alunos;


	public Turma(int codTurma, Sala sala, Professor professor, List<Aluno> alunos) {
		this.codTurma = codTurma;
		this.sala = sala;
		this.professor = professor;
		this.alunos = alunos;
	}
	
	public Turma() {
		this.alunos = new ArrayList<Aluno>();
	}



	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public int getCodTurma() {
		return codTurma;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	

}
