package mapper;

import repository.DataTemplate;
import repository.executor.DbExecutor;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, EntityClassMetaData entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), resultSet -> {
            try {
                if (resultSet.next()) {
                    T model = (T) entityClassMetaData.getConstructor().newInstance();
                    List<Field> fields = entityClassMetaData.getAllFields();

                    for (Field field : fields) {
                        field.setAccessible(true);
                        field.set(model, resultSet.getObject(field.getName()));
                    }
                    return model;
                }
                throw new RuntimeException("Objects not found");
            } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException();
            }
        });
    }

    @Override
    public List<T> findAll(Connection connection) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectAllSql(), Collections.emptyList(), resultSet -> {
            var clientList = new ArrayList<T>();
            try {
                while (resultSet.next()) {
                    T model = (T) entityClassMetaData.getConstructor().newInstance();
                    List<Field> fields = entityClassMetaData.getAllFields();

                    for (Field field : fields) {
                        field.setAccessible(true);
                        field.set(model, resultSet.getObject(field.getName()));
                    }
                    clientList.add(model);
                }
                return clientList;
            } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public long insert(Connection connection, T client) {
        List<Field> fields = entityClassMetaData.getFieldsWithoutId();
        List<Object> params = new ArrayList<>();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                params.add(field.get(client));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(),params);
    }

    @Override
    public void update(Connection connection, T client) {
        List<Field> fields = entityClassMetaData.getFieldsWithoutId();
        List<Object> params = new ArrayList<>();
        try{
            for(Field field: fields){
                field.setAccessible(true);
                params.add(field.get(client));
            }
            Field fieldId = entityClassMetaData.getIdField();
            fieldId.setAccessible(true);
            params.add(fieldId.get(client));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
         dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(),params);
    }
}
