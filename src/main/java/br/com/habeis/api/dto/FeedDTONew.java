/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.habeis.api.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class FeedDTONew implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    Integer device;

    @NotBlank
    String sensors;

    @NotBlank
    String outputs;

    @NotBlank
    @Size(min = 6, max = 8)
    String time;
    
    @NotBlank
    @Size(min = 8, max = 10)
    String date;
}
