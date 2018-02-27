package br.com.habeis.api.services;

import br.com.habeis.api.domain.Feed;
import br.com.habeis.api.repositories.FeedRepository;
import br.com.habeis.api.repositories.specifications.FeedSpecification;
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
public class FeedService {

    @Autowired
    private FeedRepository repo;

    public Feed create(Feed obj) {
        obj.setId(null);
        obj = repo.save(obj);
        return obj;
    }

    public Feed readById(Integer id) {
        Feed obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                    + ", Tipo: " + Feed.class.getSimpleName());
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

    public Page<Feed> readByCriteria(Integer deviceId, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

        Specification where = applyCriteria(deviceId);

        return repo.findAll(where, pageRequest);
    }

    public Specification applyCriteria(Integer deviceId) {

        Specification where = null;
        
        if (deviceId != null && deviceId > 0L) {
            where = addClause(where, FeedSpecification.whereDevice(deviceId));
        }
        return where;
    }

    private Specification addClause(Specification where, Specification newClause) {
        if (where == null) {
            return where(newClause);
        } else {
            return where(where).and(newClause);
        }
    }

    public Feed update(Feed obj) {
        Feed newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Feed newObj, Feed obj) {
        //newObj.setDescription(obj.getDescription());
    }
}
