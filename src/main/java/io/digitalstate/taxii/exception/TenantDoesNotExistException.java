package io.digitalstate.taxii.exception;

import org.springframework.http.HttpStatus;

public class TenantDoesNotExistException extends TaxiiException {

    private static String DEFAULT_TITLE = "Tenant cannot be found or cannot be accessed.";
    private static String DEFAULT_HTTP_STATUS = String.valueOf(HttpStatus.BAD_REQUEST.value());

    public TenantDoesNotExistException(String tenantId) {
        super(DEFAULT_TITLE,
                null, null, null,
                DEFAULT_HTTP_STATUS,
                null, null);
    }

    public TenantDoesNotExistException(String tenantId, String description) {
        super(DEFAULT_TITLE,
                description, null, null,
                DEFAULT_HTTP_STATUS,
                null, null);
    }
}
