/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.habeis.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class GraphDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private DeviceDTO equipamentos;
    private List<SensorDTO> sensores = new ArrayList<>();

}
