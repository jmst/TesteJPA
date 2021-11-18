package pt.upt.ei.lp.db;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private int numero;
	private String nome;

	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name = "AlunoTurma", 
		joinColumns = { @JoinColumn(name = "idAluno", referencedColumnName = "id") }, 
		inverseJoinColumns = { @JoinColumn(name = "idTurma", referencedColumnName = "id") })
	private List<Turma> turmas = new ArrayList<Turma>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public String toString() {
		String st = "Aluno id=" + id + "  num=" + numero + "  nome=" + nome
				+ "\n";
		for (Turma t : turmas) {
			st += "  turma id " + t.getId() +"  num " + t.getNumero() + "\n";
		}
		return st;
	}

}
