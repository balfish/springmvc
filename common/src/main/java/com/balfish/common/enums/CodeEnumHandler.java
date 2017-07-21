package com.balfish.common.enums;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class CodeEnumHandler<T extends EnumTrait> extends BaseTypeHandler<T> {

    private Class<T> enumType;

    public CodeEnumHandler(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object value = rs.getObject(columnName);

        if (rs.wasNull() || value == null) {
            return null;
        }

        int param = Integer.valueOf(String.valueOf(value));
        return getEnumByValue(param);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object value = rs.getObject(columnIndex);

        if (rs.wasNull() || value == null) {
            return null;
        }
        int param = Integer.valueOf(String.valueOf(value));
        return getEnumByValue(param);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object value = cs.getObject(columnIndex);

        if (cs.wasNull() || value == null) {
            return null;
        }
        int param = Integer.valueOf(String.valueOf(value));
        return getEnumByValue(param);
    }

    /**
     * 通过int值得到enum
     *
     * @param value code
     * @return enum
     */
    private T getEnumByValue(int value) {
        return EnumCodeUtils.EnumTraitCodeOf(value, enumType);
    }
}
