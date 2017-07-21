package com.balfish.common.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class FDateJsonDeserializer extends JsonDeserializer<Date> {
    static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<DateFormat> local = new ThreadLocal<DateFormat>();

    private static DateFormat get() {
        DateFormat format = local.get();
        if (format == null) {
            format = new SimpleDateFormat(PATTERN);
            local.set(format);
        }

        return format;
    }

    @Override
    public Date deserialize(JsonParser gen, DeserializationContext ctxt) throws IOException {
        String date = gen.getText();

        if (StringUtils.isEmpty(date)) {
            return null;
        }

        if (StringUtils.isNumeric(date)) {
            return new Date(Long.valueOf(date));
        }
        try {
            return get().parse(date);
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }
}
