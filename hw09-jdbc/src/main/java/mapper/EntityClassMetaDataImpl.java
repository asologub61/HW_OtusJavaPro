package mapper;

import annotation.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EntityClassMetaDataImpl implements EntityClassMetaData {
    private final String name;
    private final Constructor constructor;
    private final List<Field> allFields;

    private Field fieldWithId;

    private List<Field> fieldListWithoutId;

    public EntityClassMetaDataImpl(Class entityClass)  {
        this.name = entityClass.getSimpleName();
        try {
            this.constructor = entityClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        this.allFields = Arrays.asList(entityClass.getDeclaredFields());
        this.fieldListWithoutId = new ArrayList<>();

        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                this.fieldWithId = field;
            }
        }

        for (Field field : entityClass.getDeclaredFields()) {
            if ((field.isAnnotationPresent(Id.class) == false)) {
                fieldListWithoutId.add(field);
            }
        }
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Constructor getConstructor() {
        return constructor;
    }


    @Override
    public Field getIdField() {
        return fieldWithId;
    }

    @Override
    public List<Field> getAllFields() {
        return allFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {

        return fieldListWithoutId;
    }
}
