package br.com.qm.casa.saber.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.qm.casa.saber.entity.Aluno;
import br.com.qm.casa.saber.entity.Professor;
import br.com.qm.casa.saber.exception.CasaSaberException;

public class ProfessorDAO {
	
	private EntityManager entityManager;

	public ProfessorDAO() {
		this.entityManager = Persistence.createEntityManagerFactory("casaDoSaber").createEntityManager();
	}
	
	public boolean cadastraProfessor(Professor professor) throws CasaSaberException {
		professor = this.entityManager.find(Professor.class, professor.getCodFuncionario());
		
		if(professor != null) {
			throw new CasaSaberException("Aluno já está cadastrado!");
		}
		this.entityManager.getTransaction().begin();;
		this.entityManager.persist(professor);
		this.entityManager.getTransaction().commit();
		
		return true;
	}
	
	public boolean removeProfessor(int codFuncionario) throws CasaSaberException {
		Professor professor = this.entityManager.find(Professor.class, codFuncionario);
		if (professor == null) {
			throw new CasaSaberException("Professor não existe! Verifique se a codigo informado está correto!");
		}

		this.entityManager.getTransaction().begin();
		this.entityManager.remove(professor);
		this.entityManager.getTransaction().commit();

		return true;
	}
	
	public Professor consultaProfessor(int codFuncionario) {		
		return this.entityManager.find(Professor.class, codFuncionario);	
	}
	
	public  List<Professor> listaProfessores(){
		Query query = this.entityManager.createQuery("select p from Professor p");

		return query.getResultList();
	}
	public List<Professor> listaProfessoresPorDisciplina(String disciplina){ 
		Query query = this.entityManager.createQuery("select p from Professor p where lower(p.disciplina) like lower(contact('%', :disciplina,'%'");
		query.setParameter("disciplina", disciplina);

		return query.getResultList();
	}
	
}
