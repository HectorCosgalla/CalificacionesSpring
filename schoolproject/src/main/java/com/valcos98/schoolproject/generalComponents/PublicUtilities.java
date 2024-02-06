package com.valcos98.schoolproject.generalComponents;

import org.springframework.data.jpa.repository.JpaRepository;

public class PublicUtilities {
    public static <T> T getModelObjectById(Long id, JpaRepository<T,Long> repository){
        if (id != null) {
            return repository.findById(id).get();
        }
        return null;
    }
}
