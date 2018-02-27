/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.habeis.api.repositories.specifications;

import br.com.habeis.api.domain.Output;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author diego
 */
public final class OutputSpecification {

    public static Specification whereDevice(Integer param) {
        return (Specification<Output>) (Root<Output> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> cb.equal(root.join("device").get("id"), param);
    }

}
