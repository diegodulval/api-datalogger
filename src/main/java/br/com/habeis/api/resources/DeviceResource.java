package br.com.habeis.api.resources;

import br.com.habeis.api.domain.Device;
import br.com.habeis.api.domain.Output;
import br.com.habeis.api.services.DeviceService;
import br.com.habeis.api.services.FeedService;
import br.com.habeis.api.services.OutputService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Diego Dulval
 */
@RestController
@RequestMapping(value = "/device")
public class DeviceResource {

    @Autowired
    private DeviceService service;

    @Autowired
    private FeedService deviceService;

    @Autowired
    private OutputService outputService;

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable Integer id) {
        Device obj = service.readById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody Device obj) {
        obj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody Device obj, @PathVariable Integer id) {
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/outputs")
    public ResponseEntity update(@Valid @RequestBody List<Output> list, @PathVariable Integer id) {

        for (Output obj : list) {
            obj.setDevice(service.readById(id));
            outputService.update(obj);
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity read(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Device> list = service.readByCriteria(page, linesPerPage, orderBy, direction);

        return ResponseEntity.ok().body(list);
    }

}
