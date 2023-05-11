package mapper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData{
    private EntityClassMetaData<T> entityClassMetaDataClient;
    private String fieldList;

    private String fieldListWithoutId;
    private static final String SELECT_ALL = "SELECT %s * FROM %s";
    private static final String SELECT_BY_ID = "SELECT %s FROM %s WHERE %s = ?";
    private static final String INSERT = "INSERT INTO %s (%s) VALUES (%s)";
    private static final String UPDATE = "UPDATE %s SET %s WHERE %s = ?";
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
        return format (SELECT_ALL,fieldList, entityClassMetaDataClient.getName());
    }

    @Override
    public String getSelectByIdSql() {
        return  format(SELECT_BY_ID, fieldList, entityClassMetaDataClient.getName(), entityClassMetaDataClient.getIdField().getName());
    }

    @Override
    public String getInsertSql() {
        return format(INSERT ,entityClassMetaDataClient.getName(), fieldListWithoutId,fieldListWithoutId.concat(" = ?"));
    }

    @Override
    public String getUpdateSql() {
        List<Field> fields = entityClassMetaDataClient.getFieldsWithoutId();

        String fieldsList = fields.stream()
                .map(Field::getName)
                .collect(Collectors.joining(" = ?"));
        fieldsList = fieldsList.concat(" = ?");
        return format(UPDATE, entityClassMetaDataClient.getName(), fieldsList, entityClassMetaDataClient.getIdField().getName());
    }


}
