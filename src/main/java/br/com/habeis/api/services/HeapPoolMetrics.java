/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.habeis.api.services;

import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class HeapPoolMetrics implements PublicMetrics {

    private static final String PREFIX = "mempool.";
    private static final String KEY_EDEN = PREFIX + "eden";
    private static final String KEY_SURVIVOR = PREFIX + "survivor";
    private static final String KEY_TENURED = PREFIX + "tenured";

    @Override
    public Collection<Metric<?>> metrics() {
        Collection<Metric<?>> result = new ArrayList<>(4);
        for (MemoryPoolMXBean mem : ManagementFactory.getMemoryPoolMXBeans()) {
            String poolName = mem.getName();
            String name = null;
            if (poolName.indexOf("Eden Space") != -1) {
                name = KEY_EDEN;
            } else if (poolName.indexOf("Survivor Space") != -1) {
                name = KEY_SURVIVOR;
            } else if (poolName.indexOf("Tenured Gen") != -1 || poolName.indexOf("Old Gen") != -1) {
                name = KEY_TENURED;
            }

            if (name != null) {
                result.add(newMemoryMetric(name, mem.getUsage().getMax()));
                result.add(newMemoryMetric(name + ".init", mem.getUsage().getInit()));
                result.add(newMemoryMetric(name + ".committed", mem.getUsage().getCommitted()));
                result.add(newMemoryMetric(name + ".used", mem.getUsage().getUsed()));
            }
        }
        return result;
    }

    private Metric<Long> newMemoryMetric(String name, long bytes) {
        return new Metric<>(name, bytes / 1024);
    }
}
