package com.service.menu.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuGroupAuth is a Querydsl query type for MenuGroupAuth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuGroupAuth extends EntityPathBase<MenuGroupAuth> {

    private static final long serialVersionUID = 243778558L;

    public static final QMenuGroupAuth menuGroupAuth = new QMenuGroupAuth("menuGroupAuth");

    public final StringPath comCd = createString("comCd");

    public final StringPath groupId = createString("groupId");

    public final StringPath menuId = createString("menuId");

    public final StringPath permitCreate = createString("permitCreate");

    public final StringPath permitDelete = createString("permitDelete");

    public final StringPath permitRead = createString("permitRead");

    public final StringPath permitUpdate = createString("permitUpdate");

    public final DateTimePath<java.time.Instant> updateDate = createDateTime("updateDate", java.time.Instant.class);

    public final StringPath updateUser = createString("updateUser");

    public QMenuGroupAuth(String variable) {
        super(MenuGroupAuth.class, forVariable(variable));
    }

    public QMenuGroupAuth(Path<? extends MenuGroupAuth> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuGroupAuth(PathMetadata metadata) {
        super(MenuGroupAuth.class, metadata);
    }

}

