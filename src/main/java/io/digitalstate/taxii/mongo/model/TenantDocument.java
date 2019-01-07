package io.digitalstate.taxii.mongo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.taxii.common.TaxiiParsers;
import io.digitalstate.taxii.models.apiroot.TaxiiApiRootResource;
import org.immutables.value.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;

@Value.Immutable
@JsonSerialize(as=ImmutableTenantDocument.class) @JsonDeserialize(builder = ImmutableTenantDocument.Builder.class)
@Document(collection = "tenants")
@JsonTypeName("tenant")
public interface TenantDocument extends TaxiiMongoModel {

    @Override
    @Value.Default
    default String type() {
        return "tenant";
    }

    @JsonProperty("tenant")
    TaxiiApiRootResource tenant();


    @WritingConverter
    public class MongoWriterConverter implements Converter<TenantDocument, org.bson.Document> {
        public org.bson.Document convert(final TenantDocument object) {
            org.bson.Document doc = org.bson.Document.parse(object.toMongoJson());
            return doc;
        }
    }

    @ReadingConverter
    public class MongoReaderConverter implements Converter<org.bson.Document, TenantDocument> {
        public TenantDocument convert(final org.bson.Document object) {
            try {
                return TaxiiParsers.getMongoMapper().readValue(object.toJson(), TenantDocument.class);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
