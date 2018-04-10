package br.com.habeis.api.services;

import br.com.habeis.api.domain.Feed;
import br.com.habeis.api.domain.Output;
import br.com.habeis.api.dto.OutputDTO;
import br.com.habeis.api.repositories.OutputRepository;
import br.com.habeis.api.services.exceptions.DataIntegrityException;
import br.com.habeis.api.services.exceptions.ObjectNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import static org.springframework.data.jpa.domain.Specifications.where;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego Dulval
 */
@Service
public class OutputService {

    @Autowired
    private OutputRepository repo;

    public Output create(Output obj) {
        obj.setId(null);
        obj = repo.save(obj);
        return obj;
    }

    public Output readById(Integer id) {
        Output obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                    + ", Tipo: " + Output.class.getSimpleName());
        }
        return obj;
    }

    public void delete(Integer id) {
        readById(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há registros relacionados");
        }
    }

    public Page<Feed> readByCriteria(String descDecoded, Integer doctorId, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

        Specification where = applyCriteria(descDecoded, doctorId);

        return repo.findAll(where, pageRequest);
    }

    public Specification applyCriteria(String desc, Integer doctorId) {

        Specification where = null;

//        if (desc != null && !desc.isEmpty()) {
//            where = addClause(where, PosologySpecification.byDescription(desc));
//        }
//
//        if (doctorId != null && doctorId > 0L) {
//            where = addClause(where, PosologySpecification.whereDoctor(doctorId));
//        }
        return where;
    }

    private Specification addClause(Specification where, Specification newClause) {
        if (where == null) {
            return where(newClause);
        } else {
            return where(where).and(newClause);
        }
    }

    public Output update(Output obj) {
        Output newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Output newObj, Output obj) {
        newObj.setStatus(obj.getStatus());
        //newObj.setDescription(obj.getDescription());
    }

    public List<OutputDTO> toDTO(List<Output> outputs) {

        List<OutputDTO> list = new ArrayList<>();

        for (Output output : outputs) {
            OutputDTO dto = new OutputDTO();
            dto.setDescricao(output.getDescription());
            dto.setNome(output.getName());
            dto.setId(output.getId());
            dto.setValor(output.getStatus());
            list.add(dto);
        }

        return list;
    }
}
