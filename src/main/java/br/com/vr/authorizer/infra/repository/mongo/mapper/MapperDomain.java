package br.com.vr.authorizer.infra.repository.mongo.mapper;

public interface MapperDomain<DOMAIN, MODEL> {
    public abstract MODEL toModel(DOMAIN domain);

    public abstract DOMAIN toDomain(MODEL model);
}
