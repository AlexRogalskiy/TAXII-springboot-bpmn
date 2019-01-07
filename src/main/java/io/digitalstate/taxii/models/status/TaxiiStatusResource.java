package io.digitalstate.taxii.models.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.bundle.BundleableObject;
import io.digitalstate.taxii.common.json.views.AdminView;
import io.digitalstate.taxii.common.json.views.TaxiiSpecView;
import io.digitalstate.taxii.models.TaxiiModel;
import org.immutables.value.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Immutable
@Value.Style(typeImmutable = "TaxiiStatus")
@JsonSerialize(as = TaxiiStatus.class) @JsonDeserialize(builder = TaxiiStatus.Builder.class)
public interface TaxiiStatusResource extends TaxiiModel {

    @NotBlank
    @JsonProperty("id")
    @JsonView({TaxiiSpecView.class, AdminView.class})
    String getId();

    @NotBlank
    @JsonProperty("status")
    @JsonView({TaxiiSpecView.class, AdminView.class})
    String getStatus(); // Add Vocab restriction

    @JsonProperty("request_timestamp") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonView({TaxiiSpecView.class, AdminView.class})
    Optional<Instant> getRequestTimestamp();

    @NotNull @Min(0)
    @JsonProperty("total_count")
    @JsonView({TaxiiSpecView.class, AdminView.class})
    long getTotalCount();

    @NotNull @Min(0)
    @JsonProperty("success_count")
    @JsonView({TaxiiSpecView.class, AdminView.class})
    long getSuccessCount();

    @NotNull
    @JsonProperty("successes")
    @JsonView({TaxiiSpecView.class, AdminView.class})
    Set<String> getSuccesses();

    @NotNull @Min(0)
    @JsonProperty("failure_count")
    @JsonView({TaxiiSpecView.class, AdminView.class})
    long getFailureCount();

    @NotNull
    @JsonProperty("failures")
    @JsonView({TaxiiSpecView.class, AdminView.class})
    Set<TaxiiStatusFailureResource> getFailures();

    @NotNull @Min(0)
    @JsonProperty("pending_count")
    @JsonView({TaxiiSpecView.class, AdminView.class})
    long getPendingCount();

    @NotNull
    @JsonProperty("pendings")
    @JsonView({TaxiiSpecView.class, AdminView.class})
    Set<BundleableObject> getPendings();

}
