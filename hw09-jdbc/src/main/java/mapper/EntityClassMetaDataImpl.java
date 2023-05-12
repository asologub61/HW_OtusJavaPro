package mapper;

import annotation.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EntityClassMetaDataImpl<T> implements EntityClassMetaData {
    private final Class<T> entityClass;


    public EntityClassMetaDataImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    @Override
    public String getName() {
        return entityClass.getSimpleName();
    }

    @Override
    public Constructor getConstructor(){
         try {
            return entityClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Field getIdField() {
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        throw new RuntimeException();
    }

    @Override
    public List<Field> getAllFields() {
        return Arrays.asList(entityClass.getDeclaredFields());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        List<Field> fieldList = new ArrayList<>();
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class) == false) {
                fieldList.add(field);
            }
        }
        return fieldList;
    }
}
