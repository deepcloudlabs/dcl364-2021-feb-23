package com.example.stockmarket.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext reqctx, ContainerResponseContext resctx) throws IOException {
		resctx.getHeaders().add("Access-Control-Allow-Origin", "*");
		resctx.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		resctx.getHeaders().add("Access-Control-Allow-Credentials", "true");
		resctx.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS, HEAD");
		
	}

}
