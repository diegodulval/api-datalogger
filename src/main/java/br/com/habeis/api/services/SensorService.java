package br.com.habeis.api.services;

import br.com.habeis.api.domain.Sensor;
import br.com.habeis.api.repositories.SensorRepository;
import br.com.habeis.api.services.exceptions.DataIntegrityException;
import br.com.habeis.api.services.exceptions.ObjectNotFoundException;
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
public class SensorService {

    @Autowired
    private SensorRepository repo;

    public Sensor create(Sensor obj) {
        obj.setId(null);
        obj = repo.save(obj);
        return obj;
    }

    public Sensor readById(Integer id) {
        Sensor obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                    + ", Tipo: " + Sensor.class.getSimpleName());
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

    public Page<Sensor> readByCriteria(String descDecoded, Integer doctorId, Integer page, Integer linesPerPage, String orderBy, String direction) {
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

    public Sensor update(Sensor obj) {
        Sensor newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Sensor newObj, Sensor obj) {
        //newObj.setDescription(obj.getDescription());
    }
}
