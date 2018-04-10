package br.com.habeis.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data

@Entity
public class Feed implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String createdAt;
    
    private Double value;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    public Feed() {
    }
}
