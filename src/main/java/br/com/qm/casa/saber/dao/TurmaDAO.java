package br.com.qm.casa.saber.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.qm.casa.saber.entity.Aluno;
import br.com.qm.casa.saber.entity.Professor;
import br.com.qm.casa.saber.entity.Turma;

public class TurmaDAO {

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

	private Map<Integer, Turma> turmas;
	private ProfessorDAO professorDao;
	private AlunoDAO alunoDao;

	public TurmaDAO(AlunoDAO alunoDao, ProfessorDAO professorDao) {
		this.turmas = new HashMap<Integer, Turma>();
		this.professorDao = professorDao;
		this.alunoDao = alunoDao;
	}

	public boolean cadastrarTurma(Turma turma) {
		
		if (this.turmas.get(turma.getCodTurma()) != null) {
			return false; // turma já existe
		}
		
		if (turma.getProfessor() != null) {
			return false; // professor já cadastrado não pode
		}
		
		if (turma.getAlunos().size() > 0) {
			return false; // também não podem haver alunos cadastrados
		}
		
		this.turmas.put(turma.getCodTurma(), turma);
		
		return true;
	}
	
	public boolean adicionaProfessor(int codProfessor, int codTurma) {
		
		Turma turma = this.turmas.get(codTurma);
		
		if (turma == null) {
			return false; //turma não existe
		}
		
		if (turma.getProfessor() != null) {
			return false; // a turma já possui um professor cadastrado
		}
		
		Professor professorAdicionado = this.professorDao.consultaProfessor(codProfessor);
		
		if (professorAdicionado == null) {
			return false; //professor não existe
		}


		
		//Adiciona o professor à turma
		turma.setProfessor(professorAdicionado);
		
		return true;
	}
	
	public boolean adicionaAluno(int matricula, int codTurma) {
		
		Turma turma = this.turmas.get(codTurma);
		
		if (turma == null) {
			return false; //turma não existe
		}
		
		if (turma.getProfessor() == null) {
			return false; // não se pode adicionar um aluno numa turma cujo professor é nulo
		}
		
		Aluno alunoAdicionado = this.alunoDao.consultaAluno(matricula);
		
		if (alunoAdicionado == null) {
			return false; // aluno não existe no banco			
		}
		
		boolean alunoPresente = false;
		for (Aluno aluno : turma.getAlunos()) {
			
			if (aluno.getMatricula() == matricula) {
				alunoPresente = true;
			}
			
		}
		
		if (alunoPresente) {
			return false; // o aluno não pode ser inserido duas vezes
		}
		
		turma.getAlunos().add(alunoAdicionado);
		return true;
	}
	
	public List<Turma> listaTurmas() {
		
		return (List<Turma>) this.turmas.values();
		
	}
	
	public boolean removeTurma(int codTurma) {
		
		if (this.turmas.get(codTurma) == null) {
			return false; // turma não existe
		}
		
		this.turmas.remove(codTurma);
		return true;
	}
	
	public boolean listaDeChamada(int codTurma) {
		
		Turma turma = this.turmas.get(codTurma);
		
		if (turma == null) {
			return false; // turma não existe
		}
		
		List<Aluno> alunos = turma.getAlunos();
		
		if (alunos.size() == 0) {
			return false; // não existem alunos na turma
		}
		
		
		try {
			FileWriter fw = new FileWriter("chamada_" + turma.getCodTurma() + turma.getSala());
			
//			Turma XPTO Sala X
//			Professor: <Nome do Professor>
//			  Nome               Matrícula
//			<Nome Aluno1>    <Matricula aluno1>
//			<Nome Aluno2>    <Matricula aluno2>
			fw.write(String.format("Turma %d - Sala %s\n", turma.getCodTurma(), turma.getSala()));
			fw.write(String.format("Professor: %s\n", turma.getProfessor().getNome()));
			
			fw.write("Nome\tMatrícula\n");
			for (Aluno aluno : alunos) {
				
				fw.write(String.format("%s %5d\n", aluno.getNome(), aluno.getMatricula()));
				
			}
			
			fw.close();
			
		} catch (IOException e) {
			System.err.println("Erro ao escrever arquivo: " + e.getMessage());
		}
		
		return true;
	}
}
