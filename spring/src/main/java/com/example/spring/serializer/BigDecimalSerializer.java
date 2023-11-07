package com.example.spring.serializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Optional;

public class BigDecimalSerializer extends StdSerializer<BigDecimal> implements ContextualSerializer {

    private DecimalFormat decimalFormat;
    private static final int DEFAULT_SCALE = 2;

    public BigDecimalSerializer() {
        super(BigDecimal.class);
    }

    public BigDecimalSerializer(DecimalFormat decimalFormat) {
        super(BigDecimal.class);
        this.decimalFormat = decimalFormat;
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Objects.isNull(value)) {
            jsonGenerator.writeString("-");
        } else {
            if (decimalFormat != null) {
                if (decimalFormat.toPattern().contains(",")) {
                    jsonGenerator.writeString(decimalFormat.format(value));
                } else {
                    jsonGenerator.writeNumber(decimalFormat.format(value));
                }

            } else {
                jsonGenerator.writeString(String.valueOf(value.setScale(DEFAULT_SCALE, RoundingMode.HALF_EVEN)));
            }
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        return Optional.ofNullable(findFormatOverrides(prov, property, handledType()))
                .filter(JsonFormat.Value::hasPattern)
                .map(JsonFormat.Value::getPattern)
                .map(DecimalFormat::new)
                .map(BigDecimalSerializer::new)
                .orElse(this);
    }
}
