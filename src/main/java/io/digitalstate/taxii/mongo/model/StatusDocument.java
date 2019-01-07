package io.digitalstate.taxii.mongo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.taxii.common.TaxiiParsers;
import io.digitalstate.taxii.models.apiroot.TaxiiApiRoot;
import org.immutables.value.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.annotation.Id;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

@Value.Immutable
@JsonSerialize(as=ImmutableStatusDocument.class) @JsonDeserialize(builder = ImmutableStatusDocument.Builder.class)
@Document(collection = "statuses")
@JsonTypeName("status")
public interface StatusDocument extends TaxiiMongoModel {

    @Override
    @Value.Default
    default String type() {
        return "status";
    }

    @JsonProperty("tenant_id")
    String tenantId();

    @JsonProperty("process_instance_id")
    String processInstanceId();

    @JsonProperty("last_reported_status")
    String lastReportedStatus();

    @WritingConverter
    public class MongoWriterConverter implements Converter<StatusDocument, org.bson.Document> {
        public org.bson.Document convert(final StatusDocument object) {
            org.bson.Document doc = org.bson.Document.parse(object.toMongoJson());
            return doc;
        }
    }

    @ReadingConverter
    public class MongoReaderConverter implements Converter<org.bson.Document, StatusDocument> {
        public StatusDocument convert(final org.bson.Document object) {
            try {
                return TaxiiParsers.getJsonMapper().readValue(object.toJson(), StatusDocument.class);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
