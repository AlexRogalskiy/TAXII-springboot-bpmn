package io.digitalstate.taxii.models.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.taxii.common.json.views.AdminView;
import io.digitalstate.taxii.common.json.views.TaxiiSpecView;
import io.digitalstate.taxii.models.TaxiiModel;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;


@Value.Immutable
@Value.Style(typeImmutable = "TaxiiStatusFailure")
@JsonSerialize(as = TaxiiStatusFailure.class) @JsonDeserialize(builder = TaxiiStatusFailure.Builder.class)
public interface TaxiiStatusFailureResource extends TaxiiModel {

    @NotBlank
    @JsonProperty("id")
    @JsonView({TaxiiSpecView.class, AdminView.class})
    String getId();

    @NotBlank
    @JsonProperty("message")
    @JsonView({TaxiiSpecView.class, AdminView.class})
    String getMessage(); // Add Vocab restriction

}
