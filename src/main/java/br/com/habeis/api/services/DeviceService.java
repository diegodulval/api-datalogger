package br.com.habeis.api.services;

import br.com.habeis.api.domain.Device;
import br.com.habeis.api.domain.Feed;
import br.com.habeis.api.repositories.DeviceRepository;
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
public class DeviceService {

    @Autowired
    private DeviceRepository repo;

    public Device create(Device obj) {
        obj.setId(null);
        obj = repo.save(obj);
        return obj;
    }

    public Device readById(Integer id) {
        Device obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                    + ", Tipo: " + Device.class.getSimpleName());
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

    public Page<Device> readByCriteria(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

        Specification where = applyCriteria();

        return repo.findAll(where, pageRequest);
    }

    public Specification applyCriteria() {

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

    public Device update(Device obj) {
        Device newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Device newObj, Device obj) {
        //newObj.setDescription(obj.getDescription());
    }
}
