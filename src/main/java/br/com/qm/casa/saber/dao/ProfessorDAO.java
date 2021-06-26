package br.com.qm.casa.saber.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.qm.casa.saber.entity.Professor;

public class ProfessorDAO {

//	A primeira tarefa é modelar um cadastro completo (Criar, Remover, Consultar e Listar)
//	para essas duas entidades, incluindo a interface de usuário necessária para fazê-la. 
//	Além disso, deveremos ter uma listagem de professores por disciplina, 
//	e uma listagem de alunos por série, além da funcionalidade de promover o aluno de série 
//	(modificar sua série).

	
	
	private EntityManager entityManager;

	public ProfessorDAO() {
		this.entityManager = Persistence.createEntityManagerFactory("casaDoSaber").createEntityManager();
	}
	
	
	
	public boolean cadastraProfessor(Professor professor) {
		
		this.entityManager.getTransaction().begin();;
		this.entityManager.persist(professor);
		this.entityManager.getTransaction().commit();
		
		return true;
	}
	
//	public abstract boolean removeProfessor(int codFuncionario);
	
	public Professor consultaProfessor(int codFuncionario) {
		
		return this.entityManager.find(Professor.class, codFuncionario);
		
	}
	
//	public abstract List<Professor> listaProfessores();
//	
//	public abstract List<Professor> listaProfessoresPorDisciplina(String disciplina);
	
}
