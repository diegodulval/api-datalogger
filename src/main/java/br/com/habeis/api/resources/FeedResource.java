package br.com.habeis.api.resources;

import br.com.habeis.api.domain.Device;
import br.com.habeis.api.domain.Feed;
import br.com.habeis.api.domain.Output;
import br.com.habeis.api.dto.FeedDTO;
import br.com.habeis.api.dto.FeedDTONew;
import br.com.habeis.api.resources.utils.DateUtils;
import br.com.habeis.api.resources.utils.URL;
import br.com.habeis.api.services.DeviceService;
import br.com.habeis.api.services.FeedService;
import br.com.habeis.api.services.OutputService;
import br.com.habeis.api.services.exceptions.DataIntegrityException;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Diego Dulval
 */
@RestController
@RequestMapping(value = "/feed")
public class FeedResource {

    @Autowired
    private FeedService service;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private OutputService outputService;

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable Integer id) {
        Feed obj = service.readById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody Feed obj) {
        obj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody Feed obj, @PathVariable Integer id) {
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity read(
            @RequestParam(value = "device", required = true) Integer device,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) throws IOException {

        Page<Feed> list = service.readByCriteria(device, page, linesPerPage, orderBy, direction);
        
        FeedDTO dto = new FeedDTO(deviceService.readById(device), list);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/update")

    public @ResponseBody
    ResponseEntity read(@Valid @ModelAttribute FeedDTONew model) throws IOException {

        Map<String, Object> data = new HashMap<>();

        String sensorsDecode = URL.decodeParam(model.getSensors());
        String outputDecode = URL.decodeParam(model.getOutputs());

        List<Integer> outputList = URL.decodeIntList(outputDecode);
        List<Integer> sensorList = URL.decodeIntList(sensorsDecode);

        String receiveDateTime = URL.decodeParam(model.getDate()) + " " + URL.decodeParam(model.getTime());

        String persistDate = null;

        Map<String, String> dateMap = DateUtils.getMapDataTime();
        String actualDate = dateMap.get(DateUtils.DATE);
        String actualTime = dateMap.get(DateUtils.TIME);
        data.put("date", actualDate);
        data.put("time", actualTime);

        if ("77/77/7777 77:77:77".equals(receiveDateTime)) {
            persistDate = actualDate + " " + actualTime;
        } else {
            
            if (!model.getDate()
                    .matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|2[0-9])[0-9]{2})$")) {
                throw new DataIntegrityException("Informe uma data valída!");
            }
            
            if (!model.getTime()
                    .matches("^([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]")) {
                throw new DataIntegrityException("Informe uma hora valída!");
            }
            
            persistDate = receiveDateTime;
        }

        if (sensorList.size() == 10 && outputList.size() == 5) {

            Device device = deviceService.readById(model.getDevice());

            Feed feed = new Feed();
            feed.setDevice(device);
            feed.setOutputs(outputDecode);
            feed.setCreatedAt(persistDate);
            feed.setSensors(sensorsDecode);

            service.create(feed);

            String outputsReturn = "";

            List<Output> orderOutputs = device.getOutputs()
                    .stream()
                    .sorted((x, y) -> x.getId() - y.getId())
                    .collect(Collectors.toList());

            outputsReturn = orderOutputs
                    .stream()
                    .map((output) -> ((output.getStatus() < 10) ? "0" : "") + output.getStatus())
                    .map((actualOutput) -> actualOutput + ",")
                    .reduce(outputsReturn, String::concat);

            data.put("outputs", outputsReturn.substring(0, outputsReturn.length() - 1));
            data.put("status", "200");
            data.put("device", String.format("%04d", device.getId()));

        } else {
            throw new DataIntegrityException("Nao foi enviado a quantidade correta de sensores(10) e de saidas(5).");
        }

        
        Gson gson = new Gson();
        String json = gson.toJson(data);
        return ResponseEntity.ok().body(json);
    }
}
