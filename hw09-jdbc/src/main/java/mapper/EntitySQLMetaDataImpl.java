package mapper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData{
    private EntityClassMetaData<T> entityClassMetaDataClient;
    private String fieldList;

    private String fieldListWithoutId;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaDataClient) {
        this.entityClassMetaDataClient = entityClassMetaDataClient;
        this.fieldList = entityClassMetaDataClient.getAllFields().stream()
                .map(Field::getName)
                .collect(Collectors.joining(","));

        this.fieldListWithoutId = entityClassMetaDataClient.getFieldsWithoutId().stream()
                .map(Field::getName)
                .collect(Collectors.joining(","));
    }

    @Override
    public String getSelectAllSql() {
        return "SELECT" + fieldList + "FROM" + entityClassMetaDataClient.getName();
    }

    @Override
    public String getSelectByIdSql() {
        return "SELECT" + fieldList + "FROM" + entityClassMetaDataClient.getName() + "WHERE" + entityClassMetaDataClient.getIdField().getName();
    }

    @Override
    public String getInsertSql() {
        return "INSERT INTO" + entityClassMetaDataClient.getName() + "(" + fieldListWithoutId + ") VALUES (" + fieldListWithoutId.concat(" = ?" );
    }

    @Override
    public String getUpdateSql() {
        List<Field> fields = entityClassMetaDataClient.getFieldsWithoutId();

        String fieldsList = fields.stream()
                .map(Field::getName)
                .collect(Collectors.joining(" = ?"));
        fieldsList = fieldsList.concat(" = ?");
        return "UPDATE" + entityClassMetaDataClient.getName() + "SET" + fieldsList + "WHERE" + entityClassMetaDataClient.getIdField().getName() + " JDBC";
    }
}
