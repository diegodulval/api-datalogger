package br.com.habeis.api.dto;

import br.com.habeis.api.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
public class OutputDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nome;
    private String descricao;
    
    private Integer valor;

    public OutputDTO() {
    }
}
