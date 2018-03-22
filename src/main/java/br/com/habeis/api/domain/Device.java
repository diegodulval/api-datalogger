package br.com.habeis.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author diego-dulval
 */
@Data
@Entity
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @OneToMany(mappedBy = "device")
    private List<Sensor> sensors = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    private List<Output> outputs = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "device")
    private List<Feed> feeds = new ArrayList<>();
    
    public Device(){}

}
