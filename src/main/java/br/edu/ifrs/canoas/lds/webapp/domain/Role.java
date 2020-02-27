package br.edu.ifrs.canoas.lds.webapp.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rodrigo on 3/18/17.
 */
@Entity
@Data
@NoArgsConstructor
public class Role {

	@Id @GeneratedValue
	private Long id;
	private String role;
}
