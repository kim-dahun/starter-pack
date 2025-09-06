package com.service.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAuth is a Querydsl query type for UserAuth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAuth extends EntityPathBase<UserAuth> {

    private static final long serialVersionUID = 413144209L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAuth userAuth = new QUserAuth("userAuth");

    public final com.starter.lib.entity.QCmnBaseCUDEntity _super = new com.starter.lib.entity.QCmnBaseCUDEntity(this);

    public final StringPath comCd = createString("comCd");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final StringPath createUser = _super.createUser;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deleteDate = _super.deleteDate;

    //inherited
    public final StringPath deleteUser = _super.deleteUser;

    public final ListPath<String, StringPath> roles = this.<String, StringPath>createList("roles", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath status = createString("status");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    //inherited
    public final StringPath updateUser = _super.updateUser;

    public final StringPath userId = createString("userId");

    public final QUserInfo userInfo;

    public final StringPath userPassword = createString("userPassword");

    //inherited
    public final StringPath useYn = _super.useYn;

    public QUserAuth(String variable) {
        this(UserAuth.class, forVariable(variable), INITS);
    }

    public QUserAuth(Path<? extends UserAuth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAuth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAuth(PathMetadata metadata, PathInits inits) {
        this(UserAuth.class, metadata, inits);
    }

    public QUserAuth(Class<? extends UserAuth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo")) : null;
    }

}

