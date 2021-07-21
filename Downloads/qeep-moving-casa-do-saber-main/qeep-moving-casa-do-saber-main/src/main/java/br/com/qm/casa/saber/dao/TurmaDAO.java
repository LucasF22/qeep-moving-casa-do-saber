package br.com.qm.casa.saber.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.qm.casa.saber.entity.Aluno;
import br.com.qm.casa.saber.entity.Professor;
import br.com.qm.casa.saber.entity.Turma;
import br.com.qm.casa.saber.exception.CasaSaberException;

public class TurmaDAO { 

	private Map<Integer, Turma> turmas;
	private ProfessorDAO professorDao;
	private AlunoDAO alunoDao;

	public TurmaDAO(AlunoDAO alunoDao, ProfessorDAO professorDao) {
		this.turmas = new HashMap<Integer, Turma>();
		this.professorDao = professorDao;
		this.alunoDao = alunoDao;
	}

	public boolean cadastrarTurma(Turma turma) throws CasaSaberException {
		
		if (this.turmas.get(turma.getCodTurma()) != null) {
			throw new CasaSaberException("Turma j� existe. Verifique codigo se est� correto!");
		}
		
		if (turma.getProfessor() != null)  {
			throw new CasaSaberException("Professor j� cadastrado em turma. Verifique codigo se est� correto!");
		}
		
		if (turma.getAlunos().size() > 0)  {
			throw new CasaSaberException("Nova turma n�o p�de ser cadastrada. Existem alunos vinculados a turma.");
		}
		
		this.turmas.put(turma.getCodTurma(), turma);
		
		return true;
	}
	
	public boolean adicionaProfessor(int codProfessor, int codTurma) throws CasaSaberException{
		
		Turma turma = this.turmas.get(codTurma);
		
		turmaNaoExiste(turma);
		
		if (turma.getProfessor() != null) {
			throw new CasaSaberException("Turma j� possui professor. Verifique c�digo se est� correto.");
		}
		
		Professor professorAdicionado = this.professorDao.consultaProfessor(codProfessor);
		
		if (professorAdicionado == null) {
			throw new CasaSaberException("Professor n�o existe. Verifique codigo se est� correto!");
		}
		turma.setProfessor(professorAdicionado);
		
		return true;
	}
	
	public boolean adicionaAluno(int matricula, int codTurma) throws CasaSaberException {
		
		Turma turma = this.turmas.get(codTurma);
		
		turmaNaoExiste(turma);
		
		if (turma.getProfessor() == null) {
			throw new CasaSaberException("Turma n�o possui professor.");
		}
		
		Aluno alunoAdicionado = this.alunoDao.consultaAluno(matricula);
		
		if (alunoAdicionado == null) {
			throw new CasaSaberException("Aluno n�o matriculado! Verifique matricula est� correta");
		
		}
		
		boolean alunoPresente = false;
		for (Aluno aluno : turma.getAlunos()) {
			
			if (aluno.getMatricula() == matricula) {
				alunoPresente = true;
			}
			
		}
		
		if (alunoPresente) {
			throw new CasaSaberException("Aluno n�o p�de ser cadastrado nesta turma. Porque j� est� cadastrado na turma. ");

		}
		
		turma.getAlunos().add(alunoAdicionado);
		return true;
	}
	
	public List<Turma> listaTurmas() {
		
		return (List<Turma>) this.turmas.values();
		
	}
	
	public boolean removeTurma(int codTurma) throws CasaSaberException {
		
		if (this.turmas.get(codTurma) == null) {
			throw new CasaSaberException("Turma n�o existe. Verifique codigo se est� correto.");
		}
		
		this.turmas.remove(codTurma);
		return true;
	}
	
	public boolean listaDeChamada(int codTurma) throws CasaSaberException {
		
		Turma turma = this.turmas.get(codTurma);
		
		turmaNaoExiste(turma);
		
		List<Aluno> alunos = turma.getAlunos();
		
		if (alunos.size() == 0) {
			throw new CasaSaberException("N�o existe alunos nesta turma.");
		}

		try {
			FileWriter fw = new FileWriter("chamada_" + turma.getCodTurma() + turma.getSala());
			
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
	public void turmaNaoExiste(Turma turma) throws CasaSaberException {		
		if (turma == null) {
			throw new CasaSaberException("Turma n�o existe. Verifique codigo se est� correto.");
		}
	}
}
