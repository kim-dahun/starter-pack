package com.service.menu.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuAuth is a Querydsl query type for MenuAuth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuAuth extends EntityPathBase<MenuAuth> {

    private static final long serialVersionUID = -1177443311L;

    public static final QMenuAuth menuAuth = new QMenuAuth("menuAuth");

    public final StringPath comCd = createString("comCd");

    public final StringPath menuId = createString("menuId");

    public final StringPath permitCreate = createString("permitCreate");

    public final StringPath permitDelete = createString("permitDelete");

    public final StringPath permitRead = createString("permitRead");

    public final StringPath permitUpdate = createString("permitUpdate");

    public final DateTimePath<java.time.Instant> updateDate = createDateTime("updateDate", java.time.Instant.class);

    public final StringPath updateUser = createString("updateUser");

    public final StringPath userId = createString("userId");

    public QMenuAuth(String variable) {
        super(MenuAuth.class, forVariable(variable));
    }

    public QMenuAuth(Path<? extends MenuAuth> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuAuth(PathMetadata metadata) {
        super(MenuAuth.class, metadata);
    }

}

