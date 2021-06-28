package br.com.qm.casa.saber;

import java.time.LocalDate;

import br.com.qm.casa.saber.dao.AlunoDAO;
import br.com.qm.casa.saber.dao.ProfessorDAO;
import br.com.qm.casa.saber.dao.TurmaDAO;
import br.com.qm.casa.saber.entity.Aluno;
import br.com.qm.casa.saber.entity.Professor;
import br.com.qm.casa.saber.entity.Turma;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		
		AlunoDAO alunoDao = new AlunoDAO();
//		
//		
//		alunoDao.cadastraAluno(new Aluno(0, "João", 5, LocalDate.of(1996, 3, 12), LocalDate.of(2020, 3, 10)));
//		alunoDao.cadastraAluno(new Aluno(0, "Caio", 5, LocalDate.of(2001, 2, 12), LocalDate.of(2021, 3, 10)));
//		
//		alunoDao.promoveAluno(1);
//		alunoDao.promoveAluno(2);
//		
//		List<Aluno> alunos = alunoDao.listaAlunos();
//		
//		alunoDao.cadastraAluno(new Aluno(0, "Dalmo", 5, LocalDate.of(2002, 2, 12), LocalDate.of(2021, 3, 10)));
//		
//		alunos = alunoDao.listaAlunos();
		
//		AlunoDAO alunoDao = new AlunoDAO();
		ProfessorDAO professorDao = new ProfessorDAO();
		TurmaDAO turmaDao = new TurmaDAO(alunoDao, professorDao);
//		
		alunoDao.cadastraAluno(new Aluno("João", 5, LocalDate.of(1996, 3, 12), LocalDate.of(2020, 3, 10)));
		alunoDao.cadastraAluno(new Aluno("Caio", 5, LocalDate.of(2001, 2, 12), LocalDate.of(2021, 3, 10)));
		professorDao.cadastraProfessor(new Professor("Rogério", "3499859123", "Mestrado", 15000, "Programação"));
		
		Turma turma = new Turma();
		
		turmaDao.cadastrarTurma(turma);
		turmaDao.adicionaProfessor(1, 1);
		turmaDao.adicionaAluno(2, 1);
		turmaDao.adicionaAluno(1, 1);
		
		alunoDao.promoveAluno(1);
		
		professorDao.aumentaSalarioProfessor(1);
		
		
		Turma turmaConsultada = turmaDao.consultaTurma(1);
		turmaDao.listaDeChamada(1);
		
		System.out.println("Stop");
//		Turma turma = new Turma(355, "1B202");
		
//		turmaDao.cadastrarTurma(turma);
		
//		turmaDao.adicionaProfessor(1, 355);
//		turmaDao.adicionaAluno(2, 355);
//		turmaDao.adicionaAluno(1, 355);
//		
//		turmaDao.listaDeChamada(355);
		
		
		
	}
	
}
