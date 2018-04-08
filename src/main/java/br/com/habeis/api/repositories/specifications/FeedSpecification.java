/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.habeis.api.repositories.specifications;

import br.com.habeis.api.domain.Feed;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author diego
 */
public final class FeedSpecification {

    public static Specification whereSensor(Integer param) {
        return (Specification<Feed>) (Root<Feed> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> cb.equal(root.join("sensor").get("id"), param);
    }

    public static Specification whereDevice(Integer param) {
        return (Specification<Feed>) (Root<Feed> root, CriteriaQuery<?> query, CriteriaBuilder cb)
                -> cb.equal(root.join("sensor").join("device").get("id"), param);
    }

}
