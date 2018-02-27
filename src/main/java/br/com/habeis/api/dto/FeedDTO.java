/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.habeis.api.dto;

import br.com.habeis.api.domain.Device;
import br.com.habeis.api.domain.Feed;
import br.com.habeis.api.resources.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 *
 * @author diego
 */
@Data
public class FeedDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "UTC")
    private Date dateTime;

    private Device device;
    private Page<Feed> feeds;

    public FeedDTO(Device device, Page<Feed> feeds) throws IOException {
        this.dateTime = new Date();
        this.device = device;
        this.feeds = feeds;
    }

    public FeedDTO() {
    }

}
