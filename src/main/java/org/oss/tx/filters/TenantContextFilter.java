package org.oss.tx.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oss.tx.services.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Servlet filter that resolves the tenant from the request header.
 */
public class TenantContextFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantContextFilter.class);

    private static final String TENANT_ID_HEADER = "X-Tenant-Id";


    private final TenantContext tenantContext;

    public TenantContextFilter(TenantContext tenantContext) {
        this.tenantContext = tenantContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tenantId = request.getHeader(TENANT_ID_HEADER);

        if (tenantId == null) {
            LOGGER.error("Request does not have a tenant associated.");
            response.sendError(403);
            return;
        }

        try {
            tenantContext.setTenantId(tenantId);
            filterChain.doFilter(request, response);
        } finally {
            tenantContext.clearContext();
        }
    }
}
