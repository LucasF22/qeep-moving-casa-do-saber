package br.com.qm.casa.saber.entity;

import java.util.ArrayList;
import java.util.List;

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
	
	private int codTurma;
	
	private String sala;
	
	private Professor professor;

	private List<Aluno> alunos;

	public Turma(int codTurma, String sala) {
		
		this.codTurma = codTurma;
		this.sala = sala;
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

	public String getSala() {
		return sala;
	}
	
}
