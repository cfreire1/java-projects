package com.example.api.domain.services;

import com.example.api.domain.dto.CategoryRequestDTO;
import com.example.api.domain.dto.CategoryResponseDTO;
import com.example.api.domain.mapper.CategoryRequestMapper;
import com.example.api.domain.mapper.CategoryResponseMapper;
import com.example.api.persistence.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//Logica de negocio
public class CategoryServicesImpl implements CategoryServices{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryRequestMapper categoryRequestMapper;

    @Autowired
    private CategoryResponseMapper categoryResponseMapper;


    @Override
    public List<CategoryResponseDTO> getAll() {
        return categoryResponseMapper.mapperListEntityToDto(categoryRepository.getAll());
    }

    @Override
    public Optional<CategoryResponseDTO> findBy(int id) {
        return categoryResponseMapper.mapperEntityToDto(categoryRepository.findBy(id));
    }

    @Override
    public Optional<CategoryRequestDTO> save(CategoryRequestDTO categoryRequestDTO) {
        return categoryRequestMapper
                .mapperEntityToDto(categoryRepository
                        .save(categoryRequestMapper.mapperDtoToEntity(categoryRequestDTO)));
    }

    @Override
    public Optional<CategoryRequestDTO> update(CategoryRequestDTO categoryRequestDTO, int id) {

        //https://howtodoinjava.com/spring-rest/spring-rest-crud-jpa-example/
        //https://stackoverflow.com/questions/59065939/jpa-repository-save-doesnt-update-existing-data
        //https://docs.jboss.org/hibernate/core/3.3/reference/en/html/objectstate.html
        //http://www.atopecode.net/optionals-en-java-parte-3-map-vs-flatmap/
        return categoryRequestMapper
                .mapperEntityToDto(categoryRepository
                        .findBy(id)//1 - Obtenemos los registros para obtener una session separada(stated Detached) y modificarla de forma separada
                            .flatMap(categoryEntity -> {//Con flatMap devolvemos directamente un Optional
                                categoryEntity = categoryRequestMapper.mapperDtoToEntity(categoryEntity,categoryRequestDTO);// 2. modificamos los valores dentro de la session obtenida
                                return categoryRepository.save(categoryEntity);//3. Guardamos la informacion modificada, y hibernate ejecutara la actualizacion. (Nota: si la session es transitoria(Transient) insertara registro)
                            }));
    }

    @Override
    public boolean delete(int id) {
        if (findBy(id).isPresent()){
            categoryRepository.delete(id);
            return true;
        }
        return false;
    }
}
